package dao;

import models.Commentaire;
import models.Image;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class ImageDAO extends BaseDAO implements DAO<Image> {
    @Override
    public void create(Image obj) {
        update(obj);
    }

    @Override
    public Image get(Long id) {
      session = factory.openSession();
       session.beginTransaction();

       try {
           Image img = session.get(Image.class,id);
           session.getTransaction().commit();
           return img;
       } catch (Exception ignored) {
           session.getTransaction().rollback();
           return null;
       } finally {
           session.close();
       }


    }

    @Override
    public List<Image> getAll() {
        return null;
    }

    @Override
    public void update(Image obj) {
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
            Image toRemove = session.load(Image.class,id);
            session.delete(toRemove);
            session.getTransaction().commit();

        } catch (Exception ignored){

        }finally {
            session.close();
        }

    }

    public List<Image> getByProdctId(Long idPrdct) {

        session = factory.openSession();
        session.beginTransaction();

        try {
            String sql = "select * from image where product_id = :id";
           NativeQuery<Image> query = session.createNativeQuery(sql, Image.class);
           query.setParameter("id",idPrdct);

           return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }

    }


}
