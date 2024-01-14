/**********************
	Exo examens
***********************/
DROP DATABASE IF EXISTS modelisation_examen;
CREATE DATABASE IF NOT EXISTS modelisation_examen;

USE  modelisation_examen; 

/****************************
	Création des tables
*****************************/

CREATE TABLE examen(
   id_examen INT AUTO_INCREMENT,
   nom_examen VARCHAR(50),
   PRIMARY KEY(id_examen)
);

CREATE TABLE epreuve(
   id_epreuve INT AUTO_INCREMENT,
   discipline VARCHAR(50) NOT NULL,
   coef INT NOT NULL  CHECK(coef > 0),
   date_epreuve DATETIME NOT NULL,
   id_examen INT NOT NULL,
   PRIMARY KEY(id_epreuve),
   FOREIGN KEY(id_examen) REFERENCES examen(id_examen)
);


CREATE TABLE bordereau(
   id_bordereau INT AUTO_INCREMENT,
   PRIMARY KEY(id_bordereau)
);

CREATE TABLE ville_de_france(
   code_insee CHAR(5),
   nom_ville VARCHAR(60) NOT NULL,
   PRIMARY KEY(code_insee)
);

CREATE TABLE jury(
   id_jury INT AUTO_INCREMENT,
   PRIMARY KEY(id_jury)
);

CREATE TABLE com_redac(
   id_com_redac INT AUTO_INCREMENT,
   date_commi DATE,
   PRIMARY KEY(id_com_redac)
);

CREATE TABLE correcteur(
   id_correcteur INT AUTO_INCREMENT,
   PRIMARY KEY(id_correcteur)
);

CREATE TABLE etablis_scolaire(
   code_etablis CHAR(12),
   nom_etablis VARCHAR(50) NOT NULL,
   adresse_etablis VARCHAR(200) NOT NULL,
   code_insee CHAR(5) NOT NULL,
   PRIMARY KEY(code_etablis),
   FOREIGN KEY(code_insee) REFERENCES ville_de_france(code_insee)
);

CREATE TABLE enseignant(
   numen CHAR(13),
   nom_ens VARCHAR(50),
   telephone_ens VARCHAR(10),
   adresse_ens VARCHAR(200),
   code_insee CHAR(5) NOT NULL,
   code_etablis CHAR(12) NOT NULL,
   PRIMARY KEY(numen),
   FOREIGN KEY(code_insee) REFERENCES ville_de_france(code_insee),
   FOREIGN KEY(code_etablis) REFERENCES etablis_scolaire(code_etablis)
);

CREATE TABLE eleve(
   id_eleve INT AUTO_INCREMENT,
   nom_eleve VARCHAR(50) NOT NULL,
   prenom_eleve VARCHAR(50),
   date_naissance DATE NOT NULL,
   code_etablis CHAR(12) NOT NULL,
   PRIMARY KEY(id_eleve),
   FOREIGN KEY(code_etablis) REFERENCES etablis_scolaire(code_etablis)
);


CREATE TABLE note(
   id_note INT AUTO_INCREMENT,
   note DECIMAL(4,2) CHECK(note > 0),
   id_bordereau INT NOT NULL,
   id_epreuve INT NOT NULL,
   id_eleve INT NOT NULL,
   PRIMARY KEY(id_note),
   FOREIGN KEY(id_bordereau) REFERENCES bordereau(id_bordereau),
   FOREIGN KEY(id_epreuve) REFERENCES epreuve(id_epreuve),
   FOREIGN KEY(id_eleve) REFERENCES eleve(id_eleve)
);

CREATE TABLE dossier_inscription(
   numero_dossier CHAR(13),
   date_depot DATE,
   id_examen INT NOT NULL,
   id_eleve INT NOT NULL,
   PRIMARY KEY(numero_dossier),
   UNIQUE(id_eleve),
   FOREIGN KEY(id_examen) REFERENCES examen(id_examen),
   FOREIGN KEY(id_eleve) REFERENCES eleve(id_eleve)
);


