package entity;

import javax.persistence.*;

@Entity
@Table(name = "offre_produit")
public class OffreProduit {
    @EmbeddedId
    private OffreProduitId id;

    @MapsId("numProduit")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "num_produit", nullable = false)
    private Produit numProduit;

    public OffreProduitId getId() {
        return id;
    }

    public void setId(OffreProduitId id) {
        this.id = id;
    }

    public Produit getNumProduit() {
        return numProduit;
    }

    public void setNumProduit(Produit numProduit) {
        this.numProduit = numProduit;
    }

}