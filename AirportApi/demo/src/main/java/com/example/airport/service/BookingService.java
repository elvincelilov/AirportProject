package com.example.airport.service;

import com.example.airport.dto.BookingDto;
import com.example.airport.dto.BookingRequestDto;
import com.example.airport.mapper.BookingMapper;
import com.example.airport.mapper.PassengerMapper;
import com.example.airport.model.*;
import com.example.airport.repository.BookingRepository;
import com.example.airport.repository.FlightRepository;
import com.example.airport.repository.PassengerRepository;
import com.example.airport.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PassengerRepository passengerRepository;

    public BookingService(BookingRepository bookingRepository, FlightRepository flightRepository,
                          UserRepository userRepository, EmailService emailService, PassengerRepository passengerRepository) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.passengerRepository = passengerRepository;
    }

    public List<BookingDto> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(BookingMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<BookingDto> getMyBookings() {
        String email = ((UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Booking> bookings = bookingRepository.findByUser(user);

        return bookings.stream()
                .map(BookingMapper ::toDto)
                .collect(Collectors.toList());

    }

    public BookingDto createBooking(BookingRequestDto bookingRequestDto) {
        String email = ((UserDetails) SecurityContextHolder
        .getContext().getAuthentication().getPrincipal()).getUsername();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Flight flight = flightRepository.findById(bookingRequestDto.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        boolean alreadyBooked = bookingRepository.existsByUserAndFlight(user,flight);
        if(alreadyBooked){
            throw new RuntimeException("Booking already exists");
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setBookingTime(LocalDateTime.now());

        Booking savedBooking = bookingRepository.save(booking);
        Passenger passenger = PassengerMapper.toEntity(bookingRequestDto.getPassenger());
        passenger.setBooking(savedBooking);
        passengerRepository.save(passenger);
        emailService.sendBookingConfirmation(user.getEmail(),booking,passenger);
        return BookingMapper.toDto(savedBooking);

    }

    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new RuntimeException("Booking not found");
        }
        bookingRepository.deleteById(id);
    }

    public void deleteMyBooking(Long id) {
        String email = ((UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal()).getUsername();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You can only delete your own bookings");
        }

        bookingRepository.delete(booking);
    }

    public BookingDto updateBookingStatus(Long bookingId, BookingStatus newStatus) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(newStatus);
        return BookingMapper.toDto(bookingRepository.save(booking));
    }

    public BookingDto confirmPayment(Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getUser().getEmail().equals(email)) {
            try {
                throw new AccessDeniedException("You are not allowed to confirm this booking.");
            } catch (AccessDeniedException e) {
                throw new RuntimeException(e);
            }
        }

        booking.setPaymentConfirmed(true);
        return BookingMapper.toDto(bookingRepository.save(booking));
    }

    public BookingDto checkIn(Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getUser().getEmail().equals(email)) {
            try {
                throw new AccessDeniedException("You are not allowed to check in this booking.");
            } catch (AccessDeniedException e) {
                throw new RuntimeException(e);
            }
        }

        booking.setCheckedIn(true);
        return BookingMapper.toDto(bookingRepository.save(booking));
    }

}
