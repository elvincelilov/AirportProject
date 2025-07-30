package com.example.airport.dto;

public class PassengerDto {
    private String firstName;
    private String lastName;
    private String passportNumber;

    public String getFirstName() {
        return firstName;
    }

    public PassengerDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PassengerDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public PassengerDto setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
        return this;
    }
}
