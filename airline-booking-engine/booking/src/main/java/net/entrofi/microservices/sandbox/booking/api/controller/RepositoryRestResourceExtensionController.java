package net.entrofi.microservices.sandbox.booking.api.controller;


import org.springframework.hateoas.LinkBuilder;

public interface RepositoryRestResourceExtensionController {

    default LinkBuilder getBaseLink() {
        throw new IllegalStateException("Base link provider for the controller should be implemented");
    }
}
