package net.entrofi.microservices.sandbox.serviceregistry.euroka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Hasan COMAK
 */
@EnableEurekaServer
@SpringBootApplication
public class RegistryEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistryEurekaApplication.class, args);
    }
}
