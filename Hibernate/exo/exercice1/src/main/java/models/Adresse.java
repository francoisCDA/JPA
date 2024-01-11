package models;

import javax.persistence.*;

@Entity
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rue;

    private String ville;

    @Column(name = "code_postal")
    private String codePostal;

    @OneToOne
    private Commande commande;





    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
