package net.entrofi.microservices.sandbox.fms.app.helpers;


import net.entrofi.microservices.sandbox.fms.domain.entity.CodeContextPointer;
import net.entrofi.microservices.sandbox.fms.domain.entity.FlightId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Date;

public final class FlightIdHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightIdHelper.class);

    private FlightIdHelper() {

    }

    /**
     *
     * @param flightId
     * @return
     */
    public static String convertFlightIdToString(FlightId flightId) {
        if(flightId == null) {
            LOGGER.info("Returning null for null flight id");
            return null;
        }
        StringBuilder builder = new StringBuilder(flightId.getFlightNumber());
        final String delimiter = "-";
        builder.append(delimiter)
               .append(flightId.getDepartureAirportCode())
               .append(delimiter)
               .append(flightId.getArrivalAirportCode())
               .append(delimiter)
               .append(flightId.getAirlineCode())
               .append(delimiter)
               .append(flightId.getOriginDate() != null ? flightId.getOriginDate().getTime() : "")
               .append(delimiter)
               .append(flightId.getOperationalSuffix())
               .append(delimiter)
               .append(flightId.getRepeatNumber());
        return builder.toString();
    }

    /**
     * Converts pre formatted flight id string to {@link FlightId}. The format should obey the pattern:
     * <quote>[flight_number]-[departure_airport_code]-[arrival_airport_code]
     *                       -[airline_code]-[origin_date]-[operational_suffix]-[repeat_number]</quote>
     * @param flightIdString preformatted iata based flight id string:
     *                       <quote>[flight_number]-[departure_airport_code]-[arrival_airport_code]
     *                       -[airline_code]-[origin_date]-[operational_suffix]-[repeat_number]</quote>
     * @return flight id instance generated
     * @throws {@link IllegalArgumentException} when flight id string is empty or array representation of the
     * splitted string has lenght less than 7.
     */
    public static FlightId convertFlightIdFromIATAString(String flightIdString) {
        final String delimiter = "-";
        final int fieldCount = 7;
        if(StringUtils.isEmpty(flightIdString)) {
            throw new IllegalArgumentException("Invalid flight id string representation, " +
                    "Flight id should not be empty or null");
        }
        String[] fields = flightIdString.split(delimiter);
        if(fields == null || fields.length < fieldCount) {
            throw new IllegalArgumentException("Flight id string should be delimited with " + delimiter +
                    " and must have at least " + fieldCount + " fields");
        }
        final String defaultCodeContext = CodeContextPointer.CodeContext.IATA.name();
        final CodeContextPointer departurAirport = new CodeContextPointer(fields[1], defaultCodeContext);
        final CodeContextPointer arrivalAirport = new CodeContextPointer(fields[2], defaultCodeContext);
        final CodeContextPointer airline = new CodeContextPointer(fields[3], defaultCodeContext);
        FlightId flightId = FlightIdBuilder.newInstance(departurAirport, arrivalAirport, fields[0], airline)
                                           .originDate(new Date(Long.valueOf(fields[4])))
                                           .operationalSuffix(fields[5])
                                           .repeatNumber(Integer.valueOf(fields[6]))
                                           .build();
        return flightId;
    }

    public static class FlightIdBuilder {

        private CodeContextPointer airline;

        private CodeContextPointer departure;

        private CodeContextPointer arrival;

        private String flightNumber;

        private Date originDate;

        private String operationalSuffix = String.valueOf((char) 216);

        private int repeatNumber;


        private FlightIdBuilder() {
        }

        /**
         * Constructs a new instance of {@link net.entrofi.microservices.sandbox.fms.app.helpers.FlightIdHelper.FlightIdBuilder}
         * @param departureAirport mandatory
         * @param arrivalAirport   mandatory
         * @param flightNumber     mandatory
         * @throws IllegalArgumentException when either departure airport, arrival airport or flightNumber is invalid or
         *                                  departure airport and arrival airport are same.
         */
        public static FlightIdBuilder newInstance(@NotNull final CodeContextPointer departureAirport,
                                                  @NotNull final CodeContextPointer arrivalAirport,
                                                  @NotNull final String flightNumber,
                                                  @NotNull final CodeContextPointer airline) {
            if (departureAirport == null || arrivalAirport == null || StringUtils.isEmpty(flightNumber)) {
                throw new IllegalArgumentException("Departure airport, Arrival airport and flight number must all be valid for flight creation");
            } else if (departureAirport.equals(arrivalAirport)) {
                throw new IllegalArgumentException("Departure airport and arrival airport must be different airports");
            }
            FlightIdBuilder builder = new FlightIdBuilder();
            builder.airline = new CodeContextPointer("TK", CodeContextPointer.CodeContext.IATA.name());
            builder.departure = departureAirport;
            builder.arrival = arrivalAirport;
            builder.flightNumber = flightNumber;
            return builder;
        }

        public FlightIdBuilder originDate(Date originDate) {
            this.originDate = originDate;
            return this;
        }

        public FlightIdBuilder operationalSuffix(String operationalSuffix) {
            this.operationalSuffix = operationalSuffix;
            return this;
        }

        public FlightIdBuilder repeatNumber(int repeatNumber) {
            this.repeatNumber = repeatNumber;
            return this;
        }

        public FlightId build() {
            FlightId flightId = new FlightId();
            flightId.setDepartureAirport(this.departure);
            flightId.setArrivalAirport(this.arrival);
            flightId.setAirline(airline);
            flightId.setFlightNumber(flightNumber);
            flightId.setOriginDate(originDate);
            flightId.setRepeatNumber(repeatNumber);
            flightId.setOperationalSuffix(operationalSuffix);
            return flightId;
        }
    }
}
