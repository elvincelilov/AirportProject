package com.example.airport.dto;

public class FlightDto {
    private Long id;

    private String flightNumber;
    private String origin;
    private String destination;
    private String departureTime;

    public FlightDto() {
    }

    public Long getId() {
        return id;
    }

    public FlightDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public FlightDto setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
        return this;
    }

    public String getOrigin() {
        return origin;
    }

    public FlightDto setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public FlightDto setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public FlightDto setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
        return this;
    }
}
