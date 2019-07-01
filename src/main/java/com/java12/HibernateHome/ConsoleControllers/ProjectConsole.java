package com.java12.HibernateHome.ConsoleControllers;

import com.java12.HibernateHome.Commands.CommandUtils;
import com.java12.HibernateHome.Commands.CommandsSpecial;
import com.java12.HibernateHome.Entity.Company;
import com.java12.HibernateHome.Entity.Customer;
import com.java12.HibernateHome.Entity.Developer;
import com.java12.HibernateHome.Entity.Project;
import com.java12.HibernateHome.Service.CompanyService;
import com.java12.HibernateHome.Service.CustomerService;
import com.java12.HibernateHome.Service.DeveloperService;
import com.java12.HibernateHome.Service.ProjectService;

public class ProjectConsole implements ConsoleInterface {
    private ProjectService projectService = new ProjectService();

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
        System.out.println("Enter your id: ");
        System.out.println(projectService.getById(Long.parseLong(scanner.nextLine())).toString());

    }

    @Override
    public void getByNameCommand() {
        System.out.println("Enter name of project: ");
        System.out.println(projectService.getByName(scanner.nextLine()));

    }

    @Override
    public void getAllCommand() {
        String answer = projectService.getAll();
        if (answer.isEmpty()) {
            System.out.println("Sorry, but there are nothing to show");
        } else {
            System.out.println(answer);
        }

    }

    @Override
    public void deleteNeIdCommand() {
        System.out.println("Enter id: ");
        System.out.println(projectService.deleteById(Long.parseLong(scanner.nextLine())));

    }

    private Project enterInfo() {
        Project project = new Project();
        System.out.println("Enter id of project: ");
        project.setId(Long.parseLong(scanner.nextLine()));
        System.out.println("Enter name of project: ");
        project.setName(scanner.nextLine());
        System.out.println("Enter date of your contract: ");
        project.setDate(scanner.nextLine());
        System.out.println("Enter id of your customer: ");
        CustomerService customerService = new CustomerService();
        Customer customer = customerService.getById(Long.parseLong(scanner.nextLine()));
        String line = "";
        while (customer == null && !line.equalsIgnoreCase("stop")) {
            System.out.println("Customer not found.\n Try again please.\nIf you don`t want to add customer enter stop");
            line = scanner.nextLine();
            if (!line.equalsIgnoreCase("stop")) {
                customer = customerService.getById(Long.parseLong(scanner.nextLine()));
            }
        }
        project.setCustomer(customer);

        System.out.println("Enter id of your company: ");
        CompanyService companyService = new CompanyService();
        Company company = companyService.getById(Long.parseLong(scanner.nextLine()));
        line = "";
        while (customer == null && !line.equalsIgnoreCase("stop")) {
            System.out.println("Company not found.\n Try again please.\nIf you don`t want to add company enter stop");
            line = scanner.nextLine();
            if (!line.equalsIgnoreCase("stop")) {
                company = companyService.getById(Long.parseLong(scanner.nextLine()));
            }
        }
        project.setCompany(company);

        System.out.println("Enter your developers: ");
        line = "";
        Developer developer;
        DeveloperService developerService = new DeveloperService();
        while (!line.equalsIgnoreCase("stop")) {
            System.out.println("Enter id: ");
            line = scanner.nextLine();
            developer = developerService.getById(Long.parseLong(line));
            if (developer == null) {
                System.out.println("Developer not found");
            } else {
                project.addDeveloper(developer);
            }
        }


        return project;
    }

    @Override
    public void addNewRecordCommand() {
        System.out.println(projectService.createNew(enterInfo()));

    }

    @Override
    public void update() {
        System.out.println(projectService.update(enterInfo()));
    }

}
