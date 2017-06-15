package net.entrofi.microservices.sandbox.fms;

import net.entrofi.microservices.sandbox.fms.app.FMSInitializerService;
import net.entrofi.microservices.sandbox.fms.app.FMSRepositoryRestResourceConfig;
import org.h2.server.web.WebServlet;
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
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
@EnableGlobalMethodSecurity
@EnableAuthorizationServer
@EnableResourceServer
@Import(FMSRepositoryRestResourceConfig.class)
public class FMSApplication implements CommandLineRunner {

    @Value("${net.entrofi.microservices.sandbox.fms.flightQueueName}")
    private String flightQueueName;


    @Autowired
    private FMSInitializerService fmsInitializerService;

    public static void main(String[] args) {
        SpringApplication.run(FMSApplication.class, args);
    }


    @Override
    public void run(String... strings) throws Exception {
        fmsInitializerService.createFlights(30);
    }


    @Bean
    ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }


    @Bean
    public Queue flightQueue() {
        return new Queue(flightQueueName, false);
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


