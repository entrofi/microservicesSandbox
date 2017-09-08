package net.entrofi.microservices.sandbox.flightbooking.web.domain.model;


import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class WebFlightSearchQuery {

    private String origin;

    private String destination;

    private Date date;


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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
