package net.entrofi.microservices.sandbox.flightbooking.web.domain.model;


import java.util.ArrayList;
import java.util.List;

public class WebBookingInfo {

    private WebFlight flight;

    private List<PassengerInfo> passengers = new ArrayList<>();

    public WebBookingInfo() {
    }

    public WebBookingInfo(WebFlight flight) {
        this.passengers.add(new PassengerInfo());
        this.flight = flight;
    }


    public void setFlight(WebFlight flight) {
        this.flight = flight;
    }

    public WebFlight getFlight() {
        return flight;
    }

    public List<PassengerInfo> getPassengers() {
        return passengers;
    }

}
