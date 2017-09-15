package net.entrofi.microservices.sandbox.flightbooking.web;


import net.entrofi.microservices.sandbox.utils.rest.RestTemplateFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.client.RestTemplate;


@EnableGlobalMethodSecurity
@EnableEurekaClient
@SpringBootApplication
public class FlightBookingWebApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightBookingWebApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(FlightBookingWebApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return RestTemplateFactory.getRestTemplateWithHalMessageConverter();
    }
}
