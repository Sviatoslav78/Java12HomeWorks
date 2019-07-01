package com.java12.HibernateHome.Entity.SkillEnum;


public enum ProgrammingLanguage {
    JAVA("java"),
    C_PLUS_PLUS("c++"),
    JAVA_SCRIPT("javaScript"),
    PYTHON("python"),
    C_SHARP("c#");

    public String getDescription() {
        return description;
    }

    private String description;

    ProgrammingLanguage(String description) {
        this.description = description;
    }
}
