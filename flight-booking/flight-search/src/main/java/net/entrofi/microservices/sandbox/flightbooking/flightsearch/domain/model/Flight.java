
package net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Flight {

    @Id
    private String id;

    private String flightNumber;

    @Indexed
    private Date date;

    private String origin;

    private String destination;

    private String originDivision;

    private String destinationDivision;



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

    @Override
    public String toString() {
        return "Flight:id="+id+";flightNumber="+flightNumber
                +";origin="+origin+";destination="+destination+";date="+date;
    }


}
