package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fournisseur")
public class Fournisseur {
    @Id
    @Column(name = "num_fournisseur", nullable = false)
    private Integer id;

    @Column(name = "nom_fourn", length = 100)
    private String nomFourn;

    @Column(name = "adresse_fourn", length = 200)
    private String adresseFourn;

    @Column(name = "cp_fourn", length = 5)
    private String cpFourn;

    @Column(name = "ville_fourn", length = 50)
    private String villeFourn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomFourn() {
        return nomFourn;
    }

    public void setNomFourn(String nomFourn) {
        this.nomFourn = nomFourn;
    }

    public String getAdresseFourn() {
        return adresseFourn;
    }

    public void setAdresseFourn(String adresseFourn) {
        this.adresseFourn = adresseFourn;
    }

    public String getCpFourn() {
        return cpFourn;
    }

    public void setCpFourn(String cpFourn) {
        this.cpFourn = cpFourn;
    }

    public String getVilleFourn() {
        return villeFourn;
    }

    public void setVilleFourn(String villeFourn) {
        this.villeFourn = villeFourn;
    }

}