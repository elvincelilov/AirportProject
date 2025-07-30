package com.example.airport.service;

import com.example.airport.model.Booking;
import com.example.airport.model.Flight;
import com.example.airport.model.Passenger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendBookingConfirmation(String toEmail, Booking booking
            , Passenger passenger) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Booking Confirmation");
        message.setText("Dear " + passenger.getFirstName() + " " + passenger.getLastName() + ",\n\n" +
                "Your booking is confirmed.\n\n" +
                "Flight: " + booking.getFlight().getFlightNumber() + "\n" +
                "From: " + booking.getFlight().getOrigin() + "\n" +
                "To: " + booking.getFlight().getDestination() + "\n" +
                "Departure: " + booking.getFlight().getDepartureTime() + "\n" +
                "Passport: " + passenger.getPassportNumber() + "\n\n" +
                "Thank you for choosing us!");
        mailSender.send(message);
    }
}

