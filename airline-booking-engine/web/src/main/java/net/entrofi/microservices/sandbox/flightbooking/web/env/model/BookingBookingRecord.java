package net.entrofi.microservices.sandbox.flightbooking.web.env.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author Hasan COMAK
 */
public class BookingBookingRecord {

    private final Double fare;

    private final Date bookingDate;

    private final String passengerId;

    private final String name;

    private final String surname;

    private final String pnr;


    @JsonCreator
    public BookingBookingRecord(@JsonProperty("fare") Double fare,
                                @JsonProperty("bookingDate") Date bookingDate,
                                @JsonProperty("passengerId") String passengerId,
                                @JsonProperty("name")  String name,
                                @JsonProperty("surname") String surname,
                                @JsonProperty("pnr") String pnr) {
        this.fare = fare;
        this.bookingDate = bookingDate;
        this.passengerId = passengerId;
        this.name = name;
        this.surname = surname;
        this.pnr = pnr;
    }


    public Double getFare() {
        return fare;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPnr() {
        return pnr;
    }
}