CREATE TABLE convocation(
   id_epreuve INT,
   numen CHAR(13),
   id_correcteur INT,
   id_com_redac INT,
   id_jury INT,
   PRIMARY KEY(id_epreuve, numen, id_correcteur, id_com_redac, id_jury),
   FOREIGN KEY(id_epreuve) REFERENCES epreuve(id_epreuve),
   FOREIGN KEY(numen) REFERENCES enseignant(numen),
   FOREIGN KEY(id_correcteur) REFERENCES correcteur(id_correcteur),
   FOREIGN KEY(id_com_redac) REFERENCES com_redac(id_com_redac),
   FOREIGN KEY(id_jury) REFERENCES jury(id_jury)
);

CREATE TABLE examination(
   id_bordereau INT,
   id_jury INT,
   PRIMARY KEY(id_bordereau, id_jury),
   FOREIGN KEY(id_bordereau) REFERENCES bordereau(id_bordereau),
   FOREIGN KEY(id_jury) REFERENCES jury(id_jury)
);


/**************************************** 
				FAKE DATA 
*********************************************/

INSERT INTO ville_de_france (code_insee, nom_ville) VALUES
('12345', 'Paris'),
('67890', 'Marseille'),
('13579', 'Lyon'),
('24680', 'Bordeaux'),
('98765', 'Nice'),
('11111', 'Lille'),
('54321', 'Toulouse'),
('99999', 'Strasbourg'),
('77777', 'Nantes'),
('44444', 'Rennes');


INSERT INTO etablis_scolaire (code_etablis, nom_etablis, adresse_etablis, code_insee) VALUES
('E001', 'Lycée A', '123 Rue A', '12345'),
('E002', 'Lycée B', '456 Rue B', '67890'),
('E003', 'Lycée C', '789 Rue C', '13579'),
('E004', 'Lycée D', '101 Rue D', '24680'),
('E005', 'Lycée E', '202 Rue E', '98765'),
('E006', 'Lycée F', '303 Rue F', '11111'),
('E007', 'Lycée G', '404 Rue G', '54321'),
('E008', 'Lycée H', '505 Rue H', '99999'),
('E009', 'Lycée I', '606 Rue I', '77777'),
('E010', 'Lycée J', '707 Rue J', '44444');

-- '12345','67890', '13579', '24680', '98765', '11111', '54321', '99999', '77777', '44444'


INSERT INTO examen (nom_examen) VALUES
('Bac-pro'),
('BTS'),
('BDC'),
('Baccalauréat');


/****************************************** boucles pour générer des données ******************************/
INSERT INTO epreuve (discipline, coef, date_epreuve, id_examen)
SELECT
    CASE
        WHEN RAND() < 0.2 THEN 'Mathématiques'
        WHEN RAND() < 0.4 THEN 'Français'
        WHEN RAND() < 0.6 THEN 'Histoire-Géographie'
        WHEN RAND() < 0.8 THEN 'Physique-Chimie'
        ELSE 'SVT'
    END,
    RAND() * 2 + 1, -- Coefficient aléatoire entre 1 et 3
    CURRENT_TIMESTAMP - 200 + INTERVAL FLOOR(RAND() * 30) DAY, -- Date aléatoire dans les 30 prochains jours
    FLOOR(RAND() * 4) + 1 -- Id-examen aléatoire entre 1 et 4
FROM
    information_schema.tables
LIMIT 25;



/************* Procéudre pour générer des id *****************************************/
DELIMITER //
CREATE PROCEDURE InsertBordereau()
BEGIN
    DECLARE counter INT DEFAULT 0;

    WHILE counter < 20 DO
        INSERT INTO bordereau VALUES(DEFAULT);
        
        SET counter = counter + 1;
    END WHILE;
END //

DELIMITER ;



DELIMITER //
CREATE PROCEDURE InsertJury()
BEGIN
    DECLARE counter INT DEFAULT 0;

    WHILE counter < 10 DO
        INSERT INTO bordereau VALUES(DEFAULT);
        INSERT INTO correcteur VALUES(DEFAULT);
        SET counter = counter + 1;
    END WHILE;
END //

DELIMITER ;


-- Appel des procédures pour insérer des données
CALL InsertBordereau();
CALL InsertJury();



