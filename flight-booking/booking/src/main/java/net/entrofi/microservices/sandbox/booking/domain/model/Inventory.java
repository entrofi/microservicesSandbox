package net.entrofi.microservices.sandbox.booking.domain.model;


import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Inventory {


    @EmbeddedId
    private Flight flight;

    @Column(nullable = false)
    private int capacity;

    @ManyToOne(optional = false)
    private FlightMarket flightMarket;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<BookingRecord> bookings = new HashSet<>();

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

    public Set<BookingRecord> getBookings() {
        return bookings;
    }

    public void setBookings(Set<BookingRecord> bookings) {
        this.bookings = bookings;
    }

    @Transient
    public int getAvailableSeats() {
        return this.capacity - this.bookings.size();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
