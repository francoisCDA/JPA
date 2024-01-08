package DAO;

import model.Agence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class AgenceDAO implements DAO<Agence> {

    private EntityManagerFactory _emf;

    public AgenceDAO(EntityManagerFactory emf){
        _emf = emf;
    }

    @Override
    public List<Agence> getAll() {

        EntityManager em = _emf.createEntityManager();
        em.getTransaction().begin();

        List<Agence> ret = em.createQuery("select a from Agence a", Agence.class).getResultList();

        em.close();

        return ret;

    }

    @Override
    public Agence getById(Long id) {

        EntityManager em = _emf.createEntityManager();
        em.getTransaction().begin();

        Agence ret = em.getReference(Agence.class,id);

        em.close();

        return ret;

    }

    @Override
    public Long save(Agence obj) {

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
    public boolean update(Agence obj) {
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
            Agence agence = getById(id);
            em.merge(agence);
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
