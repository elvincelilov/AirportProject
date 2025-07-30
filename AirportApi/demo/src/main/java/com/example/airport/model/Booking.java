package com.example.airport.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Flight flight;

    private LocalDateTime bookingTime;

    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.PENDING;

    private boolean paymentConfirmed = false;

    private boolean isCheckedIn = false;

    public Long getId() {
        return id;
    }

    public Booking setId(Long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Booking setUser(User user) {
        this.user = user;
        return this;
    }

    public Flight getFlight() {
        return flight;
    }

    public Booking setFlight(Flight flight) {
        this.flight = flight;
        return this;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public Booking setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
        return this;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public Booking setStatus(BookingStatus status) {
        this.status = status;
        return this;
    }

    public boolean isPaymentConfirmed() {
        return paymentConfirmed;
    }

    public Booking setPaymentConfirmed(boolean paymentConfirmed) {
        this.paymentConfirmed = paymentConfirmed;
        return this;
    }

    public boolean isCheckedIn() {
        return isCheckedIn;
    }

    public Booking setCheckedIn(boolean checkedIn) {
        isCheckedIn = checkedIn;
        return this;
    }
}
