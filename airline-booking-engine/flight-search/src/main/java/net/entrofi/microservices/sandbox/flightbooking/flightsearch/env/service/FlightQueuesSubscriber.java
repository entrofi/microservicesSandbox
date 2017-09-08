package net.entrofi.microservices.sandbox.flightbooking.flightsearch.env.service;

import net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.model.Flight;
import net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.service.FlightService;
import net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.service.NoSuchFlightException;
import net.entrofi.microservices.sandbox.flightbooking.flightsearch.env.model.InboundFMSFlightMessage;
import net.entrofi.microservices.sandbox.flightbooking.flightsearch.env.model.InboundInventoryUpdateMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


@Component
public class FlightQueuesSubscriber {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightQueuesSubscriber.class);


    @Autowired
    private FlightService flightService;

    @Autowired
    private FSKBMSConsumerService kbmsConsumerService;


    @RabbitListener(queues = "#{flightQueue.name}")
    public void processNewFlights(InboundFMSFlightMessage flightMessage) {
        LOGGER.info("Processing new flight " + flightMessage);
        Flight flight = new Flight();
        flight.setDate(getOffsettedTime(flightMessage.getDate()));
        flight.setFlightNumber(flightMessage.getFlightNumber());
        flight.setAvailableSeats(flightMessage.getCapacity());
        //TODO add validation for origin and destination in order to fire events for invalid flight creation
        flight.setOrigin(kbmsConsumerService.findAirportByCode(flightMessage.getOrigin()));
        flight.setDestination(kbmsConsumerService.findAirportByCode(flightMessage.getDestination()));

        flightService.save(flight);
    }


    /**
     * Queue subscriber method to update available seats for a flight on book event. Flight date format should be
     * 'EEE MMM dd kk:mm:ss z yyyy'.
     *
     */
    @RabbitListener(queues = "${net.entrofi.microservices.sandbox.booking.flightInventoryQueueName}")
    public void processFlightInventoryStatus(InboundInventoryUpdateMessage inventoryUpdateMessage)
            throws NoSuchFlightException{
        LOGGER.info("Processing inventory update mesasge for flight availability: " + inventoryUpdateMessage);
        flightService.updateInventory(inventoryUpdateMessage.getFlight().getFlightNumber(),
                getOffsettedTime(inventoryUpdateMessage.getFlight().getDate()), inventoryUpdateMessage
                .getAvailableSeats(),
                inventoryUpdateMessage.getFare());

   }


   private Date getOffsettedTime(Date date) {
       int offset = TimeZone.getDefault().getOffset(date.getTime());
       Calendar calendar =  Calendar.getInstance();
       calendar.setTimeInMillis(date.getTime() - offset);
       return calendar.getTime();
   }

}
