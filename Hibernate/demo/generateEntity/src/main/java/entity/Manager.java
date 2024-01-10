package entity;

import javax.persistence.*;

@Entity
@Table(name = "manager")
public class Manager {
    @Id
    @Column(name = "personne_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "personne_id", nullable = false)
    private Employe employe;

    @Column(name = "niveau")
    private Integer niveau;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

}