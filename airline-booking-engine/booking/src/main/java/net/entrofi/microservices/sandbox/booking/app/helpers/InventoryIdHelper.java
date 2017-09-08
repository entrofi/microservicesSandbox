package net.entrofi.microservices.sandbox.booking.app.helpers;


import net.entrofi.microservices.sandbox.booking.domain.model.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Date;

public final class InventoryIdHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryIdHelper.class);

    private static final String delimiter = "-";

    private InventoryIdHelper() {

    }

    /**
     *
     * @param id
     * @return
     */
    public static String convertFlightToString(Flight id) {
        if(id == null) {
            LOGGER.info("Returning null for null flight id");
            return null;
        }
        StringBuilder builder = new StringBuilder(id.getFlightNumber());
        builder.append(delimiter)
               .append(id.getOrigin())
               .append(delimiter)
               .append(id.getDestination())
               .append(delimiter)
               .append(id.getDate() != null ? id.getDate().getTime() : "");
        return builder.toString();
    }

    /**
     * Converts pre formatted string representation of an inventory id to {@link Flight} which is the id class for
     * inventory instances.
     * The format should obey the pattern:
     * <quote>[flight_number]-[origin_airport_code]-[arrival_airport_code]-[origin_date]
     * @param idString preformatted iata based string representation of {@link Flight}:
     *                       <quote>[[flight_number]-[origin_airport_code]-[arrival_airport_code]-[origin_date]</quote>
     * @return flight instance generated
     * @throws {@link IllegalArgumentException} when flight id string is empty or array representation of the
     * splitted string has length less than 4.
     */
    public static Flight convertFlightFromIATAString(String idString) throws IllegalArgumentException {
        final String delimiter = "-";
        final int fieldCount = 4;
        if(StringUtils.isEmpty(idString)) {
            throw new IllegalArgumentException("Invalid flight id string representation, " +
                    "Flight id should not be empty or null");
        }

        String[] fields = idString.split(delimiter);
        if(fields == null || fields.length < fieldCount) {
            throw new IllegalArgumentException("Flight id string should be delimited with " + delimiter +
                    " and must have at least " + fieldCount + " fields");
        }
        Flight flight = new Flight();
        flight.setFlightNumber(fields[0]);
        flight.setOrigin(fields[1]);
        flight.setDestination(fields[2]);
        flight.setDate(new Date(Long.valueOf(fields[3])));
        return flight;
    }


}
