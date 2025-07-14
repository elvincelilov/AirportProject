package com.example.airport.service;

import com.example.airport.dto.FlightDto;
import com.example.airport.model.Flight;
import com.example.airport.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FlightService {

    private FlightRepository repository;

    public FlightService(FlightRepository repository) {
        this.repository = repository;
    }

    public List<Flight> getAllFlights() {
        return repository.findAll();
    }

    public Flight addFlight(Flight flight) {
        return repository.save(flight);

    }

    public void deleteFlight(Long id) {
        repository.deleteById(id);
    }

    public Flight updateFlight(Long id , FlightDto flightDto) {
        Flight flight = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Flight not found"));

        flight.setFlightNumber(flightDto.getFlightNumber());
        flight.setDepartureTime(flightDto.getDepartureTime());
        flight.setDestination(flightDto.getDestination());
        flight.setOrigin(flightDto.getOrigin());
        return repository.save(flight);
    }

    public Flight getFlightById(Long id) {
        return repository.findById(id).get();
    }
}
