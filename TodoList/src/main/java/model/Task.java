package model;

import javax.persistence.*;

@Entity
@Table(name = "todo")
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String aFaire;

    private boolean complete;

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
}
