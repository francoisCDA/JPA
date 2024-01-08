package model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_client")
   private Long id;

   private String nom;

   private String prenom;

   private LocalDate dateNaissance;

   @ManyToOne
   private Agence agence;

   @ManyToMany
   @JoinTable(joinColumns = @JoinColumn(name="client_id"), inverseJoinColumns = @JoinColumn(name="compte_id"))
   private List<Compte> comptes;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getNom() {
      return nom;
   }

   public void setNom(String nom) {
      this.nom = nom;
   }

   public String getPrenom() {
      return prenom;
   }

   public void setPrenom(String prenom) {
      this.prenom = prenom;
   }

   public LocalDate getDateNaissance() {
      return dateNaissance;
   }

   public void setDateNaissance(LocalDate dateNaissance) {
      this.dateNaissance = dateNaissance;
   }

   public Agence getAgence() {
      return agence;
   }

   public void setAgence(Agence agence) {
      this.agence = agence;
   }

   public List<Compte> getComptes() {
      return comptes;
   }

   public void setComptes(List<Compte> comptes) {
      this.comptes = comptes;
   }

   @Override
   public String toString() {
      return "Client{" +
              "id=" + id +
              ", nom='" + nom + '\'' +
              ", prenom='" + prenom + '\'' +
              ", dateNaissance=" + dateNaissance +
              ", agence=" + agence +
              ", comptes=" + comptes +
              '}';
   }
}
