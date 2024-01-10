package entity;

import javax.persistence.*;

@Entity
@Table(name = "personneprojet")
public class Personneprojet {
    @EmbeddedId
    private PersonneprojetId id;

    @MapsId("personneId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "personne_id", nullable = false)
    private Personne personne;

    public PersonneprojetId getId() {
        return id;
    }

    public void setId(PersonneprojetId id) {
        this.id = id;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

}