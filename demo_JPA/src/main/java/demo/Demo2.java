package demo;

import entity.Person;

import javax.persistence.*;
import java.util.List;

public class Demo2 {

    public static void main() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo_jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        try {
            Person person = em.getReference(Person.class,6L);
            System.out.println(person.toString());
        } catch (EnumConstantNotPresentException ex) {
            System.out.println("Entity non trouvée");
        }

        //singleResult
        Query query = em.createQuery("select p from Person p where p.nom ='Gate'", Person.class);
        Person person2 = (Person) query.getSingleResult();
        System.out.printf(person2.toString());

        System.out.println("\nresultList : ");
        //resultList
        Query query1 = em.createQuery("select p from Person p where p.id > 3");
        List<Person> personList = query1.getResultList();

        for (Person p: personList) {
            System.out.println("person : " + p.getPrenom());
        }

        System.out.println("paramètre nommé : ");
        //paramètre nommé
        Query query2 = em.createQuery("select p from Person p where p.id = :id");
        query2.setParameter("id",2L);

        List<Person> personList2 = query2.getResultList();

        for (Person p: personList2) {
            System.out.println("person : " + p.getNom());
        }

        System.out.println("Modifier une occurence : ");
        Person person5 = em.find(Person.class,5L);

        person5.setPrenom("Billou");
        person5.setNom("Gates");

        transaction.commit();


        List<Person> personList1 = em.createNativeQuery("SELECT * FROM person, Person.class").getResultList();

        for (Person p: personList1){
            System.out.println("p : " + p);
        }

        em.close();
        emf.close();



    }
}
