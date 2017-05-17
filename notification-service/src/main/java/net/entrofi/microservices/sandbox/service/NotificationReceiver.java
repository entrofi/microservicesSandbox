
package net.entrofi.microservices.sandbox.service;


import net.entrofi.microservices.sandbox.NotificationServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class NotificationReceiver {


    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationReceiver.class);

    @Autowired
    private MailNotificationService mailNotificationService;



    @RabbitListener(queues = NotificationServiceApplication.CUSTOMER_NOTIFICATION_QUEUE_NAME)
    public void processMessage(String email) {
        LOGGER.info("Processing message for  "  + email);
        mailNotificationService.sendRegisterationSuccessMail(email);
    }
}


