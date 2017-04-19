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
