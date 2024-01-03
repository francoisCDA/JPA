package services;

import dao.ImplDAO;
import dao.TodoDAO;
import model.Task;

import java.util.List;

public class TodoService {

    private ImplDAO todoDao;

    public TodoService(){
        todoDao = new ImplDAO();
    }

    public void addTask(String task){
        todoDao.addTask(task);
    }

    public List<Task> getTasks() {
        return todoDao.getTasks();
    }

    public List<Task> getActiveTask(){
        return todoDao.getActiveTasks();
    }

    public List<Task> getCompletedTask(){
        return todoDao.getCompletedTask();
    }

    public boolean completTask(Long id){
        return todoDao.completTask(id);
    }

    public boolean removeTask(Long id){
        return todoDao.removeTask(id);
    }

    public void closeEmf(){
        todoDao.close();
    }

}
