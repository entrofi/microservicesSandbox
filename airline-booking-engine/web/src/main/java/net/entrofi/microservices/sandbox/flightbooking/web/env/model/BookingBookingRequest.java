package net.entrofi.microservices.sandbox.flightbooking.web.env.model;

import net.entrofi.microservices.sandbox.flightbooking.web.domain.model.PassengerInfo;

import java.util.Date;

/**
 * @author Hasan COMAK
 */
public class BookingBookingRequest {

    private BookingFlight flight;

    private String passengerId;

    private Date bookingDate;

    private String passengerName;

    private String passengerSurname;

    private Double fare;

    private String email;

    private String phoneNumber;

    public void setFlight(BookingFlight flight) {
        this.flight = flight;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public void setPassengerSurname(String passengerSurname) {
        this.passengerSurname = passengerSurname;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BookingBookingRequest(BookingFlight flight,
                                 Date bookingDate,
                                 PassengerInfo passengerInfo,
                                 Double fare) {
        this.flight = flight;
        this.passengerId = passengerInfo.getPassengerId();
        this.bookingDate = bookingDate;
        this.passengerName = passengerInfo.getName();
        this.passengerSurname = passengerInfo.getSurname();
        this.passengerId = passengerInfo.getPassengerId();
        this.fare = fare;
        this.email = passengerInfo.getEmail();
        this.phoneNumber = passengerInfo.getPhoneNumber();
    }

    public BookingFlight getFlight() {
        return flight;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getPassengerSurname() {
        return passengerSurname;
    }

    public Double getFare() {
        return fare;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
