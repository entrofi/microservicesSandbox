package net.entrofi.microservices.sandbox.booking.api.resource;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.entrofi.microservices.sandbox.booking.domain.model.Flight;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

public final class BookingRequest {

    private final Flight flight;

    private final String passengerId;

    private final Date bookingDate;

    private final String passengerName;

    private final String passengerSurname;

    private final Double fare;

    private final String email;

    private final String phoneNumber;

    @JsonCreator
    public BookingRequest(Map<String, Object> properties) {

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        objectMapper.setDateFormat(dateFormat);
        this.flight = objectMapper.convertValue(properties.get("flight"), Flight.class);
        this.passengerId = (String) properties.get("passengerId");
        this.bookingDate = objectMapper.convertValue(properties.get("bookingDate"), Date.class);
        this.passengerName = (String) properties.get("passengerName");
        this.passengerSurname = (String) properties.get("passengerSurname");
        this.fare = (Double)properties.get("fare");
        this.email = (String) properties.get("email");
        this.phoneNumber = (String) properties.get("phoneNumber");
    }

    public Flight getFlight() {
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
