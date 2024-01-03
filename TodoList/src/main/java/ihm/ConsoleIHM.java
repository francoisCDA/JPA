package ihm;

import model.Priorite;
import model.Task;
import model.TaskInfo;
import services.TodoService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class ConsoleIHM {

    private TodoService todoService;

    public ConsoleIHM() {
        todoService = new TodoService();
    }


    public void exe() {

        boolean run = true;
        String choix;

        while (run) {
            UtilIHM.consoleLi("1 - Ajouter une tâche");
            UtilIHM.consoleLi("2 - Afficher les tâches");
            UtilIHM.consoleLi("3 - Terminer une tâche");
            UtilIHM.consoleLi("4 - Supprimer une tâche");
            System.out.println();
            UtilIHM.consoleLi("0 - Quitter l'application");
            System.out.println();

            choix = UtilIHM.inputText("Choix");

            switch (choix) {
                case "1" -> addTask();
                case "2" -> printTasks();
                case "3" -> completeTask();
                case "4" -> deleteTask();
                case "0" -> run = false;
                default -> System.out.println("pas compris");
            }
        }
        todoService.closeEmf();
    }

    private void addTask() {
        String task,description;
        Task tache = null;
        TaskInfo infoTache = null;
        LocalDate date;
        Priorite priorite;
        int prioritInt;

        task = UtilIHM.inputText("Quelle est la nouvelle tâche ?");

        if (task.isEmpty()){
           UtilIHM.consoleFail("annulation");
           return;
        }

        tache = new Task(task);
        infoTache = new TaskInfo();

        infoTache.setDescription(UtilIHM.inputText("Description de la tâche"));

        infoTache.setEcheance(UtilIHM.inputDate("Date d'expiration (YYYY-MM-JJ)"));

        prioritInt = UtilIHM.menu(Priorite.getPriority(),"Priorité de la tâche");

        infoTache.setPriorite(Priorite.getPriority(prioritInt));

        tache.setInfoTache(infoTache);

        todoService.addTask(tache);

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
