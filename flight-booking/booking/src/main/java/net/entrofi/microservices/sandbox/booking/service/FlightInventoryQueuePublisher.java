package net.entrofi.microservices.sandbox.booking.service;


import net.entrofi.microservices.sandbox.booking.domain.model.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FlightInventoryQueuePublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightInventoryQueuePublisher.class);

    @Value("${net.entrofi.microservices.sandbox.booking.flightInventoryQueueName}")
    private String flightInventoryQueueName = "flightInventoryQueue";

    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;

    public void updateInventory(Inventory inventory) {
        rabbitMessagingTemplate.convertAndSend(flightInventoryQueueName, "hello");
        LOGGER.debug("Inventory update message");
    }
}
