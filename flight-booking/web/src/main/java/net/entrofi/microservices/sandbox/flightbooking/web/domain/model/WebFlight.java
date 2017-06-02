package net.entrofi.microservices.sandbox.flightbooking.web.domain.model;


import java.util.Date;

public class WebFlight {

    private String id;

    private String flightNumber;

    private Date date;

    private WebAirport origin;

    private WebAirport destination;

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

    public WebAirport getOrigin() {
        return origin;
    }

    public void setOrigin(WebAirport origin) {
        this.origin = origin;
    }

    public WebAirport getDestination() {
        return destination;
    }

    public void setDestination(WebAirport destination) {
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
}
