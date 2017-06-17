package net.entrofi.microservices.sandbox.booking.service;


import net.entrofi.microservices.sandbox.booking.domain.model.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FlightInventoryQueuePublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightInventoryQueuePublisher.class);

    @Value("${net.entrofi.microservices.sandbox.booking.flightInventoryQueueName}")
    private String flightInventoryQueueName = "flightInventoryQueue";

    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;

    public void updateInventory(Inventory inventory) {

        Map<String, Object> tempQueueMsg = new HashMap<>();
        tempQueueMsg.put("FLIGHT_NUMBER", inventory.getFlight().getFlightNumber());
        tempQueueMsg.put("FLIGHT_DATE", inventory.getFlight().getDate());
        tempQueueMsg.put("AVAILABILITY", inventory.getAvailableSeats());
        rabbitMessagingTemplate.convertAndSend(flightInventoryQueueName, tempQueueMsg);
        LOGGER.debug("Inventory update message");
    }
}
