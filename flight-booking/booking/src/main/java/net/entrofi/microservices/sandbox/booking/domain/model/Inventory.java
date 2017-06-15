package net.entrofi.microservices.sandbox.booking.domain.model;


import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Inventory {


    @EmbeddedId
    private Flight flight;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private FlightMarket flightMarket;

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public FlightMarket getFlightMarket() {
        return flightMarket;
    }

    public void setFlightMarket(FlightMarket flightMarket) {
        this.flightMarket = flightMarket;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
