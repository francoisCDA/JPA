package heritage.demo;

import heritage.demo.model.Personne;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Iterator;
import java.util.List;

public class DemoQuery {

    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        // QUERY

        // HQL
        Query<Personne> personneQuery = session.createQuery("from Personne ");

        List<Personne> personneList = personneQuery.list();

        for (Personne p :personneList) {
            System.out.println("Personne : " + p.toString());
        }

        Iterator<Personne> personneIterator = personneQuery.iterate();

        while (personneIterator.hasNext()) {
            Personne p = personneIterator.next();
            System.out.println("Personne avec iterator " + p);
        }

        // une requète avec filter pour récupérer une liste

        Query<Personne> personneQuery2 = session.createQuery("from Personne where prenom = 'Martin'");
        List<Personne> list = personneQuery2.list();

        for (Personne p : list) {
            System.out.println("personne avec cun nom en paramètre : " + p);
        }

        Query<Personne> personneQuery3 = session.createQuery("from Personne where id = 5");
        List<Personne> list3 = personneQuery3.list();

        for (Personne p : list) {
            System.out.println("personne avec cun nom en paramètre : " + p);
        }




        session.getTransaction().commit();

        session.close();
        sessionFactory.close();

    }

}
