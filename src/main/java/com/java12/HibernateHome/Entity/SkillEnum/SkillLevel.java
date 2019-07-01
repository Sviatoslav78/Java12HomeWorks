package com.java12.HibernateHome.Entity.SkillEnum;

public enum SkillLevel {
    JUNIOR("junior"),
    MIDDLE("middle"),
    SENIOR("senior");

    private String decription;

    SkillLevel(String decription) {
        this.decription = decription;
    }
}
