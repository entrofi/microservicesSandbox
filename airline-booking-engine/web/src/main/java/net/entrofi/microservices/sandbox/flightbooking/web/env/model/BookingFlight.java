package net.entrofi.microservices.sandbox.flightbooking.web.env.model;

import java.util.Date;

/**
 * @author Hasan COMAK
 */
public class BookingFlight {


    private String origin;

    private String destination;

    private String flightNumber;

    private Date date;

    public BookingFlight(String origin, String destination, String flightNumber, Date date) {
        this.origin = origin;
        this.destination = destination;
        this.flightNumber = flightNumber;
        this.date = date;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
