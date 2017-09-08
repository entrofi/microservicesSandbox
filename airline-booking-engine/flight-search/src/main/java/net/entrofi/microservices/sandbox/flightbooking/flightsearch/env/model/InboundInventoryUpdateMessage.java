package net.entrofi.microservices.sandbox.flightbooking.flightsearch.env.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;
import java.util.Date;

public final class InboundInventoryUpdateMessage {

    private final Flight flight;

    private final int capacity;

    private final int availableSeats;

    private final double fare;

    @JsonCreator
    public InboundInventoryUpdateMessage(@NotNull @JsonProperty("flight") Flight flight,
                                         @JsonProperty("capacity") int capacity,
                                         @NotNull @JsonProperty("availableSeats") int availableSeats,
                                         @NotNull @JsonProperty("flightMarket") FlightMarket flightMarket) {
        this.flight = flight;
        this.capacity = capacity;
        this.availableSeats = availableSeats;
        this.fare = flightMarket.getBaseFare();
    }


    public Flight getFlight() {
        return flight;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getFare() {
        return fare;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("Flight: " + flight)
                .append(" capacity:" + capacity)
                .append(" availableSeats:" + availableSeats)
                .append(" fare:" + fare).build();
    }


    public static class Flight {

        private final String origin;


        private final  String destination;


        private final String flightNumber;


        private final Date date;

        @JsonCreator
        public Flight(@JsonProperty("origin") final String origin,
                      @JsonProperty("destination") final String destination,
                      @JsonProperty("flightNumber") final String flightNumber,
                      @JsonProperty("date") final Date date) {
            this.origin = origin;
            this.destination = destination;
            this.flightNumber = flightNumber;
            this.date = date;
        }

        public String getOrigin() {
            return origin;
        }

        public String getDestination() {
            return destination;
        }

        public String getFlightNumber() {
            return flightNumber;
        }

        public Date getDate() {
            return date;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append(flightNumber)
                    .append(origin)
                    .append(destination)
                    .append(date).build();
        }
    }

    public static class FlightMarket {

        private final String airportI;


        private final String airportII;


        private final boolean directional;

        private final Double baseFare;


        @JsonCreator
        public FlightMarket( @JsonProperty("airportI") String airportI,
                             @JsonProperty("airportII") String airportII,
                             @JsonProperty("directional") boolean directional,
                             @JsonProperty("baseFare") Double baseFare) {
            this.airportI = airportI;
            this.airportII = airportII;
            this.directional = directional;
            this.baseFare = baseFare;
        }


        public String getAirportI() {
            return airportI;
        }

        public String getAirportII() {
            return airportII;
        }

        public boolean isDirectional() {
            return directional;
        }

        public Double getBaseFare() {
            return baseFare;
        }
    }
}
