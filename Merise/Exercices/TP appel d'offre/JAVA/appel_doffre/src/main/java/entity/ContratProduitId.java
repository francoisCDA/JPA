package entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ContratProduitId implements Serializable {
    private static final long serialVersionUID = 2866360214789434712L;
    @Column(name = "num_produit", nullable = false)
    private Integer numProduit;

    @Column(name = "num_contrat", nullable = false)
    private Integer numContrat;

    public Integer getNumProduit() {
        return numProduit;
    }

    public void setNumProduit(Integer numProduit) {
        this.numProduit = numProduit;
    }

    public Integer getNumContrat() {
        return numContrat;
    }

    public void setNumContrat(Integer numContrat) {
        this.numContrat = numContrat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ContratProduitId entity = (ContratProduitId) o;
        return Objects.equals(this.numProduit, entity.numProduit) &&
                Objects.equals(this.numContrat, entity.numContrat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numProduit, numContrat);
    }

}