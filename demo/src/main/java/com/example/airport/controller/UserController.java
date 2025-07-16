package com.example.airport.controller;

import com.example.airport.dto.FlightDto;
import com.example.airport.dto.UserDto;
import com.example.airport.mapper.FlightMapper;
import com.example.airport.mapper.UserMapper;
import com.example.airport.model.Flight;
import com.example.airport.model.User;
import com.example.airport.service.FlightService;
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

    public UserController(FlightService flightService, UserService userService) {
        this.flightService = flightService;
        this.userService = userService;
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

    @PutMapping("/profile/{id}")
    public UserDto updateProfile(@RequestBody UserDto userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findUserByEmail(email);
        return UserMapper.toDto(userService.updateUser(email,userDto));
    }

}
