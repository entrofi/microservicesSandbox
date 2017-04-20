package net.entrofi.microservices.sandbox;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationServiceApplication {

	public static final String CUSTOMER_NOTIFICATION_QUEUE_NAME = "CustomerQ";


	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}



    public Queue customerNotificationQueue() {
        return new Queue(CUSTOMER_NOTIFICATION_QUEUE_NAME, false);
    }
}
