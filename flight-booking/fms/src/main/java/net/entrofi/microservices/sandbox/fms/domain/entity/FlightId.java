package net.entrofi.microservices.sandbox.fms.domain.entity;

import org.springframework.util.StringUtils;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * DOC documentation:type_definition Please provide <b><u>detailed</u></b> class definition.
 *
 * @author hcomak
 * @created 31 Mar 2015
 */
@Embeddable
public class FlightId implements Serializable {

    public FlightId() {
        this.departureAirport = new CodeContextPointer();
        this.airline = new CodeContextPointer();
    }


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = CodeContextPointer.CodeContextAttrNames.CODE, column = @Column(name = "AIRLINE_CODE")),
            @AttributeOverride(name = CodeContextPointer.CodeContextAttrNames.CODE_CONTEXT, column = @Column(name = "AIRLINE_CODE_CTX"))
    })
    private CodeContextPointer airline;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = CodeContextPointer.CodeContextAttrNames.CODE, column = @Column(name = "DEP_AP_CODE")),
            @AttributeOverride(name = CodeContextPointer.CodeContextAttrNames.CODE_CONTEXT, column = @Column(name = "DEP_AP_CODE_CTX"))
    })
    private CodeContextPointer departureAirport;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = CodeContextPointer.CodeContextAttrNames.CODE, column = @Column(name = "ARR_AP_CODE")),
            @AttributeOverride(name = CodeContextPointer.CodeContextAttrNames.CODE_CONTEXT, column = @Column(name = "ARR_AP_CODE_CTX"))
    })
    private CodeContextPointer arrivalAirport;


    @Basic(optional = false)
    private String flightNumber;

    @Basic(optional = true)
    private String operationalSuffix = String.valueOf((char) 216);

    @Temporal(value = TemporalType.TIMESTAMP)
    @Basic(optional = false)
    private Date originDate;

    @Basic(optional = true)
    private int repeatNumber;


    @Transient
    public CodeContextPointer getAirline() {
        return airline;
    }

    public void setAirline(CodeContextPointer airline) {
        this.airline = airline;
    }

    @Basic
    @Column(name = "AIRLINE_CODE")
    public String getAirlineCode() {
        return getAirline().getCode();
    }

    public void setAirlineCode(String airlineCode) {
        getAirline().setCode(airlineCode);
    }

    @Basic
    @Column(name = "AIRLINE_CODE_CTX")
    public String getAirlineCodeContext() {
        return getAirline().getCodeContext();
    }

    public void setAirlineCodeContext(String airlineCodeContext) {
        getAirline().setCode(airlineCodeContext);
    }

    @Transient
    public CodeContextPointer getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(CodeContextPointer departureAirport) {
        this.departureAirport = departureAirport;
    }

    @Basic
    @Column(name = "DEP_AP_CODE")
    public String getDepartureAirportCode() {
        return getDepartureAirport().getCode();
    }

    public void setDepartureAirportCode(String code) {
        getDepartureAirport().setCode(code);
    }

    @Basic
    @Column(name = "DEP_AP_CODE_CTX")
    public String getDepartureAirportCodeContext() {
        return getDepartureAirport().getCodeContext();
    }

    public void setDepartureAirportCodeContext(String codeContext) {
        getDepartureAirport().setCodeContext(codeContext);
    }


    @Transient
    public CodeContextPointer getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(CodeContextPointer departureAirport) {
        this.arrivalAirport = departureAirport;
    }

    @Basic
    @Column(name = "ARR_AP_CODE")
    public String getArrivalAirportCode() {
        return getArrivalAirport().getCode();
    }

    public void setArrivalAirportCode(String code) {
        getArrivalAirport().setCode(code);
    }

    @Basic
    @Column(name = "ARR_AP_CODE_CTX")
    public String getArrivalAirportCodeContext() {
        return getArrivalAirport().getCodeContext();
    }

    public void setArrivalAirportCodeContext(String codeContext) {
        getArrivalAirport().setCodeContext(codeContext);
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOperationalSuffix() {
        return operationalSuffix;
    }

    public void setOperationalSuffix(String operationalSuffix) {
        this.operationalSuffix = operationalSuffix;
    }

    public Date getOriginDate() {
        return originDate;
    }

    public void setOriginDate(Date originDate) {
        this.originDate = originDate;
    }

    public int getRepeatNumber() {
        return repeatNumber;
    }

    public void setRepeatNumber(int repeatNumber) {
        this.repeatNumber = repeatNumber;
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
         * @param departureAirport mandatory
         * @param arrivalAirport   mandatory
         * @param flightNumber     mandatory
         * @throws IllegalArgumentException when either departure airport, arrival airport or flightNumber is invalid or
         *                                  departure airport and arrival airport are same.
         */
        public static FlightIdBuilder newInstance(@NotNull final CodeContextPointer departureAirport,
                                                  @NotNull final CodeContextPointer arrivalAirport,
                                                  @NotNull final String flightNumber) {
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
