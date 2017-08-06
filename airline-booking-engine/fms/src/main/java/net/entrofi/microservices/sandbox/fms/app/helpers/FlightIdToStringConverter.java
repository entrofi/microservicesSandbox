package net.entrofi.microservices.sandbox.fms.app.helpers;


import net.entrofi.microservices.sandbox.fms.domain.entity.FlightId;
import org.springframework.core.convert.converter.Converter;


public class FlightIdToStringConverter implements Converter<FlightId, String> {

    @Override
    public String convert(FlightId flightId) {
        StringBuilder builder = new StringBuilder(flightId.getFlightNumber());
        final String delimiter = "-";
        builder.append(delimiter)
               .append(flightId.getDepartureAirportCode())
               .append(delimiter)
               .append(flightId.getArrivalAirportCode())
               .append(delimiter)
               .append(flightId.getAirlineCode())
               .append(delimiter)
               .append(flightId.getOriginDate().getTime());
        return builder.toString();
    }
}
