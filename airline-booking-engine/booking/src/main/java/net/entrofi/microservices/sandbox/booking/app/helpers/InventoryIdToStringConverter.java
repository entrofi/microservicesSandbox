package net.entrofi.microservices.sandbox.booking.app.helpers;


import net.entrofi.microservices.sandbox.booking.domain.model.Flight;
import org.springframework.core.convert.converter.Converter;


public class InventoryIdToStringConverter implements Converter<Flight, String> {

    @Override
    public String convert(Flight id) {
        return InventoryIdHelper.convertFlightToString(id);
    }
}
