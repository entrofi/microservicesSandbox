package net.entrofi.microservices.sandbox.booking.app.helpers;


import net.entrofi.microservices.sandbox.booking.domain.model.Flight;
import org.springframework.core.convert.converter.Converter;

public class StringToInventoryIdConverter implements Converter<String, Flight> {


    @Override
    public Flight convert(String s) {
        return InventoryIdHelper.convertFlightFromIATAString(s);
    }
}
