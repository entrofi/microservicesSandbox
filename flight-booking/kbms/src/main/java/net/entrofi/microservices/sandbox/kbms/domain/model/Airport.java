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

/*******************************************************************************
 * Airport
 * TODO Following field should be disscussed with analysts IS_DELETED, RECORD_ACTION, RECORD_ACTION_USER_ID, RECORD_ACTION_TIMESTAMP, SHORT_DESCRIPTION, TIME_ZONE
 * @author hcomak
 * @created 3/6/2015
 * @version $version
 * @since 0.0.1-SNAPSHOT
 ******************************************************************************/
@Entity
@Audited
@Table(name = "KBMS_AIRPORT")
public class Airport extends BaseInfoEntity {


    private static final long serialVersionUID = -8739449904320236625L;

    @NotEmpty(message="{net.entrofi.microservices.sandbox.kbms.domain.model.commons.error.FieldIsRequired}")
    @Pattern(message="{net.entrofi.microservices.sandbox.kbms.domain.model.Airport.error.validation.codeFormat}", regexp = "[A-Z]{3}\\b")
    @NotNull
    @Size(min = 3, max = 4, message = "{net.entrofi.microservices.sandbox.kbms.domain.model.Airport.error.validation.codeFormat}")
    @Column(unique=true, nullable=false)
    private String icaoCode;

    @NotEmpty(message="{net.entrofi.microservices.sandbox.kbms.domain.model.commons.error.FieldIsRequired}")
    @Pattern(message="{net.entrofi.microservices.sandbox.kbms.domain.model.Division.error.validation.codeFormat}", regexp = "[A-Z]{3}\\b")
    @NotNull
    @Size(min = 3, max = 3, message = "{net.entrofi.microservices.sandbox.kbms.domain.model.Airport.error.validation.codeFormat}")
    @Column(unique=true, nullable=false)
    private String code;

    @NotEmpty(message="{net.entrofi.microservices.sandbox.kbms.domain.model.commons.error.name.empty}")
    @Column(nullable=false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ACTIVE;

    private String description;

    @ManyToOne
    private Division division;


    public String getIcaoCode() {
        return icaoCode;
    }

    public void setIcaoCode(String icaoCode) {
        this.icaoCode = icaoCode;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
