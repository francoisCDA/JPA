package dao;

import model.Utilisateur;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public abstract class UserDAO {

    protected EntityManagerFactory _emf;

    protected EntityManager em;

    public UserDAO(EntityManagerFactory emf) {
        _emf = emf;
    }

    public abstract List<Utilisateur> getUsers();

    public abstract Utilisateur getUserById(Long id);

    public abstract boolean addUser(Utilisateur user);


}
