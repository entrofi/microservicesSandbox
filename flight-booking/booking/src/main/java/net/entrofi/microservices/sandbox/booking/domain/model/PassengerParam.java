package net.entrofi.microservices.sandbox.booking.domain.model;


import javax.validation.constraints.NotNull;

public final class PassengerParam {

    private final String passengerId;

    private final String name;

    private final  String surname;

    public PassengerParam(@NotNull final String passengerId,
                          @NotNull final String name, @NotNull final String surname) {
        this.passengerId = passengerId;
        this.name = name;
        this.surname = surname;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
