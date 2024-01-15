package entity;

import javax.persistence.*;

@Entity
@Table(name = "contrat_produit")
public class ContratProduit {
    @EmbeddedId
    private ContratProduitId id;

    @MapsId("numProduit")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "num_produit", nullable = false)
    private Produit numProduit;

    @MapsId("numContrat")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "num_contrat", nullable = false)
    private Contrat numContrat;

    public ContratProduitId getId() {
        return id;
    }

    public void setId(ContratProduitId id) {
        this.id = id;
    }

    public Produit getNumProduit() {
        return numProduit;
    }

    public void setNumProduit(Produit numProduit) {
        this.numProduit = numProduit;
    }

    public Contrat getNumContrat() {
        return numContrat;
    }

    public void setNumContrat(Contrat numContrat) {
        this.numContrat = numContrat;
    }

}