package DAO;

import model.Agence;
import model.Compte;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class CompteDAO implements DAO<Compte> {

    private EntityManagerFactory _emf;

    public CompteDAO(EntityManagerFactory emf){
        _emf = emf;
    }

    @Override
    public List<Compte> getAll() {
        EntityManager em = _emf.createEntityManager();
        em.getTransaction().begin();

        List<Compte> ret = em.createQuery("select c from Compte c", Compte.class).getResultList();

        em.close();

        return ret;
    }

    @Override
    public Compte getById(Long id) {

        EntityManager em = _emf.createEntityManager();
        em.getTransaction().begin();

        Compte ret = em.getReference(Compte.class,id);

        em.close();

        return ret;

    }

    @Override
    public Long save(Compte obj) {
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
    public boolean update(Compte obj) {
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
            Compte compte = getById(id);
            em.merge(compte);
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
