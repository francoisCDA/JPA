package model;

import org.hibernate.annotations.Columns;

import javax.persistence.*;

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


    public Task(String tache){
        aFaire = tache;
        complete = false;
    }

    public Task() {
        
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void completed(){
        complete = true;
    }

    @Override
    public String toString() {
        return "\t-> " +
                "id : " + id +
                ", '" + aFaire + '\'' +
                ", " + (complete ? "terminée" : "à finir") +
                ", échéance : " + infoTache.getEcheance() +
                ", priorité : " + infoTache.getPriorite() +
                ", détails : " + infoTache.getDescription() +
                '.';
    }

    public TaskInfo getInfoTache() {
        return infoTache;
    }

    public void setInfoTache(TaskInfo infoTache) {
        this.infoTache = infoTache;
    }
}
