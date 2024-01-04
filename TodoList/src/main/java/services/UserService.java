package services;

import dao.UserDAO;
import dao.UserDAOImpl;
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

    public boolean isUser(Long id) {

        Utilisateur user = userDAO.getUserById(id);

        if (user == null) {
            return false;
        } else {
            return true;
        }
    }


}
