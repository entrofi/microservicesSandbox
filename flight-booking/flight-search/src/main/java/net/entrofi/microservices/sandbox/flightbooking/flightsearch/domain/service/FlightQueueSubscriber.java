package net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.service;

import net.entrofi.microservices.sandbox.flightbooking.flightsearch.FlightSearchApplication;
import net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.model.Flight;
import net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.repository.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class FlightQueueSubscriber {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightQueueSubscriber.class);


    @Autowired
    private FlightRepository flightRepository;


    @RabbitListener(queues = FlightSearchApplication.FLIGHT_QUEUE)
    public void processFlights(Flight flight) {
        LOGGER.info("Processing flight: " + flight);
        flightRepository.save(flight);
    }
}
