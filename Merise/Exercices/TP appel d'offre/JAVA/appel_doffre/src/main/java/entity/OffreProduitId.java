package entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OffreProduitId implements Serializable {
    private static final long serialVersionUID = -5044812605676029057L;
    @Column(name = "num_produit", nullable = false)
    private Integer numProduit;

    @Column(name = "num_appel_offre", nullable = false)
    private Integer numAppelOffre;

    public Integer getNumProduit() {
        return numProduit;
    }

    public void setNumProduit(Integer numProduit) {
        this.numProduit = numProduit;
    }

    public Integer getNumAppelOffre() {
        return numAppelOffre;
    }

    public void setNumAppelOffre(Integer numAppelOffre) {
        this.numAppelOffre = numAppelOffre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OffreProduitId entity = (OffreProduitId) o;
        return Objects.equals(this.numAppelOffre, entity.numAppelOffre) &&
                Objects.equals(this.numProduit, entity.numProduit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numAppelOffre, numProduit);
    }

}