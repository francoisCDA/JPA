package entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PersonneprojetId implements Serializable {
    private static final long serialVersionUID = 2015558892022126169L;
    @Column(name = "personne_id", nullable = false)
    private Integer personneId;

    @Column(name = "projet_id", nullable = false)
    private Integer projetId;

    public Integer getPersonneId() {
        return personneId;
    }

    public void setPersonneId(Integer personneId) {
        this.personneId = personneId;
    }

    public Integer getProjetId() {
        return projetId;
    }

    public void setProjetId(Integer projetId) {
        this.projetId = projetId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PersonneprojetId entity = (PersonneprojetId) o;
        return Objects.equals(this.personneId, entity.personneId) &&
                Objects.equals(this.projetId, entity.projetId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personneId, projetId);
    }

}