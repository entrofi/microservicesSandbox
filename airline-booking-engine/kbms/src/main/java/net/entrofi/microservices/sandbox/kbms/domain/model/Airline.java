package net.entrofi.microservices.sandbox.kbms.domain.model;

import net.entrofi.microservices.sandbox.kbms.domain.model.base.BaseInfoEntity;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author hcomak
 * @created 23 Mar 2015
 */
@Entity
@Audited
@Table(name = "KBMS_AIRLINE")
public class Airline extends BaseInfoEntity {

    @NotEmpty(message = "{net.entrofi.microservices.sandbox.kbms.domain.model.commons.error.name.empty}")
    @Column(nullable = false)
    private String name;

    @Pattern(message = "{net.entrofi.microservices.sandbox.kbms.domain.model.Airline.error.validation.codeFormat}", regexp = "[A-Z0-9]{2,3}\\b")
    @NotNull
    @Size(min = 3, max = 3, message = "{net.entrofi.microservices.sandbox.kbms.domain.model.Airline.error.validation.codeFormat}")
    @Column(nullable = false)
    private String code;

    @NotEmpty(message = "{net.entrofi.microservices.sandbox.kbms.domain.model.commons.error.FieldIsRequired}")
    @Pattern(message = "{net.entrofi.microservices.sandbox.kbms.domain.model.Airline.error.validation.codeFormat}", regexp = "[A-Z0-9]{2,3}\\b")
    @NotNull
    @Size(min = 3, max = 3, message = "{net.entrofi.microservices.sandbox.kbms.domain.model.Airline.error.validation.icaoCodeFormat}")
    @Column(unique = true, nullable = false)
    private String icaoCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ACTIVE;

    @NotNull
    @ManyToOne
    private Country country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIcaoCode() {
        return icaoCode;
    }

    public void setIcaoCode(String icaoCode) {
        this.icaoCode = icaoCode;
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
}
