package com.example.airport.dto;

public class BookingRequestDto {

    private Long flightId;
    private PassengerDto passenger;

    public Long getFlightId() {
        return flightId;
    }

    public BookingRequestDto setFlightId(Long flightId) {
        this.flightId = flightId;
        return this;
    }

    public PassengerDto getPassenger() {
        return passenger;
    }

    public BookingRequestDto setPassengerDto(PassengerDto passenger) {
        this.passenger = passenger;
        return this;
    }
}
