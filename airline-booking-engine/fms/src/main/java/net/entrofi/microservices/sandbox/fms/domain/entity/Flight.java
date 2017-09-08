package net.entrofi.microservices.sandbox.fms.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO Note that this entity is not audited because Hibernate envers currently does not support EmbeddedIds with nested Embeddable fields.
 * TODO Therefore Audit log for this entity should be handled manually.
 *
 * @author hcomak
 * @created 27 Mar 2015
 */
@Entity
//@Audited
@Table(name = "FMS_FLIGHT")
public class Flight {

    public enum FlightCategory {
        INTERNATIONAL, DOMESTIC
    }


    @EmbeddedId
    private FlightId id;


    @Pattern(message = "{net.entrofi.microservices.sandbox.fms.domain.entity.Flight.error.validation.codeFormat}", regexp = "[A-Z0-9]{0,7}\\b")
    private String callSign;

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

    @Pattern(message = "{net.entrofi.microservices.sandbox.fms.domain.entity.flight.error.validation.overMidnightIndicatorFormat}", regexp = "[1-9]{1}\\b")
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
    private FlightStatus publicStatus;

    @ManyToMany
    @JsonBackReference
    private Set<Crew> crews = new HashSet<>();


    @ManyToOne(optional = false)
    private Aircraft aircraft;


    public FlightId getId() {
        return id;
    }

    public void setId(FlightId id) {
        this.id = id;
    }

    public String getCallSign() {
        return callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
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
    @JsonIgnore
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


    public Set<Crew> getCrews() {
        return crews;
    }

    public void setCrews(Set<Crew> crews) {
        this.crews = crews;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("status", publicStatus)
                .append("operational status", operationalStatus)
                .toString();
    }
}
