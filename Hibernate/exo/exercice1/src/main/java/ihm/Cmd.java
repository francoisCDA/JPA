package ihm;

import jdk.jshell.execution.Util;
import models.Produit;
import services.ProduitService;

import java.time.LocalDate;
import java.util.List;

public class Cmd {

    private static final ProduitService produitService = new ProduitService();

    public static void lsProduit(){
        List<Produit> produits = produitService.getAll();

        if (produits.isEmpty()) {
            UtilIHM.consoleConfirm("Aucun produit");
        } else {
            UtilIHM.H3("Liste des produits");
            for (Produit p : produits) {
                UtilIHM.consoleLi(p.toString());
            }
        }
    }

    public static void lsPrdctBetweenDates(){

        LocalDate dateStart = UtilIHM.inputDate("date de début");
        LocalDate dateEnd = UtilIHM.inputDate("date de fin");

        List<Produit> produits = produitService.getBetweenDates(dateStart,dateEnd);

        if (produits != null) {
            for (Produit p: produits) {
                UtilIHM.consoleLi(p.toString());
            }
        } else {
            UtilIHM.consoleConfirm("période invalide");
        }

    }

    public static void lsPrdPriceOver(){

        Double prixMin = UtilIHM.inputPrix("Prix minimum");


        List<Produit> produits = produitService.getPrdctPriceOver(prixMin);

        for (Produit p:produits) {
            UtilIHM.consoleLi(p.toString());
        }

    }



    public static void mkProduit(){

        String mark = UtilIHM.inputText("Marque du produit");

        String ref = UtilIHM.inputText("Référence du produit");

        LocalDate date = UtilIHM.inputDate("Date d'achat");

        Double prix = UtilIHM.inputPrix("prix du produit");

        int stock = newStock();

        produitService.create(mark,ref,date,prix,stock);

    }



    public static void catProduit() {

        try {
            Long idProduit = UtilIHM.inputLong("Indiquer l'id du produit ");
            catProduit(idProduit);
        } catch (Exception e) {
            UtilIHM.consoleError(e.getMessage());
        }

    }

    public static void catProduit(Long id) {

        UtilIHM.H3(produitService.get(id).toString());

    }

    public static void catIdRefFilterByStock(){

        int stock = newStock();

        List<Produit> produits = produitService.getPrdctFilterByStock(stock);

        for (Produit p: produits) {
            UtilIHM.consoleLi( "id : " + p.getId() + ", ref : " + p.getReference());
        }

    }


    public static void updProduit(){
        try {
            Long idProduit = UtilIHM.inputLong("ID du produit à editer");
            updProduit(idProduit);
        } catch (Exception e) {
            UtilIHM.consoleError(e.getMessage());
        }

    }


    public static void updProduit(Long id) {

        Produit produit = produitService.get(id);

        UtilIHM.H2("Mise à jour du produit");
        UtilIHM.consoleLi("marque - modifier la marque");
        UtilIHM.consoleLi("ref - modifier la référence produit");
        UtilIHM.consoleLi("date - modifier la date d'achat");
        UtilIHM.consoleLi("prix - modifier le prix");
        UtilIHM.consoleLi("stock - modifier le stock");

        String choix = UtilIHM.inputText("$");

        switch (choix) {
            case "marque" -> produit.setMarque(UtilIHM.inputText("Marque du produit"));
            case "ref" -> produit.setReference(UtilIHM.inputText("Indiquer la référence"));
            case "date" -> produit.setDateAchat(UtilIHM.inputDate("Date d'achat"));
            case "prix" -> produit.setPrix(UtilIHM.inputPrix("Nouveau prix"));
            case "stock" -> produit.setStock(newStock());
            default -> {
                UtilIHM.consoleError("erreur de saisie");
                return;
            }
        }

        produitService.update(produit);

    }

    private static int newStock() {

        int stock = 0;

        try {
            int saisie =  UtilIHM.inputNumber("Nouveau stock");
            if (saisie>0) { stock = saisie;}
        } catch (Exception e) {
            UtilIHM.consoleError(e.getMessage());
        }

        return stock;
    }


    public static void  rmProduit() {
        try {
            Long idProduit = UtilIHM.inputLong("ID du produit à supprimer (O annuler)");
            rmProduit(idProduit);
        } catch (Exception e) {
            UtilIHM.consoleError(e.getMessage());
        }
    }

    public static void rmProduit(Long id) {
        if (id <= 0 ) {return;}
        produitService.del(id);
    }

    public static void getTradeStockValue() {

        List<String> tradesNames = produitService.getTradeNames();

        UtilIHM.H3("Liste des Marque");
        for (String tm:tradesNames) {
            UtilIHM.consoleLi(tm);
        }

        String choix = UtilIHM.inputText("Saisir une marque");


        if (tradesNames.contains(choix)){
            TODO
        }






    }


}
