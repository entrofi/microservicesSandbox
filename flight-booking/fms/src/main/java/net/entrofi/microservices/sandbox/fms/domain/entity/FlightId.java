package net.entrofi.microservices.sandbox.fms.domain.entity;

import net.entrofi.microservices.sandbox.fms.app.helpers.FlightIdHelper;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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
        this.arrivalAirport = new CodeContextPointer();
        this.airline = new CodeContextPointer();
    }


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = CodeContextPointer.CodeContextAttrNames.CODE,
                    column = @Column(name = "AIRLINE_CODE")),
            @AttributeOverride(name = CodeContextPointer.CodeContextAttrNames.CODE_CONTEXT,
                    column = @Column(name = "AIRLINE_CODE_CTX"))
    })
    private CodeContextPointer airline;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = CodeContextPointer.CodeContextAttrNames.CODE,
                    column = @Column(name = "DEP_AP_CODE")),
            @AttributeOverride(name = CodeContextPointer.CodeContextAttrNames.CODE_CONTEXT,
                    column = @Column(name = "DEP_AP_CODE_CTX"))
    })
    private CodeContextPointer departureAirport;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = CodeContextPointer.CodeContextAttrNames.CODE,
                    column = @Column(name = "ARR_AP_CODE")),
            @AttributeOverride(name = CodeContextPointer.CodeContextAttrNames.CODE_CONTEXT,
                    column = @Column(name = "ARR_AP_CODE_CTX"))
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

    @Override
    public String toString() {
       return FlightIdHelper.convertFlightIdToString(this);
    }


}
