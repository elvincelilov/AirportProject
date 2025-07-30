package com.example.airport.dto;

import com.example.airport.model.BookingStatus;

public class StatusUpdateRequest {
    private BookingStatus status;

    public BookingStatus getStatus() {
        return status;
    }

    public StatusUpdateRequest setStatus(BookingStatus status) {
        this.status = status;
        return this;
    }
}
