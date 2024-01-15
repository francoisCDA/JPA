package entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contrat")
public class Contrat {
    @Id
    @Column(name = "num_contrat", nullable = false)
    private Integer id;

    @Column(name = "quantite")
    private Integer quantite;

    @Column(name = "date_contrat")
    private LocalDate dateContrat;

    @Column(name = "valid_contrat")
    private Boolean validContrat;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "num_fournisseur", nullable = false)
    private Fournisseur numFournisseur;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public LocalDate getDateContrat() {
        return dateContrat;
    }

    public void setDateContrat(LocalDate dateContrat) {
        this.dateContrat = dateContrat;
    }

    public Boolean getValidContrat() {
        return validContrat;
    }

    public void setValidContrat(Boolean validContrat) {
        this.validContrat = validContrat;
    }

    public Fournisseur getNumFournisseur() {
        return numFournisseur;
    }

    public void setNumFournisseur(Fournisseur numFournisseur) {
        this.numFournisseur = numFournisseur;
    }

}