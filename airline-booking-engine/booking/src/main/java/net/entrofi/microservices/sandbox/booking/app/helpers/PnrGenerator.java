package net.entrofi.microservices.sandbox.booking.app.helpers;

import net.entrofi.microservices.sandbox.booking.domain.model.Flight;
import net.entrofi.microservices.sandbox.booking.domain.model.PassengerParam;

import java.util.Base64;

/**
 * @author Hasan COMAK
 */
public final class PnrGenerator {

    private PnrGenerator() {

    }

    public static String generate(Flight flight, PassengerParam passenger) {
        StringBuilder builder = new StringBuilder();
        builder.append(flight.getFlightNumber())
                .append(flight.getOrigin())
                .append(flight.getDestination())
                .append(passenger.getName().charAt(0))
                .append(passenger.getSurname().charAt(0))
                .append(Base64.getEncoder().encode(passenger.getPassengerId().getBytes()));
        return builder.toString();
    }
}
