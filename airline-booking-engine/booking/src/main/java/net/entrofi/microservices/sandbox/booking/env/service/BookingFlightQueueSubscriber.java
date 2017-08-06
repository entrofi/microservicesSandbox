package net.entrofi.microservices.sandbox.booking.env.service;


import net.entrofi.microservices.sandbox.booking.domain.model.Flight;
import net.entrofi.microservices.sandbox.booking.domain.model.Inventory;
import net.entrofi.microservices.sandbox.booking.domain.service.InventoryService;
import net.entrofi.microservices.sandbox.booking.env.model.InboundFlightMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingFlightQueueSubscriber {


    private static final Logger LOGGER = LoggerFactory.getLogger(BookingFlightQueueSubscriber.class);

    @Autowired
    private InventoryService inventoryService;


    @RabbitListener(queues = "#{flightQueue.name}")
    public void processNewFlights(InboundFlightMessage flightMessage) {
        LOGGER.info("Processing new flight " + flightMessage);
        Flight flight = new Flight();
        flight.setDate(flightMessage.getDate());
        flight.setFlightNumber(flightMessage.getFlightNumber());
        flight.setOrigin(flightMessage.getOrigin());
        flight.setDestination(flightMessage.getDestination());
        Inventory inventory = inventoryService.save(flight, flightMessage.getCapacity());
        LOGGER.debug("Created inventory: " +inventory);
    }


}
