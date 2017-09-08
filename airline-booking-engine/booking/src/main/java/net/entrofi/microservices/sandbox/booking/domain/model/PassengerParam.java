package net.entrofi.microservices.sandbox.booking.domain.model;


import javax.validation.constraints.NotNull;

public final class PassengerParam {

    private final String passengerId;

    private final String name;

    private final  String surname;

    private final String email;

    private final String phoneNumber;

    public PassengerParam(@NotNull String passengerId,
                          @NotNull String name,
                          @NotNull String surname,
                          @NotNull String email,
                          @NotNull String phoneNumber) {
        this.passengerId = passengerId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
