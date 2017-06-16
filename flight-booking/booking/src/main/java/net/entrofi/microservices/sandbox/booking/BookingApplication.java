package net.entrofi.microservices.sandbox.booking;

import net.entrofi.microservices.sandbox.booking.domain.model.Inventory;
import net.entrofi.microservices.sandbox.booking.service.FlightInventoryQueuePublisher;
import org.h2.server.web.WebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookingApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingApplication.class);

    @Value("${net.entrofi.microservices.sandbox.fms.flightExchangeName}")
    private String flightExchangeName;

    @Value("${net.entrofi.microservices.sandbox.booking.flightInventoryQueueName}")
    private String flightInventoryQueueName = "flightInventoryQueue";


    @Autowired
    private FlightInventoryQueuePublisher flightInventoryQueuePublisher;

    public static void main(String[] args) {
        SpringApplication.run(BookingApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        flightInventoryQueuePublisher.updateInventory(new Inventory());
    }

    @Bean
    ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }

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
        return BindingBuilder.bind(flightQueue).to(flightExchange);
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
