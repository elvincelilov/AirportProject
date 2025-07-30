package com.example.airport.repository;

import com.example.airport.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Passenger findByBookingId(Long bookingId);
}
