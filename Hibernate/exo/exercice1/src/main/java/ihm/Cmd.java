package ihm;

import jdk.jshell.execution.Util;
import models.Commande;
import models.Commentaire;
import models.Image;
import models.Produit;
import services.CommentaireService;
import services.ImageService;
import services.ProduitService;

import java.time.LocalDate;
import java.util.List;

public class Cmd {

    private static final ProduitService produitService = new ProduitService();
    private static final ImageService imageService = new ImageService();
    private static final CommentaireService commentaireService = new CommentaireService();

    public static void lsProduit(){
        List<Produit> produits = produitService.getAll();

        if (produits == null || produits.isEmpty()) {
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
            Long idProduit = UtilIHM.inputLong("Indiquer l'id du produit (O pour annuler)");
            if (idProduit == 0 ) {return;}
            catProduit(idProduit);
        } catch (Exception e) {
            UtilIHM.consoleError(e.getMessage());
        }

    }

    public static void catProduit(Long id) {

        Produit p = produitService.get(id);

        UtilIHM.H3("Détail du produit");
        UtilIHM.consoleLi("id : " + p.getId());
        UtilIHM.consoleLi("Marque : " + p.getMarque());
        UtilIHM.consoleLi("Référénce : " + p.getReference());
        UtilIHM.consoleLi("Prix : " + UtilIHM.monetaireFormat( p.getPrix()) + "€" );
        UtilIHM.consoleLi("Date d'achat : " + p.getDateAchat());
        UtilIHM.consoleLi("Stock : " + p.getStock());

        List<Image> images = imageService.getByProdctId(p.getId());

        if (images != null && !images.isEmpty()) {
            UtilIHM.consoleConfirm(" --- Liste des images ---");
            for (Image img: images) {
                UtilIHM.consoleLi(img.toString());
            }
        } else {
            UtilIHM.consoleConfirm("Aucune image");
        }

        List<Commentaire> avis = commentaireService.getByProdctId(p.getId());

        if (avis != null && !avis.isEmpty()) {
            UtilIHM.consoleConfirm(" --- Liste des commentaires ---");
            for (Commentaire com: avis) {
                UtilIHM.consoleLi(com.toString());
            }
        } else {
            UtilIHM.consoleConfirm("Aucun avis");
        }

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
            Long idProduit = UtilIHM.inputLong("ID du produit à editer (0 pour annuler)");
            if (idProduit == 0 ) {return;}
            updProduit(idProduit);
        } catch (Exception e) {
            UtilIHM.consoleError(e.getMessage());
        }

    }

    public static void updProduit(Long id) {

        Produit produit = produitService.get(id);

        UtilIHM.H2("Mise à jour du produit");
        UtilIHM.consoleLi("marque - modifier '" + produit.getMarque() + '\'');
        UtilIHM.consoleLi("ref - modifier la référence '"+ produit.getReference() + '\'');
        UtilIHM.consoleLi("date - modifier la date d'achat '" + produit.getDateAchat() + '\'');
        UtilIHM.consoleLi("prix - modifier le prix '" + produit.getPrix() + "€'");
        UtilIHM.consoleLi("stock - modifier le stock '" + produit.getStock() + '\'');
        UtilIHM.consoleLi("com - editer les commentaires du produit");
        UtilIHM.consoleLi("img - editer les images du produit");


        String choix = UtilIHM.inputText("upd$");

        switch (choix) {
            case "marque" -> produit.setMarque(UtilIHM.inputText("Marque du produit"));
            case "ref" -> produit.setReference(UtilIHM.inputText("Indiquer la référence"));
            case "date" -> produit.setDateAchat(UtilIHM.inputDate("Date d'achat"));
            case "prix" -> produit.setPrix(UtilIHM.inputPrix("Nouveau prix"));
            case "stock" -> produit.setStock(newStock());
            case "com" -> {
                editCommentaires(produit);
                return;
            }
            case "img" -> {
                editImages(produit);
                return;
            }

            default -> {
                UtilIHM.consoleError("erreur de saisie");
                return;
            }
        }

        produitService.update(produit);

        UtilIHM.consoleConfirm("Produit mis à jour");
        catProduit(produit.getId());
        System.out.println();

    }



    private static void editImages(Produit produit) {

        List<Image> images = imageService.getByProdctId(produit.getId());

        if ( images!= null && !images.isEmpty()) {
            UtilIHM.H3("Liste des images");
            for (Image img:images) {
                UtilIHM.consoleLi(img.getId() + " - " + img.toString());
            }
            System.out.println();
            UtilIHM.consoleConfirm("séléctionner une image par son id");
        } else {
            UtilIHM.H3("Aucune image");
        }

        UtilIHM.consoleLi("add - ajouter une image");
        UtilIHM.consoleLi("q - retour");

        String choix = UtilIHM.inputText("editImages$");

        if (choix.equals("add")){
            String url = UtilIHM.inputText("URL de l'image");

            imageService.create(url,produit);

        } else {
            try {
                Long idImg = Long.parseLong(choix);
                for (Image img:images){
                    if(img.getId().equals(idImg)) {
                        String newUrl = UtilIHM.inputText("Nouvel URL (laisser vide pour supprimer");
                        if (newUrl.isEmpty()) {
                            imageService.del(idImg);
                        } else {
                            img.setUrl(newUrl);
                            imageService.update(img);
                        }

                        return;
                    }
                }

            } catch (Exception ignored) {

            }
        }
    }


    private static void editCommentaires(Produit produit) {

        if (produit.getAvis() != null && !produit.getAvis().isEmpty()) {
            UtilIHM.H3("Liste des commentaires");
            for (Commentaire com: produit.getAvis()) {
                UtilIHM.consoleLi(com.getId() + " - " + com.toString());
            }
            System.out.println();
            UtilIHM.consoleConfirm("sélectionner un commenaitre par son id");

        } else {
            UtilIHM.consoleConfirm("Aucun commentaire");

        }

        UtilIHM.consoleLi("add - ajouter un commentaire");

        String choix = UtilIHM.inputText("upd/commmentaries$");

        if (choix.equals("add")) {
            String avis = UtilIHM.inputText("commentaire");
            LocalDate date = UtilIHM.inputDate("date du commentaire");
            int note ;
            try {
                note = UtilIHM.inputNumber("note (0 à 5)");
            } catch (Exception e) {
                note = 0;
            }

            commentaireService.create(avis, date, note, produit);

        } else {
            try {
                Long idCom = Long.parseLong(choix);
                for (Commentaire com: produit.getAvis()) {
                    if (com.getId().equals(idCom)) {
                        editCommentaire(com);
                        return;
                    }
                }
                UtilIHM.consoleFail("id commentaire non valide");
            } catch (Exception ignored) {
                UtilIHM.consoleFail("saisie incorrecte");
            }
        }


    }

    private static void editCommentaire(Commentaire com) {

        UtilIHM.H3("Editer un commentaire");
        UtilIHM.consoleLi("rm - supprimer le commentaire");
        UtilIHM.consoleLi("avis - modifier l'avis : " + com.getAvis());
        UtilIHM.consoleLi("date - modifier la date : " + com.getDate());
        UtilIHM.consoleLi("note - modifier la note : " + com.getNote() + "/5");
        UtilIHM.consoleLi("q - quit");

        String cmd = UtilIHM.inputText("upd/commentaries/commentary$");

        switch (cmd) {
            case "q" -> {return;}
            case "rm" -> {
                commentaireService.del(com.getId());
                return;
            }
            case "avis" -> com.setAvis(UtilIHM.inputText("nouvel avis"));
            case "date" -> com.setDate(UtilIHM.inputDate("modifier la date"));
            case "note" -> com.setNote(getNote("nouvelle note"));
            default -> {
                UtilIHM.consoleFail("commande inconnue");
                return;
            }
        }

        commentaireService.update(com);

    }

    private static int getNote(String label) {

        try {
            int note = UtilIHM.inputNumber(label);
            if (note < 0 ) return 0;
            if (note > 5 ) return 5;
            return note;
        } catch (Exception e) {
            return 0;
        }
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

        lsProduit();

        try {
            Long idProduit = UtilIHM.inputLong("ID du produit à supprimer (O annuler)");
            if (idProduit == 0 ) {return;}
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

        String tradeName = getTradeName();

        Double prix = 0.0 ;

        if (tradeName != null){
            prix = produitService.getPriceFromTrade(tradeName);
        }

        UtilIHM.consoleConfirm("Valeur total des produits de la marque " + tradeName + " = " + UtilIHM.monetaireFormat(prix) + "€" );

    }

    public static void getAVGPrice(){

        Double avg =  produitService.getAVGPrice();

        UtilIHM.consoleConfirm("Le prix moyen des articles est de : " + UtilIHM.monetaireFormat(avg) + "€");

    }

    public static void getPrdctFromTrade(){

        String tradeName = getTradeName();

        if (tradeName != null){
            List<Produit> produits = produitService.getPrdctFilterByTrade(tradeName);

            if (produits != null) {
                UtilIHM.H3("liste des produits de la marque " + tradeName);
                for (Produit p: produits) {
                    UtilIHM.consoleLi(p.toString());
                }
            } else {
                UtilIHM.consoleFail("Marque non trouvée");
            }

        }
    }

    public static void rmTrade() {

        String tradeName = getTradeName();

        if (tradeName != null ) {
            produitService.rmTrade(tradeName);
        }

    }


    private static String getTradeName(){

        List<String> tradesNames = produitService.getTradeNames();

        UtilIHM.H3("Liste des Marque");
        for (String tm:tradesNames) {
            UtilIHM.consoleLi(tm);
        }

        String choix = UtilIHM.inputText("Saisir une marque");

        if (tradesNames.contains(choix)){
            return choix;
        }

        return null;

    }

    private static void printPrdctWithMinScore() {

        try {
            int minScore = UtilIHM.inputNumber("moyenne minimum") ;

            if (minScore < 0 || minScore > 5) {
                UtilIHM.consoleFail("score invalide");
                return;
            }

            List<Produit> produits = produitService.getPrdctByMinScore(minScore);

            if (produits != null && !produits.isEmpty()) {
                UtilIHM.H3("Produit ayant un score moyen supérieur ou égale à " + minScore);
                UtilIHM.printLs(produits);
            } else {
                UtilIHM.consoleFail("Aucun produit trouvé");
            }

        } catch (Exception e) {
            UtilIHM.consoleError("Saisie invalide");
        }

    }


}
