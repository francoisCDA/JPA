package heritage.demo.composeKey;

import javax.persistence.*;

@Entity
@IdClass(EmployeePK.class)
public class Employee {


    private String prenom;


    private String nom;

    private Integer taille;

    public Employee() {

    }

    @Id
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Id
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getTaille() {
        return taille;
    }

    public void setTaille(Integer taille) {
        this.taille = taille;
    }
}
