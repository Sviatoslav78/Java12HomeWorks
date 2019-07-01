package com.java12.HibernateHome.ConsoleControllers;

import com.java12.HibernateHome.Commands.CommandUtils;
import com.java12.HibernateHome.Commands.CommandsSpecial;
import com.java12.HibernateHome.Entity.*;
import com.java12.HibernateHome.Service.*;

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
        for (CommandsSpecial comm : CommandsSpecial.values()) {
            if (!comm.getDescription().equals("unknown")) {
                System.out.println(comm.getDescription());
            }
        }
    }

    @Override
    public void getByIdCommand() {
        System.out.println("Enter id: ");
        System.out.println(developerService.getById(Long.parseLong(scanner.nextLine())));
    }

    @Override
    public void getByNameCommand() {
        System.out.println("Enter name:\nExample FirstName LastName ");
        System.out.println(developerService.getByName(scanner.nextLine()));


    }

    @Override
    public void getAllCommand() {
        String answer = developerService.getAll();
        if (answer.isEmpty()) {
            System.out.println("Sorry, but there are nothing to show");
        } else {
            System.out.println(answer);
        }


    }

    @Override
    public void deleteNeIdCommand() {
        System.out.println("Enter id: ");
        System.out.println(developerService.deleteById(Long.parseLong(scanner.nextLine())));

    }

    @Override
    public void addNewRecordCommand() {


    }

    @Override
    public void update() {

    }

    private Developer enterInfo() {
        Developer developer = new Developer();
        System.out.println("Enter id of developer: ");
        developer.setId(Long.parseLong(scanner.nextLine()));
        System.out.println("Enter  first name: ");
        developer.setFirstName(scanner.nextLine());

        System.out.println("Enter last name: ");
        developer.setLastName(scanner.nextLine());

        System.out.println("Enter salary of developer: ");
        developer.setSalary(Long.parseLong(scanner.nextLine()));

        System.out.println("Enter your projects: ");
        String line = "";
        Project project;
        ProjectService projectService = new ProjectService();
        while (!line.equalsIgnoreCase("stop")) {
            System.out.println("Enter id: ");
            line = scanner.nextLine();
            project = projectService.getById(Long.parseLong(scanner.nextLine()));
            if (project == null) {
                System.out.println("Developer not found");
            } else {
                developer.addProject(project);
            }
        }

        System.out.println("Enter your skills: ");
        line = "";
        Skill skill;
        SkillService skillService = new SkillService();
        while (!line.equalsIgnoreCase("stop")) {
            System.out.println("Enter id: ");
            line = scanner.nextLine();
            skill = skillService.getById(Long.parseLong(scanner.nextLine()));
            if (skill == null) {
                System.out.println("Developer not found");
            } else {
                developer.addSkill(skill);
            }
        }



        return developer;
    }
}
