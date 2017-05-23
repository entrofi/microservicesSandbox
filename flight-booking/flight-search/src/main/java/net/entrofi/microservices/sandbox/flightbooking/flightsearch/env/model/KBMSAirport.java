package net.entrofi.microservices.sandbox.flightbooking.flightsearch.env.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KBMSAirport {

    private final String code;

    private final String name;

    private final String status;

    @JsonCreator
    public KBMSAirport(@JsonProperty("code") String code,
                       @JsonProperty("name") String name,
                       @JsonProperty("status") String status) {
        this.code = code;
        this.name = name;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
