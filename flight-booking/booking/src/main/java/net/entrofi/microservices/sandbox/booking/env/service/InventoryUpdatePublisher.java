package net.entrofi.microservices.sandbox.booking.env.service;


import net.entrofi.microservices.sandbox.booking.domain.model.Inventory;
import net.entrofi.microservices.sandbox.booking.env.model.OutboundInventoryUpdateMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InventoryUpdatePublisher {


    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryUpdatePublisher.class);

    @Value("${net.entrofi.microservices.sandbox.booking.flightInventoryQueueName}")
    private String flightInventoryQueueName = "flightInventoryQueue";

    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;


    public void publishInventoryUpdate(Inventory inventory) {
        LOGGER.info("Publishing updates for inventory: " + inventory);
        rabbitMessagingTemplate.convertAndSend(flightInventoryQueueName, new OutboundInventoryUpdateMessage(inventory));
        LOGGER.debug("Inventory update message");
    }
}
