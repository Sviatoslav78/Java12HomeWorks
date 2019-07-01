package com.java12.HibernateHome.Commands;

public enum CommandUtils {
    COMMAND_UTILS;
    public CommandsBasic getBasicClass(String command){
        if(command.equalsIgnoreCase("developer-console") || command.contains("1")) return CommandsBasic.DEVELOPER;
        if(command.equalsIgnoreCase( "customer-console") || command.contains("2")) return CommandsBasic.CUSTOMER;
        if(command.equalsIgnoreCase( "project-console") || command.contains("3")) return CommandsBasic.PROJECT;
        if(command.equalsIgnoreCase( "skill-console") || command.contains("4")) return CommandsBasic.SKILL;
        if(command.equalsIgnoreCase( "company-console") || command.contains("5")) return CommandsBasic.COMPANY;
        if(command.equalsIgnoreCase( "help") || command.contains("6")) return CommandsBasic.HELP;
        if(command.equalsIgnoreCase( "exit") || command.contains("7")) return CommandsBasic.EXIT;
        else return CommandsBasic.UNKNOWN;
    }

    public CommandsSpecial getSpecialClass(String command){
        if(command.equalsIgnoreCase("create record") || command.equalsIgnoreCase("1"))
            return CommandsSpecial.CREATE_NEW_RECORD;
        if(command.equalsIgnoreCase("update exist record") || command.equalsIgnoreCase("2"))
            return CommandsSpecial.UPDATE_EXIST_RECORD;
        if(command.equalsIgnoreCase("get all") || command.equalsIgnoreCase("3"))
            return CommandsSpecial.GET_ALL;
        if(command.equalsIgnoreCase("delete by id") || command.equalsIgnoreCase("4"))
            return CommandsSpecial.DElETE_BY_ID;
        if(command.equalsIgnoreCase("get by id") || command.equalsIgnoreCase("5"))
            return CommandsSpecial.GET_BY_ID;
        if(command.equalsIgnoreCase("get by name") || command.equalsIgnoreCase("6"))
            return CommandsSpecial.GET_BY_NAME;
        if(command.equalsIgnoreCase("to main menu") || command.equalsIgnoreCase("7"))
            return CommandsSpecial.TO_MAIN;
        if(command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("8"))
            return CommandsSpecial.EXIT;
        if(command.equalsIgnoreCase("help") || command.equalsIgnoreCase("9"))
            return CommandsSpecial.HELP;
        else  return CommandsSpecial.UNKNOWN;
    }
}
