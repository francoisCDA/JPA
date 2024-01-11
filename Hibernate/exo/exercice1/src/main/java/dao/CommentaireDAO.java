package dao;

import models.Commentaire;
import models.Image;
import models.Produit;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class CommentaireDAO extends BaseDAO implements DAO<Commentaire> {
    @Override
    public void create(Commentaire obj) {
        update(obj);
    }

    @Override
    public Commentaire get(Long id) {
        session = factory.openSession();
        session.beginTransaction();

        try {
            Commentaire ret = session.get(Commentaire.class,id);
            session.getTransaction().commit();
            return ret;
        } catch (Exception e){
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Commentaire> getAll() {
        return null;
    }


    @Override
    public void update(Commentaire obj) {

        session = factory.openSession();
        session.beginTransaction();

        try {
            session.saveOrUpdate(obj);
            session.getTransaction().commit();
        } catch (Exception ignored){

        } finally {
            session.close();
        }
    }

    @Override
    public void remove(Long id) {
        session = factory.openSession();
        session.beginTransaction();

        try {
            Commentaire toRemove = session.load(Commentaire.class,id);
            session.delete(toRemove);
            session.getTransaction().commit();

        } catch (Exception ignored){

        }finally {
            session.close();
        }
    }


    public List<Commentaire> getByProdctId(Long idProdct){

        session = factory.openSession();
        session.beginTransaction();

        try {
            String sql = "select * from commentaire where product_id = :id";
            NativeQuery<Commentaire> query = session.createNativeQuery(sql, Commentaire.class);
            query.setParameter("id",idProdct);

            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }



}
