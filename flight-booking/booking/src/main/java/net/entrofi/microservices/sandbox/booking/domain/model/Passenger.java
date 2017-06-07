package net.entrofi.microservices.sandbox.booking.domain.model;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"PASSENGER_ID", "ORIGIN", "DESTINATION", "FLIGHT_NUMBER"}))
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "PASSENGER_ID")
    private String passengerId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="flightNumber", column=@Column(name = "FLIGHT_NUMBER")),
            @AttributeOverride(name="origin", column=@Column(name = "ORIGIN")),
            @AttributeOverride(name="destination", column = @Column(name = "DESTINATION"))
    })
    private Flight flight;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}