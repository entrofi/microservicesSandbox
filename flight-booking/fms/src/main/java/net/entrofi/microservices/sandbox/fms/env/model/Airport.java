
package net.entrofi.microservices.sandbox.fms.env.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.entrofi.microservices.sandbox.fms.domain.entity.CodeContextPointer;
import org.springframework.util.StringUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Airport {

    private CodeContextPointer codeContextPointer;

    private String code;

    public Airport() {
        codeContextPointer = new CodeContextPointer();
        codeContextPointer.setCodeContext("IATA");
    }

    public void setCode(String code) {
        if(StringUtils.isEmpty(this.codeContextPointer.getCode())) {
            this.code = code;
            this.codeContextPointer.setCode(code);
        }
    }

    public String getCode() {

        return this.codeContextPointer.getCode();
    }

    public String getCodeContext() {
        return this.codeContextPointer.getCodeContext();
    }

    public CodeContextPointer getCodeContextPointer() {
        final CodeContextPointer codeContextPointer = new CodeContextPointer(this.codeContextPointer.getCode(),
                this.codeContextPointer.getCodeContext());
        return codeContextPointer;
    }
}
