/*
 * Copyright 2003-2016 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */
package net.entrofi.microservices.sandbox.model;


import org.springframework.hateoas.ResourceSupport;

public class Greeting extends ResourceSupport {

    private final String greetingMessage;

    public Greeting(final String name) {
        greetingMessage = "Hello " + name;
    }


    public String getGreetingMessage() {
        return this.greetingMessage;
    }
}
