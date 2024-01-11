package dao;

import org.hibernate.SessionFactory;
import services.SessionFactoryService;
import org.hibernate.Session;
import org.hibernate.query.Query;

public abstract class BaseDAO {

    protected SessionFactory factory;

    protected Session session;


    public BaseDAO() {
        factory = SessionFactoryService.get();
    }





}
