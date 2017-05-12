package net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.repository;

import net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.model.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface FlightRepository extends MongoRepository<Flight, String> {
}
