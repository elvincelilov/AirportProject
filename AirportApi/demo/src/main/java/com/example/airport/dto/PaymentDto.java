package com.example.airport.dto;

import java.time.LocalDateTime;

public class PaymentDto {
    private Long id;
    private Long bookingId;
    private Double amount;
    private LocalDateTime paymentTime;
    private String status;

    public Long getId() {
        return id;
    }

    public PaymentDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public PaymentDto setBookingId(Long bookingId) {
        this.bookingId = bookingId;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public PaymentDto setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public PaymentDto setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public PaymentDto setStatus(String status) {
        this.status = status;
        return this;
    }
}
