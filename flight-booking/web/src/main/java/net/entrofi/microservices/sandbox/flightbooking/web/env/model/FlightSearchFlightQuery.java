package net.entrofi.microservices.sandbox.flightbooking.web.env.model;

import java.util.Date;

public class FlightSearchFlightQuery {

    private String originDivision;

    private String destinationDivision;

    private Date date;


    public String getOriginDivision() {
        return originDivision;
    }

    public void setOriginDivision(String originDivision) {
        this.originDivision = originDivision;
    }

    public String getDestinationDivision() {
        return destinationDivision;
    }

    public void setDestinationDivision(String destinationDivision) {
        this.destinationDivision = destinationDivision;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FlightQuery: origin = " + originDivision + "; destination = " + destinationDivision
                + "; date = " + date;
    }
}
