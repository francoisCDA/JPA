package services;

import DAO.AgenceDAO;
import model.Agence;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class AgenceService {

    private AgenceDAO agenceDAO;

    public AgenceService(EntityManagerFactory emf){
        agenceDAO = new AgenceDAO(emf);
    }

    public List<Agence> getAll(){
        return agenceDAO.getAll();
    }

    public Long add(String adresse){
        Agence agence = new Agence();
        agence.setAdresse(adresse);
        return agenceDAO.save(agence);
    }

    public boolean remove(Long id) {
        return agenceDAO.remove(id);
    }

    public boolean update(Agence agence){
        return agenceDAO.update(agence);
    }


}
