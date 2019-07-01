package com.java12.HibernateHome.Commands;

public enum CommandsSpecial {
    CREATE_NEW_RECORD("1. create record"),
    UPDATE_EXIST_RECORD("2. update exist record"),
    GET_ALL("3. get all"),
    DElETE_BY_ID("4. delete by id"),
    GET_BY_ID("5. get by id"),
    GET_BY_NAME("6. get by name"),
    TO_MAIN("7. to main menu"),
    EXIT("8. exit"),
    HELP("9. help"),
    UNKNOWN("unknown");

    public String getDescription() {
        return description;
    }

    private String description;

    CommandsSpecial(String description) {
        this.description = description;
    }

}
