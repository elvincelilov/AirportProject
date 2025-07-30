package com.example.airport.mapper;

import com.example.airport.dto.PassengerDto;
import com.example.airport.model.Passenger;

public class PassengerMapper {
    public static PassengerDto toDto(Passenger passenger) {
        return new PassengerDto()
                .setFirstName(passenger.getFirstName())
                .setLastName(passenger.getLastName())
                .setPassportNumber(passenger.getPassportNumber());
    }

    public static Passenger toEntity(PassengerDto dto) {
        return new Passenger()
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setPassportNumber(dto.getPassportNumber());
    }
}
