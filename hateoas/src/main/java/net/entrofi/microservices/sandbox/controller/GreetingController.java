package net.entrofi.microservices.sandbox.controller;

import net.entrofi.microservices.sandbox.model.Greeting;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @RequestMapping("/hello")
    public HttpEntity<Greeting> sayHello(@RequestParam(name = "name", defaultValue = "Spring Boot HATEOAS", required = false)
                                         String name) {
        Greeting greeting = new Greeting(name);
        greeting.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(GreetingController.class).sayHello(name)).withSelfRel());
        return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
    }
}
