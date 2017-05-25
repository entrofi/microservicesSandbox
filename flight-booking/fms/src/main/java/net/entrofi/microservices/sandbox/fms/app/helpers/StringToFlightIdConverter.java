package net.entrofi.microservices.sandbox.fms.app.helpers;


import net.entrofi.microservices.sandbox.fms.domain.entity.FlightId;
import org.springframework.core.convert.converter.Converter;

public class StringToFlightIdConverter implements Converter<String, FlightId> {


    @Override
    public FlightId convert(String s) {
        return FlightIdHelper.convertFlightIdFromIATAString(s);
    }
}
