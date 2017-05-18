package net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.service;

import net.entrofi.microservices.sandbox.flightbooking.flightsearch.FlightSearchApplication;
import net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.model.Flight;
import net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.repository.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;


@Component
public class FlightQueuesSubscriber {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightQueuesSubscriber.class);


    @Autowired
    private FlightService flightService;


    @RabbitListener(queues = FlightSearchApplication.FLIGHT_QUEUE)
    public void processNewFlights(Flight flight) {
        LOGGER.info("Processing flight new " + flight);
        flightService.save(flight);
    }


    /**
     * Queue subscriber method to update available seats for a flight on book event. Flight date format should be
     * 'EEE MMM dd kk:mm:ss z yyyy'.
     *
     * @param flightInfo the map&lt;String, Object&gt; containing FLIGHT_NUMBER, FLIGHT_DATE, AVAILABILITY
     */
    @RabbitListener(queues = FlightSearchApplication.FLIGHT_INVENTORY_QUEUE)
    public void processFlightAvailability(Map<String, Object> flightInfo) {
        String flightNumber = (String)flightInfo.get("FLIGHT_NUMBER");
        String dateString = (String)flightInfo.get("FLIGHT_DATE");
        int availability = (Integer) flightInfo.get("AVAILABILITY");
        try {
            DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
            Date flightDate =  dateFormat.parse(dateString);
            LOGGER.debug("Updating flight inventory! Flight Number:  " + flightNumber + ", flight date: " +
                    flightDate + ", availability: " + availability);
            flightService.updateInventory(flightNumber, flightDate, availability);
        } catch (ParseException pe) {
            LOGGER.error("Unable to parse date information while processing inventory queue for flight. Please check " +
                    "provided date information ("+ dateString + ") is in correct format 'EEE MMM dd kk:mm:ss z yyyy'.");
        }

    }

}
