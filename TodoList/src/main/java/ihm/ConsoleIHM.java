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
            System.out.println("1 < Ajouter une tâche");
            System.out.println("2 < Afficher les tâches");
            System.out.println("3 < Terminer une tâche");
            System.out.println("4 < Supprimer une tâche");

            System.out.println("0 < Quitter l'application");

            System.out.print("\n\t> ");

            choix = UtilIHM.inputText("choix");

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

        if (task.length() == 0){
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

        System.out.println("\nListe des tâches");

        List<Task> tasks = todoService.getTasks() ;

        for (Task t: tasks) {
            System.out.println(t);
        }

    }

    private void completeTask() {

        System.out.println("Terminer une tâche\n");

        List<Task> tasks = todoService.getActiveTask();
        String target;
        Long id;

        for (Task t: tasks) {
            System.out.println(t);
        }

        System.out.println("\nIndiquer l'id de la tâche à terminer :");
        System.out.printf("O pour annuler");

        System.out.print("\n\t> ");

        target = scan.nextLine();

        try {
            id = parseLong(target);

            if (id == 0) return;

            if (todoService.completTask(id)){
                System.out.println("tâche compétée");
            }

        } catch (NumberFormatException e) {
            System.out.println(" !!! Erreur de saisie");
        }

    }

    private void deleteTask() {
        System.out.println("Supprimer une tâche\n");

        List<Task> tasks = todoService.getCompletedTask();
        String target;
        Long id;

        for (Task t: tasks) {
            System.out.println(t);
        }

        System.out.println("\nIndiquer la tâche à supprimer :");
        System.out.printf("O pour annuler");

        System.out.print("\n\t> ");

        target = scan.nextLine();

        try {
            id = parseLong(target);

            if (id == 0) return;

            if (todoService.removeTask(id)){
                System.out.println("tâche supprimée");
            } else {
                System.out.println(" ! erreur de suppression");
            }

        } catch (NumberFormatException e) {
            System.out.println(" !!! Erreur de saisie");
        }

    }


}
