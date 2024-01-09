package heritage.demo;

import heritage.demo.model.Personne;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.type.StringType;

import org.hibernate.query.Query;
import java.util.List;

public class DemoQuery2 {

    public static void main(String[] args) {


        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        String search = "Robert";

        Query<Personne> personneQuery = session.createQuery("from Personne where nom like :nom");
        personneQuery.setParameter("nom",search+"%", StringType.INSTANCE);
        List<Personne> lesRoberts = personneQuery.list();

        for (Personne p :lesRoberts) {
            System.out.println("les roberts : " + p);
        }

        Query<Personne> personneQuery2 = session.createQuery("from Personne where nom like ?1");
        personneQuery2.setParameter(1,search+"%",StringType.INSTANCE);
        List<Personne> lesRoberts2 = personneQuery.list();

        for (Personne p :lesRoberts2) {
            System.out.println("les roberts : " + p);
        }

        


        session.getTransaction().commit();

        session.close();
        sessionFactory.close();


    }

}