DELIMITER //
CREATE PROCEDURE InsertComRedac()
BEGIN
    DECLARE counter INT DEFAULT 0;

    WHILE counter < 20 DO
        INSERT INTO com_redac (date_commi) VALUES (CURRENT_DATE() - 400 + INTERVAL FLOOR(RAND() * 30) DAY);
        SET counter = counter + 1;
    END WHILE;
END //
DELIMITER ;

-- Appel de la procédure pour insérer 20 fois
CALL InsertComRedac();

-- Générer 20 enseignants avec les profils spécifiés
INSERT INTO enseignant (numen, nom_ens, telephone_ens, adresse_ens, code_insee, code_etablis)
VALUES
('A123456789', 'Dupont', '0123456789', '123 Rue D', '12345', 'E001'),
('B987654321', 'Martin', '0987654321', '456 Rue E', '67890', 'E002'),
('C567890123', 'Dubois', '0345678901', '789 Rue F', '13579', 'E003'),
('D111111111', 'Lefevre', '0456789012', '101 Rue G', '24680', 'E004'),
('E222222222', 'Leroy', '0567890123', '202 Rue H', '98765', 'E005'),
('F333333333', 'Moreau', '0678901234', '303 Rue I', '11111', 'E006'),
('G444444444', 'Simon', '0789012345', '404 Rue J', '54321', 'E007'),
('H555555555', 'Lefort', '0890123456', '505 Rue K', '99999', 'E008'),
('I666666666', 'Roux', '0901234567', '606 Rue L', '77777', 'E009'),
('J777777777', 'Fournier', '0012345678', '707 Rue M', '44444', 'E010'),
('K888888888', 'Girard', '0123456789', '808 Rue N', '12345', 'E001'),
('L999999999', 'Lefevre', '0234567890', '909 Rue O', '67890', 'E002'),
('M111222333', 'Renaud', '0345678901', '111 Rue P', '13579', 'E003'),
('N444555666', 'Leclerc', '0456789012', '222 Rue Q', '24680', 'E004'),
('O777888999', 'Roy', '0567890123', '333 Rue R', '98765', 'E005'),
('P111222333', 'Andre', '0678901234', '444 Rue S', '11111', 'E006'),
('Q444555666', 'Marchand', '0789012345', '555 Rue T', '54321', 'E007'),
('R777888999', 'Barbier', '0890123456', '666 Rue U', '99999', 'E008'),
('S111222333', 'Perrin', '0901234567', '777 Rue V', '77777', 'E009'),
('T444555666', 'Lemoine', '0012345678', '888 Rue W', '44444', 'E010');



-- Générer 30 élèves avec des profils aléatoires
INSERT INTO eleve (nom_eleve, prenom_eleve, date_naissance, code_etablis)
VALUES
('Durand', 'Jean', '2005-05-10', 'E001'),
('Lefevre', 'Marie', '2005-07-15', 'E002'),
('Roux', 'Pierre', '2005-09-20', 'E003'),
('Dubois', 'Sophie', '2005-02-08', 'E004'),
('Martin', 'Lucas', '2005-11-25', 'E005'),
('Fournier', 'Emma', '2005-03-12', 'E006'),
('Leroy', 'Louis', '2005-06-18', 'E007'),
('Moreau', 'Eva', '2005-08-22', 'E008'),
('Girard', 'Thomas', '2005-10-05', 'E009'),
('Lefort', 'Camille', '2005-12-30', 'E010'),
('Renaud', 'Alexandre', '2005-01-17', 'E001'),
('Leclerc', 'Alice', '2005-04-02', 'E002'),
('Roy', 'Noah', '2005-07-07', 'E003'),
('Andre', 'Léa', '2005-09-14', 'E004'),
('Marchand', 'Hugo', '2005-11-28', 'E005'),
('Barbier', 'Chloé', '2005-02-15', 'E006'),
('Perrin', 'Enzo', '2005-05-23', 'E007'),
('Lemoine', 'Clara', '2005-08-10', 'E008'),
('Giraud', 'Mathis', '2005-10-30', 'E009'),
('Lopez', 'Manon', '2005-12-05', 'E010'),
('Meyer', 'Nathan', '2005-01-25', 'E001'),
('Colin', 'Inès', '2005-04-20', 'E002'),
('Caron', 'Théo', '2005-07-27', 'E003'),
('Clement', 'Mila', '2005-09-02', 'E004'),
('Legrand', 'Gabriel', '2005-11-10', 'E005'),
('Lefebvre', 'Zoé', '2005-03-05', 'E006'),
('Perrot', 'Arthur', '2005-06-13', 'E007'),
('Robin', 'Elsa', '2005-08-17', 'E008'),
('Garnier', 'Tom', '2005-10-22', 'E009'),
('Leroux', 'Jade', '2005-12-15', 'E010');



