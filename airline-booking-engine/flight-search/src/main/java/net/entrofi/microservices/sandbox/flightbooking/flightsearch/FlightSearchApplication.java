package net.entrofi.microservices.sandbox.flightbooking.flightsearch;


import net.entrofi.microservices.sandbox.utils.rest.RestTemplateFactory;
import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableEurekaClient
public class FlightSearchApplication {



    @Value("${net.entrofi.microservices.sandbox.fms.flightExchangeName}")
    private String flightExchangeName;

    @Value("${net.entrofi.microservices.sandbox.booking.flightInventoryQueueName}")
    private String flightInventoryQueueName = "flightInventoryQueue";

    public static void main(String[] args) {
        SpringApplication.run(FlightSearchApplication.class, args);
    }


//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        return connectionFactory;
//    }
//
//    @Bean
//    public AmqpAdmin amqpAdmin() {
//        return new RabbitAdmin(connectionFactory());
//    }

    @Bean
    public FanoutExchange flightExchange() {
        return new FanoutExchange(flightExchangeName);
    }

    @Bean
    public Queue flightQueue() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding flightExchangeBinding(@Autowired FanoutExchange flightExchange,
                                         @Autowired Queue flightQueue) {
        return  BindingBuilder.bind(flightQueue).to(flightExchange);
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


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return RestTemplateFactory.getRestTemplateWithHalMessageConverter();
    }
}
