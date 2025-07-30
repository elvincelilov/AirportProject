package com.example.airport.dto;

import com.example.airport.model.BookingStatus;

import java.time.LocalDateTime;

public class BookingDto {

    private Long id;
    private Long userId;
    private String userEmail;
    private String flightNumber;
    private String origin;
    private String destination;
    private String departureTime;
    private LocalDateTime bookingTime;


    private BookingStatus status;
    private boolean paymentConfirmed;
    private boolean checkedIn;

    public Long getId() {
        return id;
    }

    public BookingDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public BookingDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public BookingDto setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public BookingDto setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
        return this;
    }

    public String getOrigin() {
        return origin;
    }

    public BookingDto setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public BookingDto setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public BookingDto setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
        return this;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public BookingDto setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
        return this;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public BookingDto setStatus(BookingStatus status) {
        this.status = status;
        return this;
    }

    public boolean isPaymentConfirmed() {
        return paymentConfirmed;
    }

    public BookingDto setPaymentConfirmed(boolean paymentConfirmed) {
        this.paymentConfirmed = paymentConfirmed;
        return this;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public BookingDto setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
        return this;
    }
}
