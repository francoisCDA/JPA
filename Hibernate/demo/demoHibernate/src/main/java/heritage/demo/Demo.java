package heritage.demo;

import heritage.demo.model.Personne;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Demo {

    public static void main(String[] args) {

        create();

        //  get();
        // update();
        //delete();

    }

    public static void create() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        // Enregistrer une personne

        Personne p = new Personne();

        p.setNom("Bill");
        p.setPrenom("John");
        p.setAge(30);

        session.save(p);

        System.out.println("Personne : " + p.getId());

        // Récupérer une personne

        session.getTransaction().commit();

        session.close();
        sessionFactory.close();


    }


    public static void get(){

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Personne personne= session.load(Personne.class,1L);
        System.out.println("get : " + personne.toString());

        session.getTransaction().commit();

        session.close();
        sessionFactory.close();


    }


    public static void update(){

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Personne personne= session.load(Personne.class,1L);

        personne.setNom("Jean-Robert");

        session.update(personne);
        System.out.println("update : " + personne.toString());

        session.getTransaction().commit();

        session.close();
        sessionFactory.close();

    }

    public static void delete(){

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Personne personne= session.load(Personne.class,1L);

        session.delete(personne);

        session.getTransaction().commit();

        session.close();
        sessionFactory.close();

    }






}
