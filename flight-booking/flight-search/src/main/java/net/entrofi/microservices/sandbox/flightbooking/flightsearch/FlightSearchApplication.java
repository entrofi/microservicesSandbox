package net.entrofi.microservices.sandbox.flightbooking.flightsearch;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class FlightSearchApplication {


    @Value("${net.entrofi.microservices.sandbox.fms.flightQueueName}")
    private String flightQueueName;

    @Value("${net.entrofi.microservices.sandbox.booking.flightInventoryQueueName}")
    private String flightInventoryQueueName = "flightInventoryQueue";

    public static void main(String[] args) {
        SpringApplication.run(FlightSearchApplication.class, args);
    }


    @Bean
    public Queue flightQueue() {
        return new Queue(flightQueueName, false);
    }

    @Bean
    public Queue flightInventoryQueue() {
        return new Queue(flightInventoryQueueName, false);
    }

    @Bean
    public RabbitTemplate jsonRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonConverter());
        return template;
    }

    @Bean
    public MessageConverter jsonConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
