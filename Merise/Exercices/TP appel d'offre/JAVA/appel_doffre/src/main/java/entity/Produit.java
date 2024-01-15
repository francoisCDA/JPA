package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "produit")
public class Produit {
    @Id
    @Column(name = "num_produit", nullable = false)
    private Integer id;

    @Column(name = "nom_produit", length = 50)
    private String nomProduit;

    @Column(name = "prix_produit", precision = 15, scale = 2)
    private BigDecimal prixProduit;

    @Column(name = "vente")
    private Boolean vente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public BigDecimal getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(BigDecimal prixProduit) {
        this.prixProduit = prixProduit;
    }

    public Boolean getVente() {
        return vente;
    }

    public void setVente(Boolean vente) {
        this.vente = vente;
    }

}