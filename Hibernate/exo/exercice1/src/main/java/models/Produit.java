package models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="produit")
public class Produit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marque;

    private String reference;

    @Column(name="date_achat")
    private LocalDate dateAchat;

    @Column(scale = 2)
    private double prix;

    private int stock;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    private List<Image> images;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Commentaire> avis;

    @ManyToMany(mappedBy = "produits")
    private List<Commande> commandes;


    public Produit() {
            images = new ArrayList<>();
            avis = new ArrayList<>();
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDate getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(LocalDate dateAchat) {
        this.dateAchat = dateAchat;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Produit " +
                "id : " + id +
                ", marque : '" + marque + '\'' +
                ", réf" + reference +
                '.';
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Commentaire> getAvis() {
        return avis;
    }

    public void setAvis(List<Commentaire> avis) {
        this.avis = avis;
    }

}
