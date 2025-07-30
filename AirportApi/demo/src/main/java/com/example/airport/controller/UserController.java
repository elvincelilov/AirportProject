package com.example.airport.controller;

import com.example.airport.dto.*;
import com.example.airport.mapper.FlightMapper;
import com.example.airport.mapper.UserMapper;
import com.example.airport.model.Booking;
import com.example.airport.model.Flight;
import com.example.airport.model.User;
import com.example.airport.service.BookingService;
import com.example.airport.service.FlightService;
import com.example.airport.service.PaymentService;
import com.example.airport.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final FlightService flightService;
    private final UserService userService;
    private final BookingService bookingService;
    private final PaymentService paymentService;

    public UserController(FlightService flightService, UserService userService
            , BookingService bookingService,PaymentService paymentService) {
        this.flightService = flightService;
        this.userService = userService;
        this.bookingService = bookingService;
        this.paymentService = paymentService;
    }

    @GetMapping("/flights")
    public List<FlightDto> viewFlights() {
        return flightService.getAllFlights()
                .stream()
                .map(FlightMapper::toDto)
                .toList();
    }

    @GetMapping("/flights/{id}")
    public FlightDto getFlightById(@PathVariable Long id) {
        return FlightMapper.toDto(flightService.getFlightById(id));
    }

    @GetMapping("/profile")
    public UserDto getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return UserMapper.toDto(userService.findUserByEmail(email));
    }

    @PutMapping("/profile")
    public UserDto updateProfile(@RequestBody UserDto userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findUserByEmail(email);
        return UserMapper.toDto(userService.updateUser(email,userDto));
    }

    @PostMapping("/bookings")
    public BookingDto createBooking(@RequestBody BookingRequestDto bookingRequestDto) {
        return bookingService.createBooking(bookingRequestDto);
    }

    @GetMapping("/mybooking")
    public List<BookingDto> getMyBookings() {
        return bookingService.getMyBookings();
    }
    @DeleteMapping("/booking/{id}")
    public String deleteMyBooking(@PathVariable Long id) {
        bookingService.deleteMyBooking(id);
        return "Booking successfully deleted";
    }

    @PutMapping("/bookings/{id}/confirm-payment")
    public BookingDto confirmPayment(@PathVariable Long id) {
        return bookingService.confirmPayment(id);
    }

    @PutMapping("/bookings/{id}/check-in")
    public BookingDto checkIn(@PathVariable Long id) {
        return bookingService.checkIn(id);
    }

    // Payment
    @PostMapping("/bookings/{id}/payment")
    public PaymentDto makePayment(@PathVariable Long id ,@RequestParam Double amount) {
        return paymentService.makePayment(id, amount);
    }

}
