package net.entrofi.microservices.sandbox.business.service;


import net.entrofi.microservices.sandbox.business.model.Customer;
import net.entrofi.microservices.sandbox.business.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Lazy
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private NotificationMessageSender messageSender;


    public Customer register(Customer customer) {
        Optional<Customer> registeredCustomer = customerRepository.findByEmail(customer.getEmail());
        if (registeredCustomer.isPresent()) {
            throw new RuntimeException("Customer with email address '" + customer.getEmail() + "' already exists!");
        } else {
            customerRepository.save(customer);
            messageSender.send(customer.getEmail());
        }
        return customer;
    }


}
