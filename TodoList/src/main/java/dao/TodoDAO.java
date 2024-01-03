package dao;

import model.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public abstract class TodoDAO {

    protected EntityManagerFactory emf;
    protected EntityManager em;
   // protected EntityTransaction transaction;


    public TodoDAO(){
        emf = Persistence.createEntityManagerFactory("todoList");
    }

    public abstract void addTask(Task tache);

    public abstract Task getTask(Long id);

    public abstract List<Task> getTasks();

    public abstract List<Task> getActiveTasks();

    public abstract List<Task> getCompletedTask();

    public abstract boolean completTask(Long id);

    public abstract boolean removeTask(Long id);


}
