package net.entrofi.microservices.sandbox.flightbooking.web.domain.model;

/**
 * @author Hasan COMAK
 */
public class WebBookingRecord {

    private WebFlight flight;

    private PassengerInfo passenger;

    private Double fare;

    private String pnr;


    public WebFlight getFlight() {
        return flight;
    }

    public void setFlight(WebFlight flight) {
        this.flight = flight;
    }

    public PassengerInfo getPassenger() {
        return passenger;
    }

    public void setPassenger(PassengerInfo passenger) {
        this.passenger = passenger;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }
}
