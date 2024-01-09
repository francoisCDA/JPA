package services;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactoryService {

    private StandardServiceRegistry registry;

    private SessionFactory sessionFactory;

    private static SessionFactoryService instance;


    private SessionFactoryService(){
        registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static SessionFactory get() {
        if (instance == null) {
            instance = new SessionFactoryService();
        }
        return instance.sessionFactory;
    }

    public static void close() {
        instance.sessionFactory.close();
        instance = null;
    }


}
