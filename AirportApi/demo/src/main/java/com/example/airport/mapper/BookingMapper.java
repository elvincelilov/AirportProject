package com.example.airport.mapper;

import com.example.airport.dto.BookingDto;
import com.example.airport.model.Booking;

public class BookingMapper {

    public static BookingDto toDto(Booking booking) {
        return new BookingDto()
                .setId(booking.getId())
                .setUserId(booking.getUser().getId())
                .setUserEmail(booking.getUser().getEmail())
                .setFlightNumber(booking.getFlight().getFlightNumber())
                .setOrigin(booking.getFlight().getOrigin())
                .setDestination(booking.getFlight().getDestination())
                .setDepartureTime(booking.getFlight().getDepartureTime())
                .setBookingTime(booking.getBookingTime())
                .setStatus(booking.getStatus())
                .setPaymentConfirmed(booking.isPaymentConfirmed())
                .setCheckedIn(booking.isCheckedIn());
    }
}
