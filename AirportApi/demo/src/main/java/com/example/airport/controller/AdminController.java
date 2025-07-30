package com.example.airport.controller;

import com.example.airport.dto.BookingDto;
import com.example.airport.dto.FlightDto;
import com.example.airport.dto.StatusUpdateRequest;
import com.example.airport.dto.UserDto;
import com.example.airport.mapper.FlightMapper;
import com.example.airport.mapper.UserMapper;
import com.example.airport.model.BookingStatus;
import com.example.airport.model.Flight;
import com.example.airport.model.User;
import com.example.airport.service.BookingService;
import com.example.airport.service.FlightService;
import com.example.airport.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private FlightService flightService;
    private UserService userService;
    private BookingService bookingService;

    public AdminController(FlightService flightService, UserService userService, BookingService bookingService) {
        this.flightService = flightService;
        this.userService = userService;
        this.bookingService = bookingService;
    }
    @PostMapping("/flights")
    public FlightDto addFlight(@RequestBody FlightDto flightDto) {
        Flight flight1=FlightMapper.toEntity(flightDto);
        Flight savedFlight=flightService.addFlight(flight1);
        return FlightMapper.toDto(savedFlight);
    }
    @PutMapping("/flights/{id}")
    public FlightDto updateFlight(@RequestBody FlightDto flightDto, @PathVariable Long id) {
        Flight updated = flightService.updateFlight(id, flightDto);
        return FlightMapper.toDto(updated);
    }
    @DeleteMapping("/flights/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }
    @GetMapping("/flights/{id}")
    public FlightDto getFlightById(@PathVariable Long id) {
        Flight flight=flightService.getFlightById(id);
        return FlightMapper.toDto(flight);
    }
    @GetMapping("/flights")
    public List<FlightDto> getAllFlights() {
        return flightService.getAllFlights()
                .stream()
                .map(FlightMapper::toDto)
                .toList();
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(UserMapper::toDto)
                .toList();
    }

    @DeleteMapping("/profile")
    public void deleteUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.deleteUserByEmail(email);
    }
    @GetMapping("/profile")
    public UserDto getUserByEmail() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return UserMapper.toDto(userService.findUserByEmail(email));
    }

    @PutMapping("/profile")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User updatedUser = userService.updateUser(email,userDto);
        return UserMapper.toDto(updatedUser);
    }

    @GetMapping("/bookings")
    public List<BookingDto> getAllBookings() {
        return bookingService.getAllBookings();
    }
    @DeleteMapping("/bookings/{id}")
    public String deleteBookingbyAdmin(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "Booking successfully deleted";
    }

    @PutMapping("/bookings/{id}/status")
    public BookingDto updateBookingStatus(@PathVariable Long id,
                                          @RequestBody StatusUpdateRequest request) {
        return bookingService.updateBookingStatus(id, request.getStatus());
    }

}
