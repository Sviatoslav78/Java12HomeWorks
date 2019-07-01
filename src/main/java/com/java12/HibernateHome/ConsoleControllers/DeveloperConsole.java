package com.java12.HibernateHome.ConsoleControllers;

import com.java12.HibernateHome.Commands.CommandUtils;
import com.java12.HibernateHome.Commands.CommandsSpecial;
import com.java12.HibernateHome.Service.DeveloperService;

public class DeveloperConsole implements ConsoleInterface {
    private DeveloperService developerService = new DeveloperService();

    @Override
    public void execute() {
        System.out.println("Let`s start with project console");
        boolean isAlive = true;
        while (isAlive) {
            System.out.println("Enter your command: ");
            CommandsSpecial command = CommandUtils.COMMAND_UTILS.getSpecialClass(scanner.nextLine());
            switch (command) {
                case GET_BY_ID:
                    getByIdCommand();
                    break;
                case UNKNOWN:
                    System.out.println("Unknown command.\nTry again please");
                    break;
                case EXIT:
                    isAlive = false;
                    System.out.println("Thanks for work!");
                    break;
                case HELP:
                    helpCommand();
                    break;
                case GET_ALL:
                    getAllCommand();
                case TO_MAIN:
                    isAlive = false;
                    break;
                case GET_BY_NAME:
                    getByNameCommand();
                    break;
                case DElETE_BY_ID:
                    deleteNeIdCommand();
                    break;
                case CREATE_NEW_RECORD:
                    addNewRecordCommand();
                    break;
                case UPDATE_EXIST_RECORD:
                    update();
                    break;

            }

        }


    }

    @Override
    public void helpCommand() {

    }

    @Override
    public void getByIdCommand() {

    }

    @Override
    public void getByNameCommand() {

    }

    @Override
    public void getAllCommand() {

    }

    @Override
    public void deleteNeIdCommand() {

    }

    @Override
    public void addNewRecordCommand() {

    }

    @Override
    public void update() {

    }
}
