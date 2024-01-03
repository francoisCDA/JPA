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

    @OneToOne
    @JoinColumn(name="info_tache_id",referencedColumnName = "id_info_tache" )
    private TaskInfo infoTache;


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
        return "\tTâche " +
                "id : " + id +
                ", '" + aFaire + '\'' +
                ", " + (complete ? "terminée" : "à finir") +
                '.';
    }

    public TaskInfo getInfoTache() {
        return infoTache;
    }

    public void setInfoTache(TaskInfo infoTache) {
        this.infoTache = infoTache;
    }
}
