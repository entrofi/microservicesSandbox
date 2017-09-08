package net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.service;


import net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.model.Flight;
import net.entrofi.microservices.sandbox.flightbooking.flightsearch.env.model.FlightQuery;

import java.util.Date;
import java.util.List;

public interface FlightService {

    List<Flight> findAvailableFlights(FlightQuery flightQuery);

    void updateInventory(String flightNumber, Date flightDate, int availableSeats, Double fare) throws
            NoSuchFlightException;

    Flight save(Flight flight);
}
