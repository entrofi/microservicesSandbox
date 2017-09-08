package net.entrofi.microservices.sandbox.flightbooking.web.env.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class FlightSearchFlight {


    private String id;

    private String flightNumber;

    private Date date;

    private FlightSearchAirport origin;

    private FlightSearchAirport destination;

    private int availableSeats;

    private Double fare;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public FlightSearchAirport getOrigin() {
        return origin;
    }

    public void setOrigin(FlightSearchAirport origin) {
        this.origin = origin;
    }

    public FlightSearchAirport getDestination() {
        return destination;
    }

    public void setDestination(FlightSearchAirport destination) {
        this.destination = destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }


    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
