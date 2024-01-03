package dao;

import model.Task;

import javax.persistence.Query;
import java.util.List;

public class ImplDAO extends TodoDAO{
    @Override
    public void addTask(String tache) {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        Task newTache = new Task(tache);

        em.persist(newTache);

        em.getTransaction().commit();
        em.close();

    }

    @Override
    public List<Task> getTasks() {

        List<Task> ret = null;

        em = emf.createEntityManager();
        em.getTransaction().begin();

        ret = em.createQuery("select t from Task t",Task.class).getResultList();

        em.close();

        return ret;
    }

    @Override
    public List<Task> getActiveTasks() {

        List<Task> ret = null;

        em = emf.createEntityManager();
        em.getTransaction().begin();

        ret = em.createQuery("select t from Task t where complete = false ",Task.class).getResultList();

        em.close();

        return ret;
    }

    @Override
    public List<Task> getCompletedTask() {

        List<Task> ret = null;

        em = emf.createEntityManager();
        em.getTransaction().begin();

        ret = em.createQuery("select t from Task t where complete = true",Task.class).getResultList();

        em.close();

        return ret;
    }


    @Override
    public boolean completTask(Long idTask) {

        em = emf.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("select t from Task t where id = :id", Task.class);
        query.setParameter("id",idTask);

        Task competedTask = (Task) query.getSingleResult();

        competedTask.completed();

        em.getTransaction().commit();
        em.close();

        return true;

    }

    @Override
    public boolean removeTask(Long id) {

        em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            Task task = em.getReference(Task.class,id);
            em.remove(task);
            em.getTransaction().commit();
            return true;
        } catch (EnumConstantNotPresentException e) {
            em.close();
            return false;
        }

    }
}
