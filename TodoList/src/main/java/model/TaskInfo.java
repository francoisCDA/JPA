package model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "todo_info")
public class TaskInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_info_tache")
    private Long id;

    @Column(length = 1000)
    private String description;

    private LocalDate echeance;

    @Enumerated(EnumType.STRING)
    private Priorite priorite;

    @OneToOne(mappedBy = "infoTache")
    private Task tache;

    public TaskInfo() {
    }

    public TaskInfo(String description, LocalDate echeance, Priorite priorite) {
        this.description = description;
        this.echeance = echeance;
        this.priorite = priorite;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEcheance() {
        return echeance;
    }

    public void setEcheance(LocalDate echeance) {
        this.echeance = echeance;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }

    public Task getTache() {
        return tache;
    }

    public void setTache(Task tache) {
        this.tache = tache;
    }
}
