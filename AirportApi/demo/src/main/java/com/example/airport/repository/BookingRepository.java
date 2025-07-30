package com.example.airport.repository;

import com.example.airport.model.Booking;
import com.example.airport.model.Flight;
import com.example.airport.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUser(User user);
    boolean existsByUserAndFlight(User user, Flight flight);
}
