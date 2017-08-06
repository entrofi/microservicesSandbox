package net.entrofi.microservices.sandbox.flightbooking.flightsearch.env.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class InboundFMSFlightMessage {

    private final String flightNumber;

    private final Date date;

    private final String origin;

    private final String destination;

    private final int capacity;

    @JsonCreator
    public InboundFMSFlightMessage(@JsonProperty("flightNumber") String flightNumber,
                                   @JsonProperty("date") Date date,
                                   @JsonProperty("capacity") int capacity,
                                   @JsonProperty("origin") String origin,
                                   @JsonProperty("destination") String destination) {
        this.flightNumber = flightNumber;
        this.date = date;
        this.capacity = capacity;
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

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return flightNumber + " on " + date + " from " + origin + " to " + destination;
    }

}
