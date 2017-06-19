package net.entrofi.microservices.sandbox.booking.api.resource;


@FunctionalInterface
public interface ImplicitResourceMapper<T, E> {

    T map(E e);
}
