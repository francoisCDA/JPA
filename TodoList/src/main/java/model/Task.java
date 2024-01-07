package model;

import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "todo")
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tache")
    private Long id;

    @Column(length = 500)
    private String aFaire;

    private boolean complete;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="info_tache_id",referencedColumnName = "id_info_tache" )
    private TaskInfo infoTache;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Utilisateur utilisateur;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="tache_categorie",joinColumns = @JoinColumn(name="tache_id"), inverseJoinColumns = @JoinColumn(name="categorie_id"))
    private List<Categorie> categories;


    public Task(String tache, Utilisateur user){
        aFaire = tache;
        complete = false;
        utilisateur = user;
        categories = new ArrayList<>();
    }

    public Task() {
        
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getaFaire() {
        return aFaire;
    }

    public void setaFaire(String aFaire) {
        this.aFaire = aFaire;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void completed(){
        complete = true;
    }

    @Override
    public String toString() {
        return "\ttache -> " +
                "id : " + id +
                ", " + aFaire +
                ", " + (complete ? "terminée" : "à finir") +
                ", utilisateur : " + utilisateur.getPseudo() +
                ",\n\t échéance : " + infoTache.getEcheance() +
                ", priorité : " + infoTache.getPriorite() +
                ", détails : " + infoTache.getDescription() +
                ",\n\t categories : " + categories.toString() +
                ".\n";
    }

    public TaskInfo getInfoTache() {
        return infoTache;
    }

    public void setInfoTache(TaskInfo infoTache) {
        this.infoTache = infoTache;
    }

    public void addCategorie(Categorie categorie){
        categories.add(categorie);
    }

    public void rmCategorie(Categorie categorie) {categories.remove(categorie);}

    public void clearCategories() {
        categories = new ArrayList<>();
    }

    public List<Categorie> getCategories() {
        return categories;
    }

    public List<String> getCategoriesString() {

       List<String> ret = new ArrayList<>();

        if (categories.isEmpty()) {return ret;}

        for (Categorie c: categories) {
            ret.add(c.getCategorie());
        }

        return ret;

    }
}
