package net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.repository;

import net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.model.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;


public interface FlightRepository extends MongoRepository<Flight, String> {

    List<Flight> findByFlightNumber(String flightNumber);

    Flight findByDateAndFlightNumber(Date date, String flightNumber);

    List<Flight> findByOrigin_DivisionNameAndDestination_DivisionNameAndDate(String originDivision, String destinationDivision, Date date);


}
