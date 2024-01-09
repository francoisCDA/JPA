package dao;

import models.Produit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import services.SessionFactoryService;

import java.time.LocalDate;
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

        Produit ret = session.get(Produit.class,id);

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

    public List<Produit> betweenDates(LocalDate start,LocalDate end){
        Session session = factory.openSession();
        session.beginTransaction();

        Query<Produit> query = session.createQuery("from Produit where dateAchat > :start and dateAchat < :end");
        query.setParameter("start",start);
        query.setParameter("end",end);

        List<Produit> ret = query.list();

        session.getTransaction().commit();
        session.close();

        return ret;
    }

    public List<Produit> getPrdctPriceOver(Double prixMin) {

        Session session = factory.openSession();
        session.beginTransaction();

        Query<Produit> query = session.createQuery("from Produit where prix > :prix ");
        query.setParameter("prix",prixMin);

        List<Produit> ret = query.list();

        session.getTransaction().commit();
        session.close();

        return ret;
    }

    public List<Produit> getPrdctFilterByStock(int stock){
        Session session = factory.openSession();
        session.beginTransaction();

        Query<Produit> query = session.createQuery("from Produit where stock < :stock ");
        query.setParameter("stock",stock);

        List<Produit> ret = query.list();

        session.getTransaction().commit();
        session.close();

        return ret;
    }

    public List<Produit> getPrdctFilterByMarque(String trade){
        Session session = factory.openSession();
        session.beginTransaction();

        Query<Produit> query = session.createQuery("from Produit where marque = :marque ");
        query.setParameter("marque",trade);

        List<Produit> ret = query.list();

        session.getTransaction().commit();
        session.close();

        return ret;
    }


    public Double getAVGPrice(){
        Session session = factory.openSession();
        session.beginTransaction();

        double ret = (double) session.createQuery("select avg(prix) from Produit").uniqueResult();

        session.getTransaction().commit();
        session.close();

        return ret;
    }

    public void rmPrdctByMarque(String trade){
        Session session = factory.openSession();
        session.beginTransaction();

        Query<Produit> query = session.createQuery("delete Produit where marque = :marque ");
        query.setParameter("marque",trade);

        session.getTransaction().commit();
        session.close();

    }

    public List<String> getTradeNames(){
        Session session = factory.openSession();
        session.beginTransaction();

        Query<String> query = session.createQuery("select distinct marque from Produit");
        List<String> ret = query.list();

        session.getTransaction().commit();
        session.close();

        return ret;

    }







}
