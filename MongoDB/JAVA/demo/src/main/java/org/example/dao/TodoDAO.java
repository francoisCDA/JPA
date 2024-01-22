package org.example.dao.impl;

import org.example.model.TodoItem;

import java.util.List;

public interface TodoDAO {

    void addTodo(TodoItem todoItem);


    List<TodoItem> getAllTodos();


}
