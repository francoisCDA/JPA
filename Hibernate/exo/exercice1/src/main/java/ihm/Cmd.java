package ihm;

import services.ProduitService;

import java.time.LocalDate;

public class Cmd {

    private static final ProduitService produitService = new ProduitService();

    public static void mkProduit(){

        String mark = UtilIHM.inputText("Marque du produit");

        String ref = UtilIHM.inputText("Référence du produit");

        LocalDate date = UtilIHM.inputDate("Date d'achat");

        Double prix = UtilIHM.inputPrix("prix du produit");

        int stock;
        try {
            stock = UtilIHM.inputNumber("nombre en stock");
            if (stock<0) {
                stock = 0;
            }
        } catch (Exception e) {
            stock = 0;
        }

        produitService.create(mark,ref,date,prix,stock);

    }


}
