package dao;

import models.Produit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import services.SessionFactoryService;

import java.util.List;

public class ProduitDAO implements DAO<Produit>{

    private SessionFactory factory;

    public ProduitDAO() {
        factory = SessionFactoryService.get();
    }

    @Override
    public void create(Produit produit) {

        update(produit);

    }

    @Override
    public Produit get(Long id) {
        Session session = factory.openSession();
        session.beginTransaction();

        Produit ret = session.load(Produit.class,id);

        session.getTransaction().commit();
        session.close();

        return ret;
    }

    @Override
    public List<Produit> getAll() {
        Session session = factory.openSession();
        session.beginTransaction();


        Query<Produit> produitsQuery = session.createQuery("from Produit ");

        List<Produit> ret = produitsQuery.list();

        session.getTransaction().commit();
        session.close();

        return ret;
    }

    @Override
    public void update(Produit produit) {

        Session session = factory.openSession();
        session.beginTransaction();

        session.saveOrUpdate(produit);

        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void remove(Long id) {

        Session session = factory.openSession();
        session.beginTransaction();

        Produit toRemove = session.load(Produit.class,id);

        session.delete(toRemove);

        session.getTransaction().commit();
        session.close();

    }
}
