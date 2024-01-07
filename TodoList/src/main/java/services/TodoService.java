package services;

import dao.TodoDAOImpl;
import model.*;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.List;

public class TodoService {

    private TodoDAOImpl todoDao;

    public TodoService(EntityManagerFactory emf){
        todoDao = new TodoDAOImpl(emf);
    }

    public boolean addTask(String tache, String descr, LocalDate expire, Priorite priorite, Utilisateur user, Categorie categorie){

        if (tache.length() == 0 ) return false;


        Task newtache = new Task(tache,user);

        TaskInfo infoTache = new TaskInfo(descr,expire,priorite);
        newtache.setInfoTache(infoTache);

        todoDao.addTask(newtache);

        newtache.addCategorie(categorie);

        todoDao.update(newtache);


        return true;
    }

    public boolean update(Task tache){
        return todoDao.update(tache);
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

        Task tacheToRemove = todoDao.getTask(id);

        tacheToRemove.clearCategories();

        todoDao.update(tacheToRemove);



        return todoDao.removeTask(tacheToRemove.getId());
    }

    public void closeEmf(){
        todoDao.close();
    }

    public List<Task> getTasksByCategorie(Categorie cat) {

        return todoDao.getTasksByCategorie(cat);

    }

    public int nbTaskByCat(Categorie cat){
        List<Task> taskByCat = todoDao.getTasksByCategorie(cat);

        return taskByCat.size();
    }

    public boolean toogleCat(Task tache, Categorie categorie) {

        for (Categorie c: tache.getCategories()) {
            if (c.getCategorie().equals(categorie.getCategorie())){
                tache.rmCategorie(c);
                return todoDao.update(tache);
            }
        }

        tache.addCategorie(categorie);
        return todoDao.update(tache);

    }

}
