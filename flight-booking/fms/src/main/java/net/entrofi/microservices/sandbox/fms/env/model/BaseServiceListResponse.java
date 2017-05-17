
package net.entrofi.microservices.sandbox.fms.env.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class BaseServiceListResponse {

    @JsonProperty("_embedded")
    private Embedded embedded;

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }
}
