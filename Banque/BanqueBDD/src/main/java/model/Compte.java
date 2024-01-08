package model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compte")
    private Long id;

    @Column(nullable = false)
    private String libelle;

    @Column(nullable = false,length = 28)
    private String iban;

    @Column(precision = 2)
    private Double solde;

    @ManyToMany(mappedBy = "comptes")
    private List<Client> clients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", iban='" + iban + '\'' +
                ", solde=" + solde +
                '}';
    }
}
