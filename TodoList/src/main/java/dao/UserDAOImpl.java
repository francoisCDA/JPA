package dao;

import ihm.UtilIHM;
import model.Task;
import model.Utilisateur;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UserDAOImpl extends UserDAO {

    public UserDAOImpl(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public List<Utilisateur> getUsers() {
        List<Utilisateur> ret = null;

        EntityManager em = _emf.createEntityManager();
        em.getTransaction().begin();

        ret = em.createQuery("select u from Utilisateur u",Utilisateur.class).getResultList();

      //  em.getTransaction().commit();
        em.close();

        return ret;
    }

    @Override
    public Utilisateur getUserById(Long id) {

        em = _emf.createEntityManager();
        em.getTransaction().begin();

        Utilisateur ret = null;

        try {
            ret = em.find(Utilisateur.class,id);
        } catch (Exception ignored) {

        } finally {
            em.close();
        }

        return ret;
    }

    @Override
    public boolean addUser(Utilisateur user) {

        boolean ret = false;

        em = _emf.createEntityManager();
        em.getTransaction().begin();

        try {
            em.persist(user);
            em.getTransaction().commit();
            ret = true;
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return ret;

    }

    public void remove(Long userId) {

        em = _emf.createEntityManager();
        em.getTransaction().begin();

        Utilisateur userToRm = em.getReference(Utilisateur.class, userId);

        em.remove(userToRm);
        em.getTransaction().commit();

        em.close();

    }

    public boolean removeTaskToUser(Task tache) {

        em = _emf.createEntityManager();
        em.getTransaction().begin();

        Utilisateur user = em.find(Utilisateur.class,tache.getUtilisateur().getId());

        boolean ret = user.rmTaskById(tache.getId());

        UtilIHM.consoleConfirm("nombre de taches restantes : "+ user.getTodoList().size() );

        em.getTransaction().commit();
        em.close();

        return ret;
    }

    public void close(){
        _emf.close();
    }

}
