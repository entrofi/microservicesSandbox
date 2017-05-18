package net.entrofi.microservices.sandbox.controller;


import net.entrofi.microservices.sandbox.business.model.Customer;
import net.entrofi.microservices.sandbox.business.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public Customer register(@RequestBody Customer customer) {
        return customerService.register(customer);
    }
}
