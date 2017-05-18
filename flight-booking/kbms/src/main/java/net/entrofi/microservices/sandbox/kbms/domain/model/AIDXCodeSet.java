package net.entrofi.microservices.sandbox.kbms.domain.model;

import net.entrofi.microservices.sandbox.kbms.domain.model.base.BaseInfoEntity;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author hcomak
 * @created 11 May 2015
 */
@Entity
@Audited
@Table(name = "KBMS_AIDX_CODE_SET", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"CODE", "CODE_SET"}, name = "KBMS_AIDX_CODE_SET_CODE_U")
})
public class AIDXCodeSet extends BaseInfoEntity {

    public enum CodeType {
        OPERATIONAL,
        PUBLIC,
        UNSPECIFIED
    }

    public static final String CODE_SET_UNSPECIFIED = "UNSPECIFIED";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CodeType type;

    /**
     * Status indicator code
     */
    @NotEmpty(message = "{net.entrofi.microservices.sandbox.kbms.domain.model.commons.error.FieldIsRequired}")
    @Pattern(message = "{net.entrofi.microservices.sandbox.kbms.domain.model.FlightStatus.error.validation.codeFormat}", regexp = "[A-Z0-9]{1,}\\b")
    @NotNull
    @Column(name = "CODE", nullable = false)
    private String code;

    /**
     * Human readable description
     */
    private String description;

    /**
     * AIDX PADIS EDIFACT AND XML CODESET reference number
     */
    @NotEmpty(message = "{net.entrofi.microservices.sandbox.kbms.domain.model.commons.error.FieldIsRequired}")
    @Column(name = "CODE_SET", nullable = false)
    private String codeSet = CODE_SET_UNSPECIFIED;


    public CodeType getType() {
        return type;
    }

    public void setType(CodeType type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCodeSet() {
        return codeSet;
    }

    public void setCodeSet(String codeSet) {
        this.codeSet = codeSet;
    }
}
