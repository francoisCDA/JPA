package dao;

import ihm.UtilIHM;
import model.Categorie;
import model.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class TodoDAOImpl extends TodoDAO{
    public TodoDAOImpl(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public void addTask(Task tache) {
        em = _emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(tache);
      //  em.persist(tache.getInfoTache()); géré par la cascade

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Task getTask(Long id) {

       em = _emf.createEntityManager();
       em.getTransaction().begin();

       Task ret = em.find(Task.class,id);

       em.getTransaction().commit();
       em.close();

       return ret;

    }

    @Override
    public List<Task> getTasks() {

        List<Task> ret = null;

        em = _emf.createEntityManager();
        em.getTransaction().begin();

        ret = em.createQuery("select t from Task t",Task.class).getResultList();

        em.getTransaction().commit();
        em.close();

        return ret;
    }

    @Override
    public List<Task> getActiveTasks() {

        List<Task> ret = null;

        em = _emf.createEntityManager();
        em.getTransaction().begin();

        ret = em.createQuery("select t from Task t where complete = false ",Task.class).getResultList();

        em.getTransaction().commit();
        em.close();

        return ret;
    }

    @Override
    public List<Task> getCompletedTask() {

        List<Task> ret = null;

        em = _emf.createEntityManager();
        em.getTransaction().begin();

        ret = em.createQuery("select t from Task t where complete = true",Task.class).getResultList();

        em.getTransaction().commit();
        em.close();

        return ret;
    }


    @Override
    public boolean completTask(Long idTask) {

        em = _emf.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("select t from Task t where id = :id", Task.class);
        query.setParameter("id",idTask);

        Task completedTask = null;

        try {
            completedTask = (Task) query.getSingleResult();
        } catch (Exception ignored) {

        }


        if (completedTask == null) {
            em.getTransaction().rollback();
            em.close();
            return false;
        }

        completedTask.completed();

        em.getTransaction().commit();
        em.close();

        return true;
    }

    @Override
    public boolean removeTask(Long id) {

        em = _emf.createEntityManager();
        em.getTransaction().begin();

        boolean ret = false;

        try {
            Task task = em.find(Task.class,id);
          //  em.remove(task.getInfoTache()); géré par la cascade
            em.remove(task);
            em.getTransaction().commit();
            ret = true;

        } catch (EnumConstantNotPresentException e) {
            em.getTransaction().rollback();


        } finally {
            em.close();
        }

        return ret;
    }

    public boolean update(Task tache) {
        boolean ret = false;

        em = _emf.createEntityManager();
        em.getTransaction().begin();

        try {
            em.merge(tache);
            em.getTransaction().commit();
            ret = true;

        } catch (Exception e){
            em.getTransaction().rollback();
            UtilIHM.consoleError(e.toString());
        } finally {
            em.close();
        }

        return ret;

    }

    public List<Task> getTasksByCategorie(Categorie cat){
        List<Task> ret = null;

        em = _emf.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("select t from Task t join t.categories c where c.categorie = :cat");
        query.setParameter("cat",cat.getCategorie());

        ret = query.getResultList();
        em.getTransaction().commit();
        em.close();

        return ret;

    }


    public void close(){
        _emf.close();
    }
}
