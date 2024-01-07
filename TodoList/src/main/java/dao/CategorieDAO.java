package dao;

import ihm.UtilIHM;
import model.Categorie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class CategorieDAO implements InterfaceDAO<Categorie> {

    private EntityManagerFactory _emf;

   // private EntityManager em;

    public CategorieDAO(EntityManagerFactory emf){
        _emf = emf;
    }


    @Override
    public List<Categorie> getAll() {
        List<Categorie> ret = null;

        EntityManager em = _emf.createEntityManager();
        em.getTransaction().begin();

        ret = em.createQuery("select c from Categorie c", Categorie.class).getResultList();

        em.getTransaction().commit();
        em.close();

        return ret;
    }

    @Override
    public boolean save(Categorie cat) {
        EntityManager em = _emf.createEntityManager();
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

    @Override
    public boolean rm(Long id) {

        EntityManager em = _emf.createEntityManager();
        em.getTransaction().begin();

        boolean ret = false;

        try {
            Categorie catToRm = em.find(Categorie.class,id);
            em.remove(catToRm);
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
        EntityManager em = _emf.createEntityManager();
        em.getTransaction().begin();

        Categorie ret = null;

        try {

            Query query = em.createQuery("select c from Categorie c where categorie = :cat", Categorie.class);
            query.setParameter("cat",cat);

             ret = (Categorie) query.getSingleResult();
            em.getTransaction().commit();
        } catch (NoResultException ignored){
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return ret;

    }

    public void close(){
        _emf.close();
    }

}
