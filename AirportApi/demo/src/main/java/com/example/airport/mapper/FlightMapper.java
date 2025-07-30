package com.example.airport.mapper;

import com.example.airport.dto.FlightDto;
import com.example.airport.model.Flight;

public class FlightMapper {

    public static FlightDto toDto(Flight flight) {
        FlightDto flightDto = new FlightDto();
        flightDto.setId(flight.getId());
        flightDto.setFlightNumber(flight.getFlightNumber());
        flightDto.setOrigin(flight.getOrigin());
        flightDto.setDepartureTime(flight.getDepartureTime());
        flightDto.setDestination(flight.getDestination());
        return flightDto;
    }

    public static Flight toEntity(FlightDto flightDto) {
        Flight flight = new Flight();
        flight.setId(flightDto.getId());
        flight.setFlightNumber(flightDto.getFlightNumber());
        flight.setOrigin(flightDto.getOrigin());
        flight.setDepartureTime(flightDto.getDepartureTime());
        flight.setDestination(flightDto.getDestination());
        return flight;
    }
}
