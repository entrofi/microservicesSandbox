
package net.entrofi.microservices.sandbox.model;


import org.springframework.hateoas.ResourceSupport;
import org.springframework.util.StringUtils;

public class Greeting extends ResourceSupport {


    public static final String GREETING_BODY = "Hello";

    private final String greetingMessage;

    public Greeting() {
        this.greetingMessage = GREETING_BODY;
    }


    public Greeting(final String name) {

        if(!StringUtils.isEmpty(name)) {
            greetingMessage = GREETING_BODY + " " + name;
        }else {
            greetingMessage = GREETING_BODY;
        }
    }


    public String getGreetingMessage() {
        return this.greetingMessage;
    }
}
