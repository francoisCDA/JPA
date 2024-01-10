package ihm;

import services.SessionFactoryService;

public class Autoexec {

    public static void bat() {

        boolean run = true;

        UtilIHM.H2("Welcome");

        UtilIHM.consoleConfirm("Type 'help' to print available commands");

        while (run) {

            String cmd = UtilIHM.inputText("$");

            switch (cmd) {
                case "exit", "quit", "q" -> run = false;
                case "help", "h" -> cmdMenu();

                case "ls", "dir" -> Cmd.lsProduit();
                case "rm", "del" -> Cmd.rmProduit();
                case "cat" -> Cmd.catProduit();
                case "mk", "create" -> Cmd.mkProduit();
                case "upd" -> Cmd.updProduit();
                case "tsv" -> Cmd.getTradeStockValue();
                case "lsd" -> Cmd.lsPrdctBetweenDates();
                case "lsp" -> Cmd.lsPrdPriceOver();
                case "cats" -> Cmd.catIdRefFilterByStock();
                case "avg" -> Cmd.getAVGPrice();
                case "lsm" -> Cmd.getPrdctFromTrade();
                case "rmm" -> Cmd.rmTrade();
                default -> UtilIHM.consoleFail("commande non trouvée - tapper 'help' pour afficher la liste des commandes disponibles");
            }
        }

        SessionFactoryService.close();

    }

    private static void cmdMenu() {

        System.out.println("\n");
        UtilIHM.H3("Liste des commandes :");

        UtilIHM.consoleLi("help / h - afficher ce menu");
        UtilIHM.consoleLi("exit / quit / q - quitter l'application");
        UtilIHM.consoleLi("ls - lister les produits");
        UtilIHM.consoleLi("cat - afficher un produit");
        UtilIHM.consoleLi("mk / create - creer un nouveau produit");
        UtilIHM.consoleLi("upd - mettre un jour un produit");
        UtilIHM.consoleLi("rm - supprimer un produit ");
        UtilIHM.consoleLi("rmm - suppriemr une marque et ses produits (ex.4 q.4)");
        UtilIHM.consoleLi("tsv - afficher la valeur du stock des produits d'une marque");
        UtilIHM.consoleLi("avg - afficher le prix moyen des produits (ex.4 q.2)");
        UtilIHM.consoleLi("lsd - lister les produits achetés entre 2 dates");
        UtilIHM.consoleLi("lsp - lister les produits en fonction de leur prix");
        UtilIHM.consoleLi("lsm - lister les produits d'une marque choisie (ex.4 q.3)");
        UtilIHM.consoleLi("cats - afficher les id et références en fonction de leur stock");
        System.out.println("\n");
    }

}
