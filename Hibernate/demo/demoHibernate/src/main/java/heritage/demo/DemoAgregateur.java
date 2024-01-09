package heritage.demo;

import com.mysql.cj.QueryResult;
import heritage.demo.model.Personne;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class DemoAgregateur {

    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        // Fonction d'aggrégation

        Query<Integer> query = session.createQuery("select max(age) from Personne");
        int ageMax = query.uniqueResult();
        System.out.println("Age max : " + ageMax);


        double ageMoyen = (double) session.createQuery("select avg(age) from Personne").uniqueResult();
        System.out.println("Moyenne d'age : " + ageMoyen);


        // Fonction IN

        List noms = new ArrayList<String>();

        noms.add("Robert");
        noms.add("Bill");

        Query<Personne> query1 = session.createQuery("from Personne where nom in :noms");
        query1.setParameter("noms",noms);

        List<Personne> personneList = query1.list();

        for (Personne p : personneList) {
            System.out.println(p);
        }

        // Execute Update

        String update_query = "update Personne set nom = :nomP where id=2";
        Query query3 = session.createQuery(update_query);
        query3.setParameter("nomP","JOJO");
        int success = query3.executeUpdate();

        System.out.println("success : " + success);


        session.getTransaction().commit();

        session.close();
        sessionFactory.close();


    }



}
