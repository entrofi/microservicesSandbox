package net.entrofi.microservices.sandbox.flightbooking.web.domain.model;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.Assert;

public class WebAirport {
    private String code;

    private String name;

    private String divisionName;

    private String divisionCode;

    private String countryName;

    private String countryCode;

    /**
     * Construct a representation object for ui
     * @param airport the object itself and name and code parameters should not be null.
     * @param countryParam paramater object representing country
     * @param division parameter object representing country division where airport is located.
     * @throws IllegalArgumentException when @{link airport} parameter is null or the fields of it are null.
     */
    public WebAirport(NameCodeParamWrapper airport, CountryParam countryParam, NameCodeParamWrapper division) {
        Assert.notNull(airport, "Impossible to construct airport with no name and code.");
        Assert.notNull(airport.name, "Impossible to construct airport with no name and code.");
        Assert.notNull(airport.code, "Impossible to construct airport with no name and code.");
        this.code = airport.code;
        this.name = airport.name;
        if(countryParam != null) {
            this.countryName = countryParam.name;
            this.countryCode = countryParam.code;
        }

        if (division != null) {
            this.divisionName = division.name;
            this.divisionCode = division.code;
        }

    }

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

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


    public final static class CountryParam {

        private String name;
        private String code;
        private final String region;

        public CountryParam(NameCodeParamWrapper nameCodeParamWrapper, String region) {
            this.name = nameCodeParamWrapper.name;
            this.code = nameCodeParamWrapper.code;
            this.region = region;
        }
    }


    public final static class NameCodeParamWrapper {
        private final String name;

        private final String code;

        public NameCodeParamWrapper(String name, String code) {
            this.name = name;
            this.code = code;
        }
    }
}
