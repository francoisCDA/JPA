package services;

import dao.TodoDAOImpl;
import model.Priorite;
import model.Task;
import model.TaskInfo;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.List;

public class TodoService {

    private TodoDAOImpl todoDao;

    public TodoService(EntityManagerFactory emf){
        todoDao = new TodoDAOImpl(emf);
    }

    public boolean addTask(String tache, String description, LocalDate expir, Priorite priorite){

        if (tache.length() == 0 ) return false;


        Task newtache = new Task(tache);
        TaskInfo infoTache = new TaskInfo(description,expir,priorite);

        newtache.setInfoTache(infoTache);

        todoDao.addTask(newtache);

        return true;
    }

    public Task getTask(Long id) { return todoDao.getTask(id); }

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
