package com.java12.HTTPhome.ConsoleControllers.PetLogic;

public  class CommandPetUtils {
    public PetCommands getCommand(String id) {
        if (id.trim().toLowerCase().contains("post") || id.trim().contains("1")) return PetCommands.POST;
        if (id.trim().toLowerCase().contains("delete") || id.trim().contains("2")) return PetCommands.DELETE;
        if(id.trim().toLowerCase().contains("update") || id.trim().contains("3")) return PetCommands.UPDATE;
        if(id.trim().toLowerCase().contains("get by id") || id.trim().contains("4")) return PetCommands.GET_BY_ID;
        if(id.trim().toLowerCase().contains("get by status") || id.trim().contains("5")) return PetCommands.GET_BY_STATUS;
        if(id.trim().toLowerCase().contains("upload") || id.trim().contains("6"))return PetCommands.UPLOAD_FILE;
        if(id.trim().toLowerCase().toLowerCase().contains("put") || id.trim().contains("7")) return PetCommands.PUT_UPDATE;
        if(id.trim().toLowerCase().contains("help")) return PetCommands.HELP;
        if(id.trim().toLowerCase().contains("exit")) return PetCommands.EXIT;
        if(id.trim().toLowerCase().contains("to") || id.contains("10")) return PetCommands.ORDER_CONSOLE;
        else return PetCommands.UNKNOWN;
    }
}
