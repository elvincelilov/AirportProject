package com.example.airport.model;

import jakarta.persistence.*;

@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String firstName ;
    private String lastName ;
    private  String passportNumber ;

    @OneToOne
    private Booking booking ;

    public Long getId() {
        return id;
    }

    public Passenger setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Passenger setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public Passenger setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Passenger setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Booking getBooking() {
        return booking;
    }

    public Passenger setBooking(Booking booking) {
        this.booking = booking;
        return this;
    }
}
