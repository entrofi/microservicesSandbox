package net.entrofi.microservices.sandbox.booking.domain.model;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Access(AccessType.PROPERTY)
public class Flight implements Serializable {

    @Basic(optional = false)
    private String origin;

    @Basic(optional = false)
    private String destination;

    @Basic(optional = false)
    private String flightNumber;


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
}
