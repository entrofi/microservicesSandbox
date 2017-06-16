package net.entrofi.microservices.sandbox.flightbooking.flightsearch.env.service;

import net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.model.Flight;
import net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.service.FlightService;
import net.entrofi.microservices.sandbox.flightbooking.flightsearch.env.model.FMSFlightMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class FlightQueuesSubscriber {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightQueuesSubscriber.class);


    @Autowired
    private FlightService flightService;

    @Autowired
    private FSKBMSConsumerService kbmsConsumerService;


    @RabbitListener(queues = "#{flightQueue.name}")
    public void processNewFlights(FMSFlightMessage flightMessage) {
        LOGGER.info("Processing new flight " + flightMessage);
        Flight flight = new Flight();
        flight.setDate(flightMessage.getDate());
        flight.setFlightNumber(flightMessage.getFlightNumber());
        flight.setAvailableSeats(flightMessage.getCapacity());
        //TODO add validation for origin and destination in order to fire events for invalid flight creation
        flight.setOrigin(kbmsConsumerService.findAirportByCode(flightMessage.getOrigin()));
        flight.setDestination(kbmsConsumerService.findAirportByCode(flightMessage.getDestination()));

        flightService.save(flight);
    }



}
