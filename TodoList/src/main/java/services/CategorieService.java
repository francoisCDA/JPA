package services;

import dao.CategorieDAO;
import model.Categorie;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class CategorieService {

    private final CategorieDAO categorieDAO;

    public CategorieService(EntityManagerFactory emf) {
        categorieDAO = new CategorieDAO(emf);
    }

    public List<Categorie> getAll(){
        return categorieDAO.getAll();
    }


    public Categorie get(String cat) {
        return categorieDAO.get(cat);
    }

    public boolean create(String cat) {
        Categorie newCat = new Categorie();
        newCat.setCategorie(cat);
        return categorieDAO.save(newCat);
    }


}
