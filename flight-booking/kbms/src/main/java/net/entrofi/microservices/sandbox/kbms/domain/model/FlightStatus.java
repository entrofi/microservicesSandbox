package net.entrofi.microservices.sandbox.kbms.domain.model;

import net.entrofi.microservices.sandbox.kbms.domain.model.base.BaseInfoEntity;
import org.hibernate.envers.Audited;

import javax.persistence.*;


@Entity
@Audited
@Table(name="FMS_FLIGHT_STATUS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"CODE", "CODE_SET"}, name = "FMS_FLIGHT_STAT_CODE_CODE_SET_U")
})
public class FlightStatus extends BaseInfoEntity {

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
    @Column(name="CODE", nullable = false)
    private String code;

    /**
     * Human readable description
     */
    private String description;

    /**
     * AIDX PADIS EDIFACT AND XML CODESET reference number
     */
    @Column(name="CODE_SET", nullable = false)
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


    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(!(o instanceof FlightStatus)){
            return false;
        }
        FlightStatus status = (FlightStatus) o;
        //TODO should we add type argument here?
        if(status.getCode().equals(this.getCode())
                && status.getCodeSet().equals(this.getCodeSet())
                && status.getId().longValue() == this.getId().longValue()){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode(){
        int result = 13;
        int cofactor = 8;
        int codeElem = code == null ? 0 : code.hashCode();
        int codeSetElem = codeSet == null ? 0 : codeSet.hashCode();
        result = result * cofactor + codeElem;
        result = result * cofactor + codeSetElem;
        result = result * cofactor + Long.bitCount(getId());
        return result;
    }
}
