package com.example.airport.service;

import com.example.airport.dto.PaymentDto;
import com.example.airport.mapper.PaymentMapper;
import com.example.airport.model.Booking;
import com.example.airport.model.Payment;
import com.example.airport.model.PaymentStatus;
import com.example.airport.repository.BookingRepository;
import com.example.airport.repository.PaymentRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public PaymentService(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    public PaymentDto makePayment(Long bookingId ,Double amount) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        // İcazə yoxlaması
        if (!booking.getUser().getEmail().equals(currentUserEmail)) {
            try {
                throw new AccessDeniedException("You are not allowed to make payment for this booking.");
            } catch (AccessDeniedException e) {
                throw new RuntimeException(e);
            }
        }

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(amount);
        payment.setPaymentTime(LocalDateTime.now());
        payment.setStatus(PaymentStatus.COMPLETED);

        return PaymentMapper.toDto(paymentRepository.save(payment));
    }
}
