package net.entrofi.microservices.sandbox.booking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookingApplication implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(BookingApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(BookingApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}
