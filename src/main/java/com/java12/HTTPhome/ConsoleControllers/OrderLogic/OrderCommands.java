package com.java12.HTTPhome.ConsoleControllers.OrderLogic;

public enum OrderCommands {
    ADD("1. add new order"),
    GET_BY_ID("2. get order by id"),
    GET_INVANTORY("3. get inventory"),
    DELETE("4. delete"),
    UNKNOWN("unknown"),
    HELP("5. help"),
    EXIT("6. exit"),
    PET_CONSOLE("7. to pet console");

    public String getDescription() {
        return description;
    }

    private String description;

    OrderCommands(String description) {
        this.description = description;
    }
}
