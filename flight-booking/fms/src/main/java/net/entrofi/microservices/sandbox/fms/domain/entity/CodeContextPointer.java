package net.entrofi.microservices.sandbox.fms.domain.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * DOC documentation:type_definition Please provide <b><u>detailed</u></b> class definition.
 *
 * @author hcomak
 * @created 15 Apr 2015
 */
@Embeddable
@Access(AccessType.PROPERTY)
public class CodeContextPointer {

    public static final class CodeContextAttrNames{
        public static final String CODE = "code";
        public static final String CODE_CONTEXT = "codeContext";
    }

    public enum CodeContext {
        ICAO, IATA
    }
    @NotNull
    private String code;

    @NotNull
    private String codeContext;

    public CodeContextPointer() {

    }

    public CodeContextPointer(@NotNull String code, @NotNull String codeContext){
        this.code = code;
        this.codeContext = codeContext;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String airportCode) {
        this.code = airportCode;
    }

    public String getCodeContext() {
        return codeContext;
    }

    public void setCodeContext(String codeContext) {
        this.codeContext = codeContext;
    }

    @Override
    public String toString() {
        return "code: " + code + ", context: " + codeContext;
    }

    @Override
    public boolean equals(Object obj) {
        CodeContextPointer that = (CodeContextPointer) obj;
        if(obj != null) {
            return code.equals((that.getCode())) && codeContext.equals(that.getCodeContext());
        } else {
            return false;
        }

    }
}
