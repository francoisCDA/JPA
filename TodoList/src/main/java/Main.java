import ihm.ConsoleIHM;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        ConsoleIHM todoList = new ConsoleIHM(Persistence.createEntityManagerFactory("todoList"));

        todoList.exe();

    }

}
