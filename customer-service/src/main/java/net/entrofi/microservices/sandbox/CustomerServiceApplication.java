package net.entrofi.microservices.sandbox;

import net.entrofi.microservices.sandbox.business.model.Customer;
import net.entrofi.microservices.sandbox.business.repository.CustomerRepository;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static final String CUSTOMER_NOTIFICATION_QUEUE_NAME = "CustomerQ";

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return (runner) -> {
            customerRepository.save(new Customer("Hasan", "Comak", "hasan.comak@mail.tv"));
            customerRepository.save(new Customer("Simge", "Comak", "simge.comak@mail.tv"));
            customerRepository.save(new Customer("Cansu", "Comak", "cansu.comak@mail.tv"));
            customerRepository.save(new Customer("Albert", "Einstein", "albert.einstein@mail.tv"));
            customerRepository.save(new Customer("Max", "Planck", "max.planck@mail.tv"));
            customerRepository.save(new Customer("Erwin", "Schrodinger", "erwin.schrodingerk@mail.tv"));
        };
    }


    @Bean("customerQueue")
    public Queue customerQueue() {
        return new Queue(CUSTOMER_NOTIFICATION_QUEUE_NAME, false);
    }
}
