package net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.model;


import org.apache.commons.lang3.builder.ToStringBuilder;

public class Airport {

    private String code;

    private String name;

    private String divisionName;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
