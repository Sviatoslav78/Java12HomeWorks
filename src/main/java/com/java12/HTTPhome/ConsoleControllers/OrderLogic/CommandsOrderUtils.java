package com.java12.HTTPhome.ConsoleControllers.OrderLogic;



public class CommandsOrderUtils {
    public OrderCommands getCommand(String id){
        if(id.trim().toLowerCase().contains("add") || id.contains("1")) return OrderCommands.ADD;
        if(id.trim().toLowerCase().contains("get by id") || id.contains("2")) return OrderCommands.GET_BY_ID;
        if(id.trim().toLowerCase().contains("get inventory") || id.contains("3")) return OrderCommands.GET_INVANTORY;
        if(id.toLowerCase().trim().contains("delete") || id.contains("4")) return OrderCommands.DELETE;
        if(id.trim().toLowerCase().contains("help") || id.contains("5")) return OrderCommands.HELP;
        if(id.trim().toLowerCase().contains("exit") || id.contains("6")) return OrderCommands.EXIT;
        if(id.trim().toLowerCase().contains("to") || id.contains("7")) return  OrderCommands.PET_CONSOLE;
        else return OrderCommands.UNKNOWN;
    }


}
