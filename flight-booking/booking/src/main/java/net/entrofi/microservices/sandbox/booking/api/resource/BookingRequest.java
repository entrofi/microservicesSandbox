package net.entrofi.microservices.sandbox.booking.api.resource;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public final class BookingRequest {

    private final String inventoryId;

    private final String passengerId;

    private final Date bookingDate;

    private final String passengerName;

    private final String passengerSurname;

    private final Double fare;

    @JsonCreator
    public BookingRequest(@JsonProperty("inventoryId")final String inventoryId,
                          @JsonProperty("passengerId") final String passengerId,
                          @JsonProperty("bookingDate")final Date bookingDate,
                          @JsonProperty("passengerName")final String passengerName,
                          @JsonProperty("passengerSurname")final String passengerSurname,
                          @JsonProperty("fare")final Double fare) {
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