DELIMITER //

CREATE PROCEDURE GenerateNotes()
BEGIN
    DECLARE counter INT DEFAULT 1;
    DECLARE id_bordereau_val INT;
    DECLARE id_epreuve_val INT;
    DECLARE id_eleve_val INT;

    WHILE counter <= 200 DO
        -- Calcul des valeurs pour id_bordereau, id_epreuve et id_eleve
        SET id_bordereau_val = FLOOR((counter - 1) / 5) + 1;
        SET id_epreuve_val = (counter - 1) % 25 + 1;
        SET id_eleve_val = (counter - 1) % 30 + 1;

        -- Insertion de la note avec des valeurs aléatoires entre 10 et 20
        INSERT INTO note (note, id_bordereau, id_epreuve, id_eleve)
        VALUES (RAND() * 10 + 10, id_bordereau_val, id_epreuve_val, id_eleve_val);

        SET counter = counter + 1;
    END WHILE;
END //

DELIMITER ;

-- Appel de la procédure pour générer des notes
CALL GenerateNotes();



INSERT INTO dossier_inscription (numero_dossier, date_depot, id_examen, id_eleve)
VALUES
('D001', '2022-01-05', 1, 1),
('D002', '2022-01-06', 4, 2),
('D003', '2022-01-07', 3, 30),
('D004', '2022-02-10', 2, 4),
('D005', '2022-02-15', 1, 25),
('D006', '2022-03-20', 3, 6),
('D007', '2022-03-25', 4, 27),
('D008', '2022-04-30', 2, 8),
('D009', '2022-05-05', 1, 29),
('D010', '2022-06-10', 4, 10),
('D011', '2022-06-15', 3, 11),
('D012', '2022-07-20', 2, 12),
('D013', '2022-08-25', 1, 13),
('D014', '2022-08-30', 3, 14),
('D015', '2022-09-04', 4, 15),
('D016', '2022-10-09', 2, 16),
('D017', '2022-10-14', 1, 17),
('D018', '2022-11-19', 3, 18),
('D019', '2022-11-24', 4, 19),
('D020', '2022-12-29', 1, 20);



-- Générer 20 convocations avec des contraintes
INSERT INTO convocation (id_epreuve, numen, id_correcteur, id_com_redac, id_jury)
VALUES
(1, 'A123456789', 1, 1, NULL),
(2, 'B987654321', 2, 2, 2),
(3, 'C567890123', 3, NULL, 3),
(4, 'D111111111', NULL, 4, 4),
(5, 'E222222222', 5, 5, NULL),
(1, 'F333333333', 6, 6, 6),
(2, 'G444444444', 7, NULL, 7),
(3, 'H555555555', NULL, 8, 8),
(4, 'I666666666', 9, 9, NULL),
(5, 'J777777777', 10, 10, 10),
(2, 'K888888888', NULL, NULL, 1),
(12, 'L999999999', 2, 2, 2),
(3, 'M111222333', 3, NULL, 3),
(7, 'N444555666', 4, 4, 4),
(15, 'A123456789', NULL, 5, 5),
(3, 'B987654321', 6, NULL, 6),
(1, 'C567890123', 7, 7, NULL),
(8, 'D111111111', NULL, 8, 8),
(14, 'E222222222', 9, NULL, NULL),
(2, 'F333333333', 10, 10, NULL);


INSERT INTO examination (id_bordereau, id_jury)
VALUES
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 1),
(12, 2),
(13, 3),
(14, 4),
(15, 5),
(16, 6),
(17, 7),
(18, 8),
(19, 9),
(20, 10);
