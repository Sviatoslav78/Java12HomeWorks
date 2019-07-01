package com.java12.HibernateHome.ConsoleControllers;

import com.java12.HibernateHome.Commands.CommandUtils;
import com.java12.HibernateHome.Commands.CommandsSpecial;
import com.java12.HibernateHome.Entity.Skill;
import com.java12.HibernateHome.Service.SkillService;

public class SkillConsole implements ConsoleInterface {
    private SkillService skillService = new SkillService();

    @Override
    public void execute() {
        System.out.println("Let`s start with skill console");
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
        for (CommandsSpecial comm : CommandsSpecial.values()) {
            if (!comm.getDescription().equals("unknown")) {
                System.out.println(comm.getDescription());
            }
        }
    }

    @Override
    public void getByIdCommand() {
        System.out.println("Enter your id: ");
        System.out.println(skillService.getById(Long.parseLong(scanner.nextLine())).toString());

    }

    @Override
    public void getByNameCommand() {
        System.out.println("Enter name of skill: ");
        System.out.println(skillService.getByName(scanner.nextLine()));

    }

    @Override
    public void getAllCommand() {
        String answer = skillService.getAll();
        if (answer.isEmpty()) {
            System.out.println("Sorry, but there are nothing to show");
        } else {
            System.out.println(skillService.getAll());
        }

    }

    @Override
    public void deleteNeIdCommand() {
        System.out.println("Enter id: ");
        System.out.println(skillService.deleteById(Long.parseLong(scanner.nextLine())));

    }

    @Override
    public void addNewRecordCommand() {
        System.out.println(skillService.createNew(new Skill()));
    }

    @Override
    public void update() {
        System.out.println(skillService.update(new Skill()));

    }
}
