package ihm;

import model.Categorie;
import model.Priorite;
import model.Task;
import model.Utilisateur;
import services.CategorieService;
import services.TodoService;
import services.UserService;

import javax.persistence.Column;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class ConsoleIHM {

    private static UserService userService;
    private static TodoService todoService;
    private static CategorieService categorieService;


    public static void exe() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("todoList");
        userService = new UserService(emf);
        todoService = new TodoService(emf);
        categorieService = new CategorieService(emf);

        boolean run = true;
        String choix;


        while (run) {

            List<Utilisateur> userList = userService.getUsers();
            List<Categorie> categories = categorieService.getAll();

            UtilIHM.H1("Menu Principal");


            if (userList.isEmpty()) {
                UtilIHM.consoleConfirm("Aucun utilisateur");
            } else {
                UtilIHM.consoleConfirm("Liste des utlisateurs");
                for ( Utilisateur u:userList ){
                    UtilIHM.consoleLi(u.getId() + " - selectionner : " + u.getPseudo() +", " + u.getTodoList().size() + " tâche en cours");
                }
            }
            System.out.println();

            if (categories.isEmpty()){
                UtilIHM.consoleConfirm("Aucune catégorie de définie");
            } else {
                UtilIHM.consoleConfirm("Filtrer par catégorie");
                for (Categorie c:categories) {
                    UtilIHM.consoleLi(c.getCategorie() + " - nombre de tâches correspondantes : " + todoService.nbTaskByCat(c));
                }
            }

            System.out.println();
            UtilIHM.consoleLi("A - Ajouter un utilisateur");
            UtilIHM.consoleLi("C - Créer une nouvelle catégorie");
            UtilIHM.consoleLi("R - Supprimer une catégorie");
            UtilIHM.consoleLi("L - Lister toutes les tâches");
            UtilIHM.consoleLi("M - Lister les tâches actives");
            UtilIHM.consoleLi("N - Lister les tâches terminées");
            UtilIHM.consoleLi("Q - Quitter l'application");
            System.out.println();

            choix = UtilIHM.inputText("Choisir une action").toUpperCase();

            switch (choix) {
                case "A" -> addUser();
                case "C" -> addCat();
                case "R" -> rmCat(categories);
                case "L" -> printTasks(todoService.getTasks());
                case "M" -> printTasks(todoService.getActiveTask());
                case "N" -> printTasks(todoService.getCompletedTask());
                case "Q" -> run = false;
                default -> handleChoixUser(choix);
            }
        }
        todoService.closeEmf();
        userService.closeEmf();
        categorieService.closeEmf();
        emf.close();
    }

    private static void rmCat(List<Categorie> categories) {

        if (categories.isEmpty()) {
            UtilIHM.consoleConfirm("Aucune catégorie");
        } else {
            UtilIHM.H2("Liste des catégories");
            for (Categorie c: categories) {
                UtilIHM.consoleLi(c.getId() + " - supprimer : " + c.getCategorie());
            }
                UtilIHM.consoleLi("0 - annuler\n");

            try {
                Long choix = UtilIHM.inputLong("Indiquer l'ID");

                for(Categorie c: categories){
                    if (c.getId().equals(choix)){
                        if (categorieService.rmCat(c.getId())){
                            UtilIHM.consoleConfirm("suppression effectuée");
                        } else {
                            UtilIHM.consoleFail("problème de suppression");
                        }
                        return;
                    }
                }

                UtilIHM.consoleFail("Catégorie non trouvée");

            } catch (Exception e) {
                UtilIHM.consoleError("saisie invalide");
            }

        }

    }


    private static Categorie addCat() {
        String cat;

        UtilIHM.consoleConfirm("Créer une catégorie");

        cat = UtilIHM.inputText("Nom de la catégorie").toUpperCase();

        if (cat.isBlank() || cat.length() == 1 || cat.equals("DEL") || cat.equals("REMOVE")) {
            UtilIHM.consoleFail("Nom de catégorie invalide");
            return null;
        }

        try {
            Long number = parseLong(cat);
            UtilIHM.consoleFail("une catégorie ne peut être un nombre");
        } catch (NumberFormatException e) {
            return categorieService.create(cat);
        }

        return null;
    }


    private static void addUser() {
        String pseudo;

        UtilIHM.consoleConfirm("Ajouter un utilisateur");

        pseudo = UtilIHM.inputText("Pseudo du nouvel utilisateur");

        if (userService.addUser(pseudo)) {
            UtilIHM.consoleConfirm("Nouvel utilisateur ajouté");
        } else {
            UtilIHM.consoleFail("Ajoute d'utilsateur impossible");
        }
    }


    private static void handleChoixUser(String choix) {

        Categorie categorie = categorieService.get(choix);

        if (categorie != null) {
            UtilIHM.consoleConfirm("selection de la categorie : "+ categorie.getCategorie());
            menuCategorie(categorie);
            return;
        }

        Long id;

        try {
            id = parseLong(choix);

            if (userService.isUser(id)) {
                menuTache(id);
            } else {
                UtilIHM.consoleFail("Utilisateur non valide");
            }

        } catch (NumberFormatException e) {
            UtilIHM.consoleFail("Erreur de saisie");
        }
    }

    private static void menuCategorie(Categorie categorie) {

        List<Task> selectedTask = todoService.getTasksByCategorie(categorie);

        printTasks(selectedTask);


    }


    private static void menuTache(Long id) {

        Utilisateur user = userService.getUserById(id);

        String choix;

        if (user.getTodoList().isEmpty()) {
            UtilIHM.H2(user.getPseudo() + " n'a aucune tâche");
        } else {
            UtilIHM.H3("Lises des tâches de " + user.getPseudo() );
            for ( Task t: user.getTodoList()  ){
                UtilIHM.consoleLi(t.toString());
            }
        }

        UtilIHM.consoleLi("Selectionner une tâche par son ID -");
        UtilIHM.consoleLi("A - Ajouter une tâche à " + user.getPseudo());
        UtilIHM.consoleLi("REMOVE - Retirer l'utilisateur " + user.getPseudo());
        UtilIHM.consoleLi("Q - Retour menu Utilisateur");
        System.out.println();

        choix = UtilIHM.inputText("Choix").toUpperCase();

        switch (choix) {
            case "A" -> addTask(user);
            case "REMOVE" -> userService.remove(user) ;
            case "Q" -> {
                return;
            }
            default -> handleChoixTache(choix,id);
        }

    }


    private static void handleChoixTache(String choix, Long idUser) {

        Long id;

        try {
            id = parseLong(choix);

            Task tache = todoService.getTask(id);


            if (tache != null ) {

                UtilIHM.consoleConfirm(tache.toString());

                if (idUser != 0L && !tache.getUtilisateur().getId().equals(idUser) ){
                    UtilIHM.consoleFail("Tâche d'un autre utilisateur");
                    return;
                }

                while (updateTask(tache));

            } else {
                UtilIHM.consoleFail("Aucune tâche correspondante");
            }

        } catch (NumberFormatException e) {
            UtilIHM.consoleFail("Erreur de saisie");
        }
    }


    private static boolean updateTask(Task tache) {

        List<Categorie> categories = categorieService.getAll();

        UtilIHM.H2("mettre à jour la tâche " + tache.getId());

        UtilIHM.consoleLi("1 - tache : " + tache.getaFaire());
        UtilIHM.consoleLi("2 - description : " + tache.getInfoTache().getDescription());
        UtilIHM.consoleLi("3 - priorité : " + tache.getInfoTache().getPriorite());
        UtilIHM.consoleLi("4 - échéance : " + tache.getInfoTache().getEcheance());
        UtilIHM.consoleLi("5 - " + (tache.isComplete() ? "tâche 'terminée'" : "marquer comme 'terminée'" ));
        UtilIHM.consoleLi("6 - user : " + tache.getUtilisateur().getPseudo());

        for (Categorie c:categories) {
            UtilIHM.consoleLi(c.getCategorie() + " - " + (tache.getCategoriesString().contains(c.getCategorie()) ? "RETIRER DE" : "AJOUTER À") + " la categorie " + c.getCategorie() );
        }

        UtilIHM.consoleLi("7 - ajouter une nouvelle catégorie");

        System.out.print("\n");
        UtilIHM.consoleLi("DEL - Supprimer la tâche");
        System.out.print("\n");
        UtilIHM.consoleLi("Q - Retour");
        System.out.println();

        String choix = UtilIHM.inputText("élément à modifier").toUpperCase();

        for (Categorie cat: categories) {
            if (cat.getCategorie().equals(choix)) {
                if (todoService.toogleCat(tache,cat)){
                 UtilIHM.consoleConfirm("Les catégories ont bien été mise à jour");
                } else {
                 UtilIHM.consoleFail("Problème pendant la modification de la mise à jour");
                }
                return true;
            }
        }

        Categorie newCat = null;

        switch (choix) {

            case "1" -> tache.setaFaire(UtilIHM.inputText("Quelle est la nouvelle tâche ?"));
            case "2" -> tache.getInfoTache().setDescription(UtilIHM.inputText("Description de la tâche"));
            case "3" -> tache.getInfoTache().setPriorite(prioriteToDo());
            case "4" -> tache.getInfoTache().setEcheance(UtilIHM.inputDate("Date d'expiration (YYYY-MM-JJ)"));
            case "5" -> {
                if (tache.isComplete()) {
                    return true;
                } else {
                    tache.completed();
                }
            }
            case "6" -> {
                Utilisateur user = userToDo();
                if (user == null) {
                    UtilIHM.consoleFail("Utilisateur invalide");
                    return true;
                }
                tache.setUtilisateur(user);
            }
            case "7" -> newCat = addCat();
            case "DEL" -> {
               // if (todoService.removeTask(tache.getId())){
                if (userService.removeTask(tache) && todoService.removeTask(tache.getId())){
                    UtilIHM.consoleConfirm("Tâche supprimée");
                } else {
                    UtilIHM.consoleError("Problème à la suppresion de la tâche");
                }
                return false;
            }
            case "Q" -> {
                return false;
            }

            default -> {
                UtilIHM.consoleFail("Saisie invalide");
                return true;
            }
        }

        if (newCat != null) {
            todoService.toogleCat(tache,newCat);
            return true;
        }

        if (todoService.update(tache)) {
            UtilIHM.consoleConfirm("mise à jour réussie");
        } else {
            UtilIHM.consoleFail("Problème lors de la mise à jour");
        }

        return true;

    }

    private static Utilisateur userToDo() {

        List<Utilisateur> userList = userService.getUsers();
        Utilisateur ret= null;

        UtilIHM.consoleConfirm("Liste des utlisateurs");
        for ( Utilisateur u:userList ){
            UtilIHM.consoleLi(u.getId() + " - selectionner : " + u.getPseudo());
        }

        try {
            Long idUser = UtilIHM.inputLong("Selectionner un utilisateur");

            for (Utilisateur u: userList){
                if (u.getId().equals(idUser)) {
                    ret = u;
                    break;
                }
            }
        } catch (Exception ignored) {

        }

        return ret;
    }


    private static void addTask(Utilisateur user) {

        String task,description;
        Categorie categorie = null;
        LocalDate date;
        Priorite priorite;


        task = UtilIHM.inputText("Quelle est la nouvelle tâche ?");

        if (task.isEmpty()){
           UtilIHM.consoleFail("annulation");
           return;
        }

        description = UtilIHM.inputText("Description de la tâche");

        date = UtilIHM.inputDate("Date d'expiration (YYYY-MM-JJ)");

        priorite = prioriteToDo();

       while (categorie == null) {
           categorie = addCat();
           if (categorie == null) {
               UtilIHM.consoleFail("Saisie invalide");
           }
       }

        if (todoService.addTask(task,description,date,priorite,user,categorie)) {
            UtilIHM.consoleConfirm("Tâche ajoutée");
        } else {
            UtilIHM.consoleFail("Ajout impossible");
        }
    }



    private static Priorite prioriteToDo(){

        Priorite ret;
        int prioritInt;

        prioritInt = UtilIHM.menu(Priorite.getPriority(),"Priorité de la tâche");

        ret = Priorite.getPriority(prioritInt);

        return ret;

    }


    private static void printTasks( List<Task> tasks ) {

        UtilIHM.consoleConfirm("Liste des tâches :");

        if (tasks.size() > 0) {
            for (Task t: tasks) {
                System.out.println(t);
            }
            System.out.println();

            String choix = UtilIHM.inputText("Sélectionner une tâche par son ID ( 0 pour annuler )");

            handleChoixTache(choix,0L);

        } else {
            UtilIHM.consoleConfirm("Aucune tâche trouvée");
        }

    }


}
