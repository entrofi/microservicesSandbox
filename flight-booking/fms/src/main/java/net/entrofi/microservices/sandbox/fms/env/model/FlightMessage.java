
package net.entrofi.microservices.sandbox.fms.env.model;


import java.util.Date;

public class FlightMessage {

    private final String flightNumber;

    private final Date date;

    private final String origin;

    private final String destination;

    public FlightMessage(String flightNumber, Date date, String origin, String destination) {
        this.flightNumber = flightNumber;
        this.date = date;
        this.origin = origin;
        this.destination = destination;
    }


    public String getFlightNumber() {
        return flightNumber;
    }

    public Date getDate() {
        return date;
    }


    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

}
