package net.entrofi.microservices.sandbox.booking.env.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.entrofi.microservices.sandbox.booking.domain.model.Inventory;

public final class OutboundInventoryUpdateMessage {

    @JsonUnwrapped
    private final Inventory inventory;

    private final UpdateType type;


    public OutboundInventoryUpdateMessage(@JsonProperty("inventory") final Inventory inventory,
                                          @JsonProperty("type") final UpdateType type) {
        this.inventory = inventory;
        this.type = type;
    }


    public Inventory getInventory() {
        return inventory;
    }

    public UpdateType getType() {
        return type;
    }

    public enum UpdateType {
        FARE, BOOKING, ALL
    }
}
