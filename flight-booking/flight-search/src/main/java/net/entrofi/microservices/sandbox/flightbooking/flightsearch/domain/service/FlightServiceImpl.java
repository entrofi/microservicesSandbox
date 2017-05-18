package net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.service;

import net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.model.Flight;
import net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.repository.FlightRepository;
import net.entrofi.microservices.sandbox.flightbooking.flightsearch.env.FlightQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class FlightServiceImpl implements FlightService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightServiceImpl.class);

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public List<Flight> findAvailableFlights(FlightQuery flightQuery) {
        List<Flight> flights = flightRepository.findByOrigin_DivisionNameAndDestination_DivisionNameAndDate(
                flightQuery.getOriginDivision(),
                flightQuery.getDestinationDivision(),
                flightQuery.getDate());
        LOGGER.trace("Found " + flights.size() + " for query: " + flightQuery.toString());
        return flights.stream().filter(flight -> flight.getAvailableSeats() > 0).collect(Collectors.toList());
    }

    @Override
    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public void updateInventory(String flightNumber, Date flightDate, int availableSeats) {
        Flight flight = flightRepository.findByDateAandFlightNumber(flightDate, flightNumber);
        if(flight == null ) {
            LOGGER.info("Unable to find flight with flight number: " + flightNumber + " on " + flightDate);
        } else {
            LOGGER.trace("Updating available seats to " + availableSeats + " for flight " + flight.toString());
            flight.setAvailableSeats(availableSeats);
            flightRepository.save(flight);
        }

    }
}
