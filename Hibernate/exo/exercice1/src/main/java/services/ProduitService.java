package services;

import dao.ProduitDAO;
import models.Produit;

import java.time.LocalDate;
import java.util.List;

public class ProduitService {

    private ProduitDAO produitDAO;

    public ProduitService() {
        produitDAO = new ProduitDAO();
    }

    public void create(String marq, String ref, LocalDate datAchat, Double prix, int stock){

        Produit newProduit = new Produit();
        newProduit.setMarque(marq);
        newProduit.setReference(ref);
        newProduit.setDateAchat(datAchat);
        newProduit.setPrix(prix);
        newProduit.setStock(stock);

        produitDAO.create(newProduit);

    }

    public List<Produit> getAll() { return produitDAO.getAll();}

    public Produit get(Long id ) { return produitDAO.get(id);}

    public void update(Produit produit) { produitDAO.update(produit);}
    public void del(Long id) {produitDAO.remove(id);}

}
