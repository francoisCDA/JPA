package dao;

import model.Categorie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class CategorieDAO implements InterfaceDAO<Categorie> {

    private final EntityManagerFactory _emf;

    private EntityManager em;

    public CategorieDAO(EntityManagerFactory emf){
        _emf = emf;
    }


    @Override
    public List<Categorie> getAll() {
        List<Categorie> ret = null;

        em = _emf.createEntityManager();
        em.getTransaction().begin();

        ret = em.createQuery("select c from Categorie c", Categorie.class).getResultList();

        em.close();

        return ret;
    }

    @Override
    public boolean save(Categorie cat) {
        em = _emf.createEntityManager();
        em.getTransaction().begin();

        boolean ret = false;

        try {
            em.persist(cat);
            em.getTransaction().commit();
            ret = true;
        } catch (Exception ignored){
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return ret;
    }


    public Categorie get(String cat){
        em = _emf.createEntityManager();
        em.getTransaction().begin();

        Categorie ret = null;

        Query query = em.createQuery("select c from Categorie c where categorie = :cat", Categorie.class);
        query.setParameter("cat",cat);

        try {
             ret = (Categorie) query.getSingleResult();

        } catch (NoResultException ignored){

        } finally {
            em.close();
        }

        return ret;

    }

}
