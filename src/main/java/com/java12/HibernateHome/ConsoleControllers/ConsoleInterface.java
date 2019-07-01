package com.java12.HibernateHome.ConsoleControllers;

import java.util.Scanner;

public interface ConsoleInterface {
    Scanner scanner = new Scanner(System.in);
    void execute();

    void helpCommand();

    void getByIdCommand();

    void getByNameCommand();

    void getAllCommand();

    void deleteNeIdCommand();

    void addNewRecordCommand();

    void update();
}
