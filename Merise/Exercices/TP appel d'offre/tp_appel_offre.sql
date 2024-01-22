drop DATABASE if EXISTS modelisation_appel_offre;

CREATE DATABASE IF NOT EXISTS modelisation_appel_offre;

use modelisation_appel_offre;


CREATE TABLE fournisseur(
   num_fournisseur INT,
   nom_fourn VARCHAR(100),
   adresse_fourn VARCHAR(200),
   cp_fourn VARCHAR(5),
   ville_fourn VARCHAR(50),
   PRIMARY KEY(num_fournisseur)
);

CREATE TABLE produit(
   num_produit INT,
   nom_produit VARCHAR(50),
   prix_produit DECIMAL(15,2),
   vente BOOLEAN,
   PRIMARY KEY(num_produit)
);

CREATE TABLE appel_offre(
   num_appel_offre INT,
   date_ouverture DATE,
   date_fermeture DATE,
   PRIMARY KEY(num_appel_offre)
);

CREATE TABLE offre_ferme(
   id_offre_ferme INT,
   prix_offre_ferme DECIMAL(15,2),
   num_appel_offre INT NOT NULL,
   num_fournisseur INT NOT NULL,
   PRIMARY KEY(id_offre_ferme),
   FOREIGN KEY(num_appel_offre) REFERENCES appel_offre(num_appel_offre),
   FOREIGN KEY(num_fournisseur) REFERENCES Fournisseur(num_fournisseur)
);

CREATE TABLE contrat(
   num_contrat INT,
   quantite INT,
   date_contrat DATE,
   valid_contrat DATE,
   num_fournisseur INT NOT NULL,
   PRIMARY KEY(num_contrat),
   FOREIGN KEY(num_fournisseur) REFERENCES Fournisseur(num_fournisseur)
);

CREATE TABLE offre_produit(
   num_produit INT,
   num_appel_offre INT,
   PRIMARY KEY(num_produit, num_appel_offre),
   FOREIGN KEY(num_produit) REFERENCES produit(num_produit),
   FOREIGN KEY(num_appel_offre) REFERENCES appel_offre(num_appel_offre)
);

CREATE TABLE contrat_produit(
   num_produit INT,
   num_contrat INT,
   PRIMARY KEY(num_produit, num_contrat),
   FOREIGN KEY(num_produit) REFERENCES produit(num_produit),
   FOREIGN KEY(num_contrat) REFERENCES contrat(num_contrat)
);


/************************************
1 - Sélectionner tous les produits avec leur prix.
*****************************************/

select nom_produit, prix_produit from produit;

/************************************
2 - Trouver le nombre total d'offres disponibles.
*****************************************/

select count(*) from appel_offre;

/************************************
3 - Lister tous les fournisseurs situés à Paris.
*****************************************/

select num_fournisseur from fournisseur where ville_fourn = 'PARIS';

/************************************
4 - Afficher les offres et la quantité totale de produits demandés pour chaque offre.
*****************************************/

select DISTINCT num_appel_offre, count(num_produit) from 


/************************************
5 - Trouver les produits qui n'ont pas encore été inclus dans un contrat.
*****************************************/


/************************************
6 - Afficher les contrats signés par chaque fournisseur avec la date de signature.
*****************************************/
/************************************
7 - Lister les offres avec les noms des produits correspondants.
*****************************************/
/************************************
8 - Trouver le fournisseur qui a effectué le plus d'offres.
*****************************************/
/************************************
9 - Calculer le montant total des contrats par produit.
*****************************************/
/************************************
10 - Trouver les offres qui n'ont pas été signées avant leur date de clôture et les fournisseurs responsables.
*****************************************/

