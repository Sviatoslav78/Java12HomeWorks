package com.java12.HibernateHome.Commands;

public enum CommandsBasic {
    DEVELOPER("1. developer-console"),
    CUSTOMER("2. customer-console"),
    PROJECT("3. project-console"),
    SKILL("4. skill-console"),
    COMPANY("5. company-console"),
    HELP("6. help"),
    EXIT("7. exit"),
    UNKNOWN("unknown");

    public String getDescription() {
        return description;
    }

    private String description;

    CommandsBasic(String description) {
        this.description = description;
    }
}
