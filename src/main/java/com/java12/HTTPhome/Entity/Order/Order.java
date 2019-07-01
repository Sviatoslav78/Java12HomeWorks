package com.java12.HTTPhome.Entity.Order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
    private long id;
    private long petId;
    private long quantity;
    private String shipDate;
    private OrderStatus status;
    private boolean complete;

    @Override
    public String toString() {
        return "Order: " +
                "ID: " + id + ";\n" +
                "PET-ID: " + petId + ";\n" +
                "QUANTITY: " + quantity + ";\n" +
                "SHIP-DATE: " + shipDate + ";\n" +
                "STATUS: " + status + ";\n" +
                "COMPLETE: " + complete + ";\n";
    }
}
