package services;

import dao.ProduitDAO;
import models.Produit;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public Produit get(Long id) { return produitDAO.get(id);}

    public void update(Produit produit) { produitDAO.update(produit);}

    public void del(Long id) {produitDAO.remove(id);}

    public List<Produit> getBetweenDates(LocalDate start,LocalDate end){
        if (start.isBefore(end)) {
            return produitDAO.betweenDates(start, end);
        } else {
            return null;
        }
    }

    public List<Produit> getPrdctPriceOver(Double prixMin) {
        if (prixMin < 0.0 ) { prixMin = 0.0 ;}

        return produitDAO.getPrdctPriceOver(prixMin);
    }

    public List<Produit> getPrdctFilterByStock(int stock){
        if (stock < 0 ) { return new ArrayList<Produit>(); }

        return produitDAO.getPrdctFilterByStock(stock);
    }

    public List<Produit> getPrdctFilterByTrade(String trade) {
        return produitDAO.getPrdctFilterByMarque(trade);
    }

    public List<String> getTradeNames() {
        return produitDAO.getTradeNames();
    }

    public Double getPriceFromTrade(String marque) {

        List<Produit> produits = produitDAO.getPrdctFromTrade(marque);
        Double ret = 0.0;

        if (!produits.isEmpty()){
            for (Produit p:produits){
                ret += p.getPrix() * p.getStock();
            }
        }

        return ret;
    }

    public Double getAVGPrice(){
        return produitDAO.getAVGPrice();
    }

    public void rmTrade(String trade) {
        produitDAO.rmPrdctByMarque(trade);
    }

    public List<Produit> getPrdctByMinScore(int scoreMin) {
        return produitDAO.getByProdctScore(scoreMin);
    }

}
