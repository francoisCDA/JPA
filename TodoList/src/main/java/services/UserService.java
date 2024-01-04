package services;

import dao.UserDAO;
import dao.UserDAOImpl;
import jdk.jshell.execution.Util;
import model.Utilisateur;
import java.util.List;

import javax.persistence.EntityManagerFactory;

public class UserService {

    private UserDAOImpl userDAO;


    public UserService(EntityManagerFactory emf){
        userDAO = new UserDAOImpl(emf);
    }

    public boolean addUser(String pseudo) {

        Utilisateur newUser = new Utilisateur(pseudo);

        return userDAO.addUser(newUser);

    }

    public List<Utilisateur> getUsers() {

        return userDAO.getUsers();

    }

    public Utilisateur getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    public boolean isUser(Long id) {

        Utilisateur user = getUserById(id);

        if (user == null) {
            return false;
        } else {
            return true;
        }
    }



}
