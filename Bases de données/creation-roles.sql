/*================
Création des roles
================*/
CREATE ROLE 'connecte', 'adjoint', 'maire', 'prefet', 'appadmin', 'dbadmin';

/*=========================
Affectation des permissions
=========================*/
-- Role Utilisateur connecté : on donne l'accès en lecture a la BDD
GRANT SELECT
    ON bdSAE.*
    TO 'connecte'
;

-- Role adjoint au maire : Peut modifier des données annuelles
GRANT connecte
    TO 'adjoint'
;

GRANT UPDATE(nbMaison, nbAppart, prixMoyen, prixM2Moyen, SurfaceMoy)
    ON bdSAE.DonneesAnnuelles
    TO 'adjoint'
;

-- Role maire : Peut créer des données annuelles, des gares ou des aéroports et les modifier
GRANT adjoint
    TO 'maire';

GRANT UPDATE, INSERT
    ON bdSAE.DonneesAnnuelles
    TO 'maire'
;

GRANT INSERT, UPDATE
    ON bdSAE.Gare
    TO 'maire'
;

GRANT INSERT, UPDATE
    ON bdSAE.Aeroport
    TO 'maire'
;

-- Role préfet : peut gérer les données annuelles, gares, aéroport et communes
GRANT maire
    TO 'prefet'
;

GRANT DELETE
    ON bdSAE.DonneesAnnuelles
    TO 'prefet'
;

GRANT DELETE
    ON bdSAE.Gare
    TO 'prefet'
;

GRANT DELETE
    ON bdSAE.Aeroport
    TO 'prefet'
;

GRANT INSERT, UPDATE, DELETE
    ON bdSAE.Commune
    TO 'prefet'
;


-- Role administrateur de l'application : possède l'accès à toutes les tables de la base de donnée
GRANT INSERT, UPDATE, DELETE
    ON bdSAE.*
    TO 'appadmin'
;

-- Role administrateur de la BDD : Peut gérer l'entiereté de la BDD ainsi que transmetre des droits aux utilisateurs
GRANT CREATE, UPDATE, DELETE, ALTER, DROP, INSERT
    ON bdSAE.*
    TO 'dbadmin'
    WITH GRANT OPTION
;


/*==================
Test des permissions
==================*/

-- /!\ Ces instructions doivent être exécutés avec le compte root de la base de donnée

-- Creation des utilisateurs :

CREATE USER 'user_connecte'@'localhost' IDENTIFIED BY 'password';
CREATE USER 'user_adjoint'@'localhost' IDENTIFIED BY 'password';
CREATE USER 'user_maire'@'localhost' IDENTIFIED BY 'password';
CREATE USER 'user_prefet'@'localhost' IDENTIFIED BY 'password';
CREATE USER 'user_appadmin'@'localhost' IDENTIFIED BY 'password';
CREATE USER 'user_dbadmin'@'localhost' IDENTIFIED BY 'password';


-- Attribution des roles aux utilisateurs :

GRANT 'connecte' TO 'user_connecte'@'localhost';
GRANT 'adjoint' TO 'user_adjoint'@'localhost';
GRANT 'maire' TO 'user_maire'@'localhost';
GRANT 'prefet' TO 'user_prefet'@'localhost';
GRANT 'appadmin' TO 'user_appadmin'@'localhost';
GRANT 'dbadmin' TO 'user_dbadmin'@'localhost';

-- Définition des roles par défaut.

SET DEFAULT ROLE ALL TO 
	'user_connecte'@'localhost',
    'user_adjoint'@'localhost',
    'user_maire'@'localhost',
    'user_prefet'@'localhost',
    'user_appadmin'@'localhost',
    'user_dbadmin'@'localhost';


/*============== 
Requêtes de test
==============*/

------------------------------
-- MySQL Workbench / user_connecte@localhost --
-----------------------------
SELECT * FROM bdSAE.Commune;
INSERT INTO Annee
VALUES (2024, 3.4);

------------------------------
-- MySQL Workbench / user_adjoint@localhost --
-----------------------------
UPDATE bdSAE.DonneesAnnuelles SET nbMaison = 100 WHERE lAnnee = 2018 AND laCommune = 22005;
INSERT INTO Gare 
VALUES  (2365, "Guégon", false, true, 56070);

------------------------------
-- MySQL Workbench / user_maire@localhost --
-----------------------------
INSERT INTO Gare 
VALUES  (2365, "Guégon", false, true, 56070);
CREATE TABLE TestMaire (
	idMaire INT PRIMARY KEY
);

------------------------------
-- MySQL Workbench / user_prefet@localhost --
-----------------------------
DELETE FROM bdSAE.Gare WHERE codeGare = 2365;
INSERT INTO Annee
VALUES (2024, 3.4);

------------------------------
-- MySQL Workbench / user_appadmin@localhost --
-----------------------------
INSERT INTO Annee
VALUES (2024, 3.4);
CREATE TABLE TestAdmin (
	idAdmin INT PRIMARY KEY
);

------------------------------
-- MySQL Workbench / user_dbadmin@localhost --
-----------------------------
CREATE TABLE TestAdmin (
	idAdmin INT PRIMARY KEY
);
DROP TABLE TestAdmin;
CREATE DATABASE testDBAdmin;