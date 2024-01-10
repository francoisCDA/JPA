package models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="commentaire")
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_commentaire", nullable = false)
    private Long id;

    private String avis;

    private LocalDate date;

    private int note;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Produit produit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public String toString() {
        return  '\'' + avis +
                "', le " + date +
                ", note " + note +
                "/5.";
    }
}
