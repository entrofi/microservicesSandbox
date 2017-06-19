package net.entrofi.microservices.sandbox.booking.api.resource;


import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Date;

public final class BookingRequest {

    private final String inventoryId;

    private final String passengerId;

    private final Date bookingDate;

    private final String passengerName;

    private final String passengerSurname;

    private final Double fare;

    @JsonCreator
    public BookingRequest(final String inventoryId, final String passengerId,
                          final Date bookingDate, final String passengerName,
                          final String passengerSurname, final Double fare) {
        this.inventoryId = inventoryId;
        this.passengerId = passengerId;
        this.bookingDate = bookingDate;
        this.passengerName = passengerName;
        this.passengerSurname = passengerSurname;
        this.fare = fare;
    }


    public String getInventoryId() {
        return inventoryId;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getPassengerSurname() {
        return passengerSurname;
    }

    public Double getFare() {
        return fare;
    }
}
