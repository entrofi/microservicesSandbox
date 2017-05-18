/* *****************************************************************************
 * City.java
 *
 * Copyright (c) 2012 TAV Bilisim Hizmetleri AS, TURKIYE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * TAV Bilisim Hizmetleri AS, TURKIYE
 *
 * 
 ******************************************************************************/
package net.entrofi.microservices.sandbox.kbms.domain.model;

import net.entrofi.microservices.sandbox.kbms.domain.model.base.BaseInfoEntity;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/*******************************************************************************
 * Division
 *
 * @author hcomak
 * @version Jan 5, 2015
 * @created Jan 5, 2015
 ******************************************************************************/
@Audited
@Entity
@Table(name = "KBMS_DIVISION")
public class Division extends BaseInfoEntity {

    public enum DivisionType {
        DISTRICT, STATE, PROVINCE, CITY, ADMINISTRATIVE_REGION, COUNTY
    }


    @NotEmpty(message = "{net.entrofi.microservices.sandbox.kbms.domain.model.commons.error.FieldIsRequired}")
    @Pattern(message = "{net.entrofi.microservices.sandbox.kbms.domain.model.Division.error.validation.codeFormat}", regexp = "[A-Z]{3}\\b")
    @NotNull
    @Size(min = 3, max = 3, message = "{net.entrofi.microservices.sandbox.kbms.domain.model.Division.error.validation.codeFormat}")
    @Column(unique = true, nullable = false)
    private String code;

    @NotEmpty(message = "{net.entrofi.microservices.sandbox.kbms.domain.model.commons.error.name.empty}")
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ACTIVE;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Country country;


    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Division parentDivision;

    @OneToMany
    private Set<Division> subDivisions = new HashSet<>();

    @OneToMany
    private Set<Airport> airports = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DivisionType type;


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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Division getParentDivision() {
        return parentDivision;
    }

    public void setParentDivision(Division parentDivision) {
        this.parentDivision = parentDivision;
    }

    public Set<Division> getSubDivisions() {
        return subDivisions;
    }

    public void setSubDivisions(Set<Division> subDivisions) {
        this.subDivisions = subDivisions;
    }

    public Set<Airport> getAirports() {
        return airports;
    }

    public void setAirports(Set<Airport> airports) {
        this.airports = airports;
    }

    public DivisionType getType() {
        return type;
    }

    public void setType(DivisionType type) {
        this.type = type;
    }
}
