package com.java12;

import com.google.gson.Gson;

import java.util.Scanner;

public interface ConsoleInterface {
    Gson jsonParser = new Gson();
    Scanner scanner = new Scanner(System.in);

    void execute();
}
