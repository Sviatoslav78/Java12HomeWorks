package com.java12.Entity.Pet;


public enum PetStatus {
    available("Pet is available"),
    pending("Pet is pending"),
    sold("Pet is sold");
    private String description;

    PetStatus(String description) {
        this.description = description;
    }
}
