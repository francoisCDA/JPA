package DAO;

import model.Agence;
import model.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ClientDAO implements DAO<Client> {

    private EntityManagerFactory _emf;

    public ClientDAO(EntityManagerFactory emf){
        _emf = emf;
    }

    @Override
    public List<Client> getAll() {
        EntityManager em = _emf.createEntityManager();
        em.getTransaction().begin();

        List<Client> ret = em.createQuery("select c from Client c", Client.class).getResultList();

        em.close();

        return ret;
    }

    @Override
    public Client getById(Long id) {

        EntityManager em = _emf.createEntityManager();
        em.getTransaction().begin();

        Client ret = em.getReference(Client.class,id);

        em.close();

        return ret;

    }

    @Override
    public Long save(Client obj) {
        EntityManager em = _emf.createEntityManager();
        em.getTransaction().begin();

        Long ret = 0L;

        try {
            em.persist(obj);
            em.getTransaction().commit();
            ret = obj.getId();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return ret;

    }

    @Override
    public boolean update(Client obj) {
        EntityManager em = _emf.createEntityManager();
        em.getTransaction().begin();

        boolean ret = false;

        try {
            em.merge(obj);
            em.getTransaction().commit();
            ret = true;
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return true;
    }

    @Override
    public boolean remove(Long id) {
        EntityManager em = _emf.createEntityManager();
        em.getTransaction().begin();

        boolean ret = false;

        try {
            Client client = getById(id);
            em.merge(client);
            em.getTransaction().commit();
            ret = true;
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return true;
    }
}
