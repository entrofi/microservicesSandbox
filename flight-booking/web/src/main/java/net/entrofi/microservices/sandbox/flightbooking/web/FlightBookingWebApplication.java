package net.entrofi.microservices.sandbox.flightbooking.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@EnableGlobalMethodSecurity
@SpringBootApplication
public class FlightBookingWebApplication implements CommandLineRunner{

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightBookingWebApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(FlightBookingWebApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}
