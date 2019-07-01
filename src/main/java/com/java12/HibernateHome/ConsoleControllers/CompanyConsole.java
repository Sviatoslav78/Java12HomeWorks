package com.java12.HibernateHome.ConsoleControllers;

import com.java12.HibernateHome.Commands.CommandUtils;
import com.java12.HibernateHome.Commands.CommandsSpecial;
import com.java12.HibernateHome.Entity.Company;
import com.java12.HibernateHome.Entity.Developer;
import com.java12.HibernateHome.Entity.Project;
import com.java12.HibernateHome.Service.CompanyService;
import com.java12.HibernateHome.Service.DeveloperService;
import com.java12.HibernateHome.Service.ProjectService;

public class CompanyConsole implements ConsoleInterface {
    private CompanyService companyService = new CompanyService();

    @Override
    public void execute() {
        System.out.println("Let`s start with company console");
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
        System.out.println(companyService.getById(Long.parseLong(scanner.nextLine())));

    }

    @Override
    public void getByNameCommand() {
        System.out.println("Enter name of company: ");
        System.out.println(companyService.getByName(scanner.nextLine()));

    }

    @Override
    public void getAllCommand() {
        String answer = companyService.getAll();
        if (answer.isEmpty()) {
            System.out.println("Sorry, but there are nothing to show");
        } else {
            System.out.println(answer);
        }

    }

    @Override
    public void deleteNeIdCommand() {
        System.out.println("Enter id: ");
        System.out.println(companyService.deleteById(Long.parseLong(scanner.nextLine())));

    }


    @Override
    public void addNewRecordCommand() {
        System.out.println(companyService.createNew(enterInfo()));

    }

    @Override
    public void update() {
        System.out.println(companyService.update(enterInfo()));
    }

    private Company enterInfo() {
        Company company = new Company();
        System.out.println("Enter id of company: ");
        company.setId(Long.parseLong(scanner.nextLine()));
        System.out.println("Enter name of company: ");
        company.setCompanyName(scanner.nextLine());
        System.out.println("Enter id of TAX: ");
        company.setTaxId(Long.parseLong(scanner.nextLine()));


        System.out.println("Enter your developers: ");
        String line = "";
        Developer developer;
        DeveloperService developerService = new DeveloperService();
        while (!line.equalsIgnoreCase("stop")) {
            System.out.println("Enter id: ");
            line = scanner.nextLine();
            developer = developerService.getById(Long.parseLong(line));
            if (developer == null) {
                System.out.println("Developer not found");
            } else {
                company.addDeveloper(developer);
            }
        }

        System.out.println("Enter your projects: ");
        line = "";
        Project project;
        ProjectService projectService = new ProjectService();
        while (!line.equalsIgnoreCase("stop")) {
            System.out.println("Enter id: ");
            line = scanner.nextLine();
            project = projectService.getById(Long.parseLong(line));
            if (project == null) {
                System.out.println("Developer not found");
            } else {
                company.addProject(project);
            }
        }


        return company;
    }
}
