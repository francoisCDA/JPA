package entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "offre_ferme")
public class OffreFerme {
    @Id
    @Column(name = "id_offre_ferme", nullable = false)
    private Integer id;

    @Column(name = "prix_offre_ferme", precision = 15, scale = 2)
    private BigDecimal prixOffreFerme;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "num_fournisseur", nullable = false)
    private Fournisseur numFournisseur;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrixOffreFerme() {
        return prixOffreFerme;
    }

    public void setPrixOffreFerme(BigDecimal prixOffreFerme) {
        this.prixOffreFerme = prixOffreFerme;
    }

    public Fournisseur getNumFournisseur() {
        return numFournisseur;
    }

    public void setNumFournisseur(Fournisseur numFournisseur) {
        this.numFournisseur = numFournisseur;
    }

}