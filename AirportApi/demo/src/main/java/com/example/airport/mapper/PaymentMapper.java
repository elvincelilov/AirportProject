package com.example.airport.mapper;

import com.example.airport.dto.PaymentDto;
import com.example.airport.model.Payment;

public class PaymentMapper {
    public static PaymentDto toDto(Payment payment) {
        return new PaymentDto()
                .setId(payment.getId())
                .setAmount(payment.getAmount())
                .setBookingId(payment.getBooking().getId())
                .setPaymentTime(payment.getPaymentTime())
                .setStatus(payment.getStatus().name());
    }
}
