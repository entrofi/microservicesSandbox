package net.entrofi.microservices.sandbox.flightbooking.flightsearch.controller;


import net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.model.Flight;
import net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.service.FlightService;
import net.entrofi.microservices.sandbox.flightbooking.flightsearch.env.model.FlightQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightSearchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightSearchController.class);

    @Autowired
    private FlightService flightService;


    @PostMapping(value = "/search")
    public List<Flight> findAvailable(@RequestBody FlightQuery query) {
        List<Flight> flights = flightService.findAvailableFlights(query);
        LOGGER.trace("Found " + (flights != null ? flights.size(): 0) + " for query " + query);
        return flights;
    }
}
