package net.entrofi.microservices.sandbox.fms.env.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


public class Embedded {
    //TODO this should be generic
    @JsonProperty("airports")
    private List<Airport> list = new ArrayList<>();

    public List<Airport> getList() {
        return list;
    }

    public void setList(List<Airport> list) {
        this.list = list;
    }
}