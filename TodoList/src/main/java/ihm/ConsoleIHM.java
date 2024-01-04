package ihm;

import model.Priorite;
import model.Task;
import model.Utilisateur;
import services.TodoService;
import services.UserService;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class ConsoleIHM {

    private UserService userService;
    private TodoService todoService;

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
            UtilIHM.consoleLi("A - Ajouter un utilsateur");

            if (userList.isEmpty()) {
                UtilIHM.consoleConfirm("Aucun utilisateur");
            } else {
                UtilIHM.consoleConfirm("Liste des utlisateurs");
                for ( Utilisateur u:userList ){
                    UtilIHM.consoleLi(u.getId() + " - selectionner : " + u.getPseudo());
                }
            }

            System.out.println();
            UtilIHM.consoleLi("Q - Quitter l'application");
            System.out.println();

            choix = UtilIHM.inputText("Choisir une action").toUpperCase();

            switch (choix) {
                case "A" -> addUser();
                case "Q" -> run = false;
                default -> handleChoix(choix);
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

    private void handleChoix(String choix) {

        Long id;

        try {
            id = parseLong(choix);

            if (userService.isUser(id)) {
                menuTache(id);
            } else {
                UtilIHM.consoleFail("Utilisateur non valide");
            }

        } catch (NumberFormatException e) {
            UtilIHM.consoleFail("erreur de saisie");
            return;
        }

    }


    private void menuTache(Long id) {

        String choix;

        UtilIHM.consoleLi("1 - Ajouter une tâche");
        UtilIHM.consoleLi("2 - Afficher les tâches");
        UtilIHM.consoleLi("3 - Terminer une tâche");
        UtilIHM.consoleLi("4 - Modifier les infos d'une tâche");
        UtilIHM.consoleLi("5 - Supprimer une tâche");
        System.out.println();
        UtilIHM.consoleLi("0 - Retour menu Utilisateur");
        System.out.println();

        choix = UtilIHM.inputText("Choix");

        switch (choix) {
            case "1" -> addTask();
            case "2" -> printTasks();
            case "3" -> completeTask();
            case "4" -> updateTask();
            case "5" -> deleteTask();
            case "0" -> {
                return;
            }
            default -> System.out.println("pas compris");
        }

    }


    private void updateTask() {
        Long choix;

        printTasks();

        try {
            choix = UtilIHM.inputLong("Indiquer l'ID à modifier");

            Task target = todoService.getTask(choix);

            UtilIHM.consoleConfirm(target.toString());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

    private void addTask() {
        String task,description;
        LocalDate date;
        Priorite priorite;
        int prioritInt;

        task = UtilIHM.inputText("Quelle est la nouvelle tâche ?");

        if (task.isEmpty()){
           UtilIHM.consoleFail("annulation");
           return;
        }

        description = UtilIHM.inputText("Description de la tâche");

        date = UtilIHM.inputDate("Date d'expiration (YYYY-MM-JJ)");

        prioritInt = UtilIHM.menu(Priorite.getPriority(),"Priorité de la tâche");

        priorite = Priorite.getPriority(prioritInt);


        if (todoService.addTask(task,description,date,priorite)) {
            UtilIHM.consoleConfirm("Tâche ajoutée");
        } else {
            UtilIHM.consoleFail("Ajout impossible");
        }
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
