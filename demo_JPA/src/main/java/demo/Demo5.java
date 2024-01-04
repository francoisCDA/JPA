package demo;

import entity.oneToMany.Departement;
import entity.oneToMany.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Demo5 {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo_jpa");

    public static void main() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Employee employee = new Employee();
        Employee employee1 = new Employee();

        employee.setPrenom("Jacques");
        employee.setNom("Martin");

        employee1.setPrenom("Henri");
        employee1.setNom("Michel");

        Departement deptCompatble = new Departement();

        deptCompatble.setDepName("comptabilité");

        employee.setDepartement(deptCompatble);
        employee1.setDepartement(deptCompatble);

        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(employee);
        employeeList.add(employee1);

        deptCompatble.setEmployeeList(employeeList);

        em.persist(deptCompatble);

        em.getTransaction().commit();

        em.close();
        emf.close();

    }


    public static void show() {



    }


}
