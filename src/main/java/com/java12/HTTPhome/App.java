package com.java12.HTTPhome;

import com.java12.HTTPhome.ConsoleControllers.OrderLogic.OrderConsole;
import com.java12.HTTPhome.ConsoleControllers.PetLogic.PetConsole;

import java.io.IOException;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("You can work with:\n1. " +
                "Pet console;\n2. Order console.\nEnter 1 to use pet-console and 2 to order-console");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while (true) {
            if (line.equalsIgnoreCase("1")) {
                new PetConsole().execute();
                break;
            }
            if (line.equals("2")) {
                new OrderConsole().execute();
                break;
            } else {
                System.out.println("Unknown command");
            }
        }


    }

}
