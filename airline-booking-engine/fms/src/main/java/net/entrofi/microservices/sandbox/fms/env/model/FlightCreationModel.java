package net.entrofi.microservices.sandbox.fms.env.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 *
 * @deprecated This model is created only for creating sample test data on the fly. Do not use it for actual
 * implementations.
 */
public class FlightCreationModel {

    private final String originAirport;

    private final String destinationAirport;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final Date date;

    @JsonCreator
    public FlightCreationModel(@JsonProperty("originAirport") String originAirport,
                               @JsonProperty("destinationAirport") String destinationAirport,
                               @JsonProperty("date") Date date) {
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.date = date;
    }

    public String getOriginAirport() {
        return originAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public Date getDate() {
        return date;
    }
}
