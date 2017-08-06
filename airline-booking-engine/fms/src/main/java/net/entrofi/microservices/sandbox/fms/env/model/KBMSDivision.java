
package net.entrofi.microservices.sandbox.fms.env.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KBMSDivision {

    private final String code;

    private final String name;

    private final String type;

    @JsonCreator
    public KBMSDivision(@JsonProperty("code") String code,
                        @JsonProperty("name") String name,
                        @JsonProperty("type") String type) {
        this.code = code;
        this.name = name;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
