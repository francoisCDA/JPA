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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="info_tache_id",referencedColumnName = "id_info_tache" )
    private TaskInfo infoTache;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Utilisateur utilisateur;


    @ManyToMany(cascade = CascadeType.ALL)
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
        return "\t-> " +
                "id : " + id +
                ", " + aFaire +
                ", " + (complete ? "terminée" : "à finir") +
                ", utilisateur : " + utilisateur.getPseudo() +
                ",\n\t échéance : " + infoTache.getEcheance() +
                ", priorité : " + infoTache.getPriorite() +
                ", détails : " + infoTache.getDescription() +
                ",\n\t categories : " + categories.toString() +
                '.';
    }

    public TaskInfo getInfoTache() {
        return infoTache;
    }

    public void setInfoTache(TaskInfo infoTache) {
        this.infoTache = infoTache;
    }
}
