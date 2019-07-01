package com.java12.HibernateHome;


import com.java12.HibernateHome.Commands.CommandUtils;
import com.java12.HibernateHome.Commands.CommandsBasic;
import com.java12.HibernateHome.ConsoleControllers.*;

import java.util.Scanner;

public class ConsoleApp {
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, user. Let`s start");
        helpCommand();
        boolean isAlive = true;
        while (isAlive) {
            System.out.println("Enter your command: ");
            CommandsBasic command = CommandUtils.COMMAND_UTILS.getBasicClass(scanner.nextLine());
            switch (command) {
                case HELP:
                    helpCommand();
                    break;
                case EXIT:
                    System.out.println("Thanks)");
                    isAlive = false;
                    break;
                case UNKNOWN:
                    System.out.println("Unknown command, try again");
                    break;
                case PROJECT:
                    new ProjectConsole().execute();
                    break;
                case SKILL:
                    new SkillConsole().execute();
                    break;
                case CUSTOMER:
                    new CustomerConsole().execute();
                    break;
                case COMPANY:
                    new CompanyConsole().execute();
                    break;
                case DEVELOPER:
                    new DeveloperConsole().execute();
                    break;
            }
        }


    }


    public void helpCommand() {
        for (CommandsBasic comm : CommandsBasic.values()) {
            if (!comm.getDescription().equals("unknown")) {
                System.out.println(comm.getDescription());
            }
        }
    }

}
