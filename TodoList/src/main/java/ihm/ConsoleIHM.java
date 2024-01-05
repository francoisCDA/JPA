package ihm;

import model.Priorite;
import model.Task;
import model.Utilisateur;
import services.TodoService;
import services.UserService;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class ConsoleIHM {

    private final UserService userService;
    private final TodoService todoService;

    public ConsoleIHM(EntityManagerFactory emf) {
        userService = new UserService(emf);
        todoService = new TodoService(emf);
    }


    public void exe() {

        boolean run = true;
        String choix;


        while (run) {

            List<Utilisateur> userList = userService.getUsers();


            UtilIHM.H1("Menu Principal");


            if (userList.isEmpty()) {
                UtilIHM.consoleConfirm("Aucun utilisateur");
            } else {
                UtilIHM.consoleConfirm("Liste des utlisateurs");
                for ( Utilisateur u:userList ){
                    UtilIHM.consoleLi(u.getId() + " - selectionner : " + u.getPseudo());
                }
            }

            System.out.println();
            UtilIHM.consoleLi("A - Ajouter un utilisateur");
            UtilIHM.consoleLi("Q - Quitter l'application");
            System.out.println();

            choix = UtilIHM.inputText("Choisir une action").toUpperCase();

            switch (choix) {
                case "A" -> addUser();
                case "Q" -> run = false;
                default -> handleChoixUser(choix);
            }

        }
        todoService.closeEmf();
    }


    private void addUser() {
        String pseudo;

        UtilIHM.consoleConfirm("Ajouter un utilisateur");

        pseudo = UtilIHM.inputText("pseudo de l'utilisateur");

        if (userService.addUser(pseudo)) {
            UtilIHM.consoleConfirm("Nouvel utilisateur ajouté");
        } else {
            UtilIHM.consoleFail("Ajoute d'utilsateur impossible");
        }

    }

    private void handleChoixUser(String choix) {

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


    private void menuTache(Long id) {

        Utilisateur user = userService.getUserById(id);
        String choix;

        if (user.getTodoList().isEmpty()) {
            UtilIHM.consoleConfirm(user.getPseudo() + " n'a aucune tâche");
        } else {
            UtilIHM.consoleConfirm("Selectionner une tâche de " + user.getPseudo() +  " par son ID");
            for ( Task t: user.getTodoList()  ){
                UtilIHM.consoleLi(t.toString());
            }
        }

        UtilIHM.consoleLi("A - Ajouter une tâche");
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


    private void handleChoixTache(String choix, Long idUser) {

        Long id;

        try {
            id = parseLong(choix);

            Task tache = todoService.getTask(id);

            if (tache != null && tache.getUtilisateur().getId().equals(idUser)) {
                updateTask(tache);
            } else {
                UtilIHM.consoleFail("Tache invalide valide");
            }

        } catch (NumberFormatException e) {
            UtilIHM.consoleFail("Erreur de saisie");
        }
    }


    private void updateTask(Task tache) {

        UtilIHM.consoleConfirm("mettre à jour la tâche " + tache.getId());

        UtilIHM.consoleLi("1 - tache : " + tache.getaFaire());
        UtilIHM.consoleLi("2 - description : " + tache.getInfoTache().getDescription());
        UtilIHM.consoleLi("3 - priorité : " + tache.getInfoTache().getPriorite());
        UtilIHM.consoleLi("4 - échéance : " + tache.getInfoTache().getEcheance());
        UtilIHM.consoleLi("5 - " + (tache.isComplete() ? "tâche terminée" : "terminer la tâche" ));
        UtilIHM.consoleLi("6 - user : " + tache.getUtilisateur().getPseudo());
        System.out.print("\n");
        UtilIHM.consoleLi("DEL - Supprimer la tâche");
        System.out.print("\n");
        UtilIHM.consoleLi("Q - Retour");

        String choix = UtilIHM.inputText("élément à modifier").toUpperCase();


        switch (choix) {

            case "1" -> tache.setaFaire(UtilIHM.inputText("Quelle est la nouvelle tâche ?"));
            case "2" -> tache.getInfoTache().setDescription(UtilIHM.inputText("Description de la tâche"));
            case "3" -> tache.getInfoTache().setPriorite(prioriteToDo());
            case "4" -> tache.getInfoTache().setEcheance(UtilIHM.inputDate("Date d'expiration (YYYY-MM-JJ)"));
            case "5" -> {
                if (tache.isComplete()) {
                    return;
                } else {
                    tache.completed();
                }
            }
            case "6" -> tache.setUtilisateur(userToDo());
            case "DEL" -> {
                todoService.removeTask(tache.getId());
                return;
            }
            case "Q" -> {
                return;
            }

            default -> {
                UtilIHM.consoleFail("Saisie invalide");
                return;
            }
        }

        if (todoService.update(tache)) {
            UtilIHM.consoleConfirm("mise à jour réussie");
        } else {
            UtilIHM.consoleFail("Problème lors de la mise à jour");
        }

    }

    private Utilisateur userToDo() {

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


    private void addTask(Utilisateur user) {

        String task,description;
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


        if (todoService.addTask(task,description,date,priorite,user)) {
            UtilIHM.consoleConfirm("Tâche ajoutée");
        } else {
            UtilIHM.consoleFail("Ajout impossible");
        }
    }


    private Priorite prioriteToDo(){

        Priorite ret;
        int prioritInt;

        prioritInt = UtilIHM.menu(Priorite.getPriority(),"Priorité de la tâche");

        ret = Priorite.getPriority(prioritInt);

        return ret;

    }


    private void printTasks() {

        UtilIHM.consoleConfirm("Liste des tâches :");

        List<Task> tasks = todoService.getTasks() ;

        for (Task t: tasks) {
            System.out.println(t);
        }

        System.out.println("\n");

    }

    private void completeTask() {

        UtilIHM.consoleConfirm("Terminer une tâche");

        List<Task> tasks = todoService.getActiveTask();
        String target;
        Long id;

        for (Task t: tasks) {
            System.out.println(t);
        }
        System.out.println("\n");

        target = UtilIHM.inputText("ID de la tâche à annuler (0 pour annuler)");

        try {
            id = parseLong(target);

            if (id == 0) return;

            if (todoService.completTask(id)){
                UtilIHM.consoleConfirm("tâche compétée");
            } else {
                UtilIHM.consoleFail("Suppression impossible");
            }

        } catch (NumberFormatException e) {
            UtilIHM.consoleError("Erreur de saisie");
        }

    }

    private void deleteTask() {

        UtilIHM.consoleConfirm("Supprimer une tâche");

        List<Task> tasks = todoService.getCompletedTask();
        String target;
        Long id;

        for (Task t: tasks) {
            System.out.println(t);
        }
        System.out.println("\n");

        target = UtilIHM.inputText("ID de la tâche à supprimer (0 pour annuler)");

        try {
            id = parseLong(target);

            if (id == 0) return;

            if (todoService.removeTask(id)){
                UtilIHM.consoleConfirm("tâche supprimée");
            } else {
                UtilIHM.consoleError("Problème à la suppression");
            }

        } catch (NumberFormatException e) {
            UtilIHM.consoleError("Erreur de saisie");
        }
    }

}
