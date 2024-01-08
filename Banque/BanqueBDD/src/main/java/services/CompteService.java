package services;

import DAO.CompteDAO;

import model.Compte;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class CompteService {

    private CompteDAO compteDAO;

    public CompteService(EntityManagerFactory emf){
        compteDAO = new CompteDAO(emf);
    }

    public List<Compte> getAll(){
        return  compteDAO.getAll();
    }

    public Long add(String iban, String libelle, Double solde){
        Compte compte = new Compte();
        compte.setIban(iban);
        compte.setLibelle(libelle);
        compte.setSolde(solde);
        return  compteDAO.save(compte);
    }

    public boolean remove(Long id) {
        return  compteDAO.remove(id);
    }

    public boolean update(Compte compte){
        return  compteDAO.update(compte);
    }

}
