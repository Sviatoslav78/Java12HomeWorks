package com.java12.HibernateHome.ConsoleControllers;

import com.java12.HibernateHome.Commands.CommandUtils;
import com.java12.HibernateHome.Commands.CommandsSpecial;

import com.java12.HibernateHome.Entity.Customer;

import com.java12.HibernateHome.Entity.Project;

import com.java12.HibernateHome.Service.CustomerService;
import com.java12.HibernateHome.Service.DeveloperService;
import com.java12.HibernateHome.Service.ProjectService;

public class CustomerConsole implements ConsoleInterface {

    private CustomerService customerService = new CustomerService();

    @Override
    public void execute() {
        System.out.println("Let`s start with customer console");
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
        System.out.println(customerService.getById(Long.parseLong(scanner.nextLine())).toString());

    }

    @Override
    public void getByNameCommand() {
        System.out.println("Enter name of customer: ");
        System.out.println(customerService.getByName(scanner.nextLine()));

    }

    @Override
    public void getAllCommand() {
        String answer = customerService.getAll();
        if (answer.isEmpty()) {
            System.out.println("Sorry, but there are nothing to show");
        } else {
            System.out.println(answer);
        }

    }

    @Override
    public void deleteNeIdCommand() {
        System.out.println("Enter id: ");
        System.out.println(customerService.deleteById(Long.parseLong(scanner.nextLine())));

    }


    @Override
    public void addNewRecordCommand() {
        System.out.println(customerService.createNew(enterInfo()));
    }

    @Override
    public void update() {
        System.out.println(customerService.update(enterInfo()));
    }

    private Customer enterInfo() {
        Customer customer = new Customer();
        System.out.println("Enter id of customer: ");
        customer.setId(Long.parseLong(scanner.nextLine()));
        System.out.println("Enter name of customer: ");
        customer.setCustomerName(scanner.nextLine());
        System.out.println("Enter extra info about customer: ");
        customer.setExtraInfo(scanner.nextLine());


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
                customer.addProject(project);
            }
        }


        return customer;
    }
}
