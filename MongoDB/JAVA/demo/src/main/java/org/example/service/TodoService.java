package org.example.service;

import org.example.dao.impl.TodoDAO;
import org.example.model.TodoItem;

import java.util.List;

public class TodoService {

    private TodoDAO todoDAO;

    public TodoService(TodoDAO todoDAO) {
        this.todoDAO = todoDAO;
    }

    public void addTodo(TodoItem todoItem){
        todoDAO.addTodo(todoItem);
    }

    public List<TodoItem> getAllTodos(){
        return todoDAO.getAllTodos();

    }


}
