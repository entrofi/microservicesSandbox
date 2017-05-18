package net.entrofi.microservices.sandbox.booking.domain.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class BookingRecord {

    private Long id;

    private Flight flight;

    @OneToOne
    private Passenger passenger;

    private Double fare;

    private Date bookingDate;


}
