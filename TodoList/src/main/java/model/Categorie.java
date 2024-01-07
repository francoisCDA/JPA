package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="categorie")
public class Categorie {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // devrait peut-être être définie comme clé primaire...
    private String categorie;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "categories")
    private List<Task> taches = new ArrayList<>();


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public List<Task> getTaches() {
        return taches;
    }

    public void setTaches(List<Task> taches) {
        this.taches = taches;
    }

    @Override
    public String toString() {
        return '[' + categorie + ']';
    }
}
