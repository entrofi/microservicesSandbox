package net.entrofi.microservices.sandbox.business.service;


import net.entrofi.microservices.sandbox.CustomerServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class NotificationMessageSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationMessageSender.class);
    @Autowired
    private RabbitMessagingTemplate messagingTemplate;

    public void send(String message) {
        LOGGER.info("Sending message to the queue" + CustomerServiceApplication.CUSTOMER_NOTIFICATION_QUEUE_NAME);
        messagingTemplate.convertAndSend(CustomerServiceApplication.CUSTOMER_NOTIFICATION_QUEUE_NAME, message);
    }


}
