package org.example;

import org.example.service.TodoService;

import java.util.Scanner;

public class Ihm {

    private TodoService todoService;

    private Scanner scanner;

    public Ihm(TodoService todoService, Scanner scanner) {
        this.todoService = todoService;
        this.scanner = scanner;
    }




}
