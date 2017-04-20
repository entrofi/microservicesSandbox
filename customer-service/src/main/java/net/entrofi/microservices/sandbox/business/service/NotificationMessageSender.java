/*
 * Copyright 2003-2016 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */
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
