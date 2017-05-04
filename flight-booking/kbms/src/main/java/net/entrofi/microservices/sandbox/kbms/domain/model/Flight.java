package net.entrofi.microservices.sandbox.kbms.domain.model;

import org.hibernate.validator.constraints.NotEmpty;


import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.Duration;
import java.util.Date;

/**
 * TODO Note that this entity is not audited because Hibernate envers currently does not support EmbeddedIds with nested Embeddable fields.
 * TODO Therefore Audit log for this entity should be handled manually.
 * @author hcomak
 * @created 27 Mar 2015
 */
@Entity
//@Audited
@Table(name="FMS_FLIGHT")
public class Flight {

    public enum DepartureArrival{
        DEPARTURE,ARRIVAL
    }

    public enum FlightCategory{
        INTERNATIONAL, DOMESTIC
    }


    @EmbeddedId
    private FlightId id;


    @NotEmpty(message = "{aero.tav.tams.fms.domain.entity.commons.error.name.empty}")
    @Pattern(message="{aero.tav.tams.fms.domain.entity.Flight.error.validation.codeFormat}", regexp = "[A-Z]{3,4}\\b")
    @Column(nullable = false)
    private String systemAirport;

    @Pattern(message="{aero.tav.tams.fms.domain.entity.Flight.error.validation.codeFormat}", regexp = "[A-Z0-9]{0,7}\\b")
    private String callSign;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DepartureArrival departureArrival;


    private Boolean arrivalSecurityCheck;

    private Boolean departureSecurityCheck;

    private Long flightDurationLong;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "STD")
    private Date scheduledTimeDeparture;

    @Column(name = "STA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduledTimeArrival;

    @Column(name = "ETD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date estimatedTimeDeparture;

    @Column(name = "ETA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date estimatedTimeArrival;

    @Column(name = "ATD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualTimeDeparture;

    @Column(name = "ATA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualTimeArrival;

    @Pattern(message="{aero.tav.tams.fms.domain.entity.flight.error.validation.overMidnightIndicatorFormat}", regexp = "[1-9]{1}\\b")
    @Column(name = "MIDNIGHT_IND", length = 1)
    private String overMidnightIndicator;


    @Enumerated(EnumType.STRING)
    private FlightCategory category;

    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalDate;


    @ManyToOne
    @JoinColumn(name = "OP_STATUS")
    private FlightStatus operationalStatus;

    @ManyToOne
    @JoinColumn(name = "PUB_STATUS")
    private FlightStatus  publicStatus;


    public FlightId getId() {
        return id;
    }

    public void setId(FlightId id) {
        this.id = id;
    }

    public String getSystemAirport() {
        return systemAirport;
    }

    public void setSystemAirport(String systemAirport) {
        this.systemAirport = systemAirport;
    }

    public String getCallSign() {
        return callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public DepartureArrival getDepartureArrival() {
        return departureArrival;
    }

    public void setDepartureArrival(DepartureArrival departureArrival) {
        this.departureArrival = departureArrival;
    }

    public Boolean getArrivalSecurityCheck() {
        return arrivalSecurityCheck;
    }

    public void setArrivalSecurityCheck(Boolean arrivalSecurityCheck) {
        this.arrivalSecurityCheck = arrivalSecurityCheck;
    }

    public Boolean getDepartureSecurityCheck() {
        return departureSecurityCheck;
    }

    public void setDepartureSecurityCheck(Boolean departureSecurityCheck) {
        this.departureSecurityCheck = departureSecurityCheck;
    }

    @Transient
    public Duration getFlightDuration() {
        return Duration.ofMillis(flightDurationLong);
    }


    public Long getFlightDurationLong() {
        return flightDurationLong;
    }

    public void setFlightDurationLong(Long flightDurationL) {
        this.flightDurationLong = flightDurationL;
    }


    public Date getScheduledTimeDeparture() {
        return scheduledTimeDeparture;
    }

    public void setScheduledTimeDeparture(Date scheduledTimeDeparture) {
        this.scheduledTimeDeparture = scheduledTimeDeparture;
    }

    public Date getScheduledTimeArrival() {
        return scheduledTimeArrival;
    }

    public void setScheduledTimeArrival(Date scheduledTimeArrival) {
        this.scheduledTimeArrival = scheduledTimeArrival;
    }

    public Date getEstimatedTimeDeparture() {
        return estimatedTimeDeparture;
    }

    public void setEstimatedTimeDeparture(Date estimatedTimeDeparture) {
        this.estimatedTimeDeparture = estimatedTimeDeparture;
    }

    public Date getEstimatedTimeArrival() {
        return estimatedTimeArrival;
    }

    public void setEstimatedTimeArrival(Date estimatedTimeArrival) {
        this.estimatedTimeArrival = estimatedTimeArrival;
    }

    public Date getActualTimeDeparture() {
        return actualTimeDeparture;
    }

    public void setActualTimeDeparture(Date actualTimeDeparture) {
        this.actualTimeDeparture = actualTimeDeparture;
    }

    public Date getActualTimeArrival() {
        return actualTimeArrival;
    }

    public void setActualTimeArrival(Date actualTimeArrival) {
        this.actualTimeArrival = actualTimeArrival;
    }

    public String getOverMidnightIndicator() {
        return overMidnightIndicator;
    }

    public void setOverMidnightIndicator(String overMidnightIndicator) {
        this.overMidnightIndicator = overMidnightIndicator;
    }

    public FlightCategory getCategory() {
        return category;
    }

    public void setCategory(FlightCategory category) {
        this.category = category;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public FlightStatus getOperationalStatus() {
        return operationalStatus;
    }

    public void setOperationalStatus(FlightStatus operationalStatus) {
        this.operationalStatus = operationalStatus;
    }

    public FlightStatus getPublicStatus() {
        return publicStatus;
    }

    public void setPublicStatus(FlightStatus publicStatus) {
        this.publicStatus = publicStatus;
    }
}
