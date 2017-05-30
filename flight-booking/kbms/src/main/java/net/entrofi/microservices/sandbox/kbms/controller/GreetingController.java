package net.entrofi.microservices.sandbox.kbms.controller;


import net.entrofi.microservices.sandbox.kbms.domain.model.Greeting;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/greeting")
@RefreshScope
public class GreetingController {

    @Value("${net.entrofi.microservices.property.refreshcheck:Default Check}")
    private String propertyRefreshCheck = "default";

    @RequestMapping("/hello")
    public HttpEntity<Greeting> sayHello(@RequestParam(name = "name", defaultValue = "Spring Boot HATEOAS", required = false)
                                         String name) {
        Greeting greeting = new Greeting(name + " " + propertyRefreshCheck);
        greeting.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(GreetingController.class).sayHello
                (name + " " + propertyRefreshCheck)).withSelfRel());
        return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
    }
}
