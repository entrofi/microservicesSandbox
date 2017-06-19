package net.entrofi.microservices.sandbox.booking.env.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.entrofi.microservices.sandbox.booking.domain.model.Inventory;

public final class OutboundInventoryUpdateMessage {

    @JsonUnwrapped
    private final Inventory inventory;


    public OutboundInventoryUpdateMessage(@JsonProperty("inventory") final Inventory inventory){
        this.inventory = inventory;
    }


    public Inventory getInventory() {
        return inventory;
    }
}
