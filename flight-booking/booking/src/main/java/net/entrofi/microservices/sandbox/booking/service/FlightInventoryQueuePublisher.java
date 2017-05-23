package net.entrofi.microservices.sandbox.booking.service;


import net.entrofi.microservices.sandbox.booking.BookingApplication;
import net.entrofi.microservices.sandbox.booking.domain.model.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlightInventoryQueuePublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightInventoryQueuePublisher.class);

    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;

    public void updateInventory(Inventory inventory) {
        rabbitMessagingTemplate.convertAndSend(BookingApplication.FLIGHT_INVENTORY_QUEUE, "hello");
        LOGGER.debug("Inventory update message");
    }
}
