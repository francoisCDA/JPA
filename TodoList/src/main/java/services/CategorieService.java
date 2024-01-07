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

    public Categorie create(String cat) {

        Categorie newCat = categorieDAO.get(cat);

        if (newCat != null) {
            return newCat;
        }

        newCat = new Categorie();
        newCat.setCategorie(cat);

        if (categorieDAO.save(newCat)){
            return newCat;
        } else {
            return null;
        }
    }

    public boolean rmCat(Long catId){

        return categorieDAO.rm(catId);

    }

    public void closeEmf(){
        categorieDAO.close();
    }

}
