package models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="commande")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name="commande_produits",
            joinColumns = @JoinColumn(name = "commande_id"),
            inverseJoinColumns = @JoinColumn(name = "produit_id")
    )
    private List<Produit> produits;

    private Double total;

    private LocalDate dateCommande;

    @OneToOne(mappedBy = "commande",cascade = CascadeType.ALL)
    private Adresse adresse;




    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
