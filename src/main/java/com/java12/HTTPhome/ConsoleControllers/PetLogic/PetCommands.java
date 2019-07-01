package com.java12.HTTPhome.ConsoleControllers.PetLogic;

public enum PetCommands {
    POST("1. post(create new pet)"),
    DELETE("2. delete"),
    UPDATE("3. update pet"),
    GET_BY_ID("4. get by id"),
    GET_BY_STATUS("5. get by status"),
    UPLOAD_FILE("6. upload file"),
    PUT_UPDATE("7. put (update exist pet"),
    UNKNOWN("unknown"),
    HELP("8. help"),
    EXIT("9. exit"),
    ORDER_CONSOLE("10. to order console");

    private String description;

    PetCommands(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
