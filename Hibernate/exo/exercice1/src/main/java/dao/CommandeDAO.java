package dao;

import models.Commande;
import models.Commentaire;

import java.util.List;

public class CommandeDAO extends BaseDAO implements DAO<Commande>{

    @Override
    public void create(Commande obj) {
        update(obj);
    }

    @Override
    public Commande get(Long id) {
        session = factory.openSession();
        session.beginTransaction();

        try {
            Commande ret = session.get(Commande.class,id);
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
    public List<Commande> getAll() {
        return null;
    }

    @Override
    public void update(Commande obj) {
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
            Commande toRemove = session.load(Commande.class,id);
            session.delete(toRemove);
            session.getTransaction().commit();

        } catch (Exception ignored){

        }finally {
            session.close();
        }
    }
}
