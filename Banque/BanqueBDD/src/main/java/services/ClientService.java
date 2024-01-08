package services;

import DAO.ClientDAO;

import javax.persistence.EntityManagerFactory;

public class ClientService {

    private ClientDAO clientDAO;

    public ClientService(EntityManagerFactory emf) {
        clientDAO = new ClientDAO(emf);
    }



}
