/*
SAE 2.04, partie 2 : Exploitation d'une base de données, administration
Guhéneuf-Le Brec Nathan
Emeraud Jean-Louis
Patinec-Haxel François
Gomez--Jego Iñaki
Groupe 1A
*/

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
SELECT * 
FROM bdSAE.Commune
;
/*
1207 tuples sélectionnés <-> 1207 row(s) returned

# idCommune, nomCommune, leDepartement
'22001', 'ALLINEUC', '22'
'22002', 'ANDEL', '22'
'22003', 'AUCALEUC', '22'
'22004', 'BEGARD', '22'
'22005', 'BELLE-ISLE-EN-TERRE', '22'
'22006', 'BERHET', '22'
'22008', 'BOBITAL', '22'
'22009', 'LE BODEO', '22'
'22011', 'BOQUEHO', '22'
'22012', 'LA BOUILLIE', '22'
*/ 

INSERT INTO Annee VALUES (2024, 3.4)
;
/*
Error Code: 1142. INSERT command denied to user 'user_connecte'@'localhost' for table 'annee'
*/

------------------------------
-- MySQL Workbench / user_adjoint@localhost --
-----------------------------
UPDATE bdSAE.DonneesAnnuelles 
SET nbMaison = 100 
WHERE lAnnee = 2018 
AND laCommune = 22005
;
/*
1 row(s) affected Rows matched: 1  Changed: 1  Warnings: 0
*/

INSERT INTO Gare VALUES  (2365, "Guégon", false, true, 56070)
;
/*
Error Code: 1142. INSERT command denied to user 'user_adjoint'@'localhost' for table 'gare'
*/

------------------------------
-- MySQL Workbench / user_maire@localhost --
-----------------------------
INSERT INTO Gare VALUES  (2365, "Guégon", false, true, 56070)
;
/*
1 tuple ajouté <-> 1 row(s) affected
*/

CREATE TABLE TestMaire (
	idMaire INT PRIMARY KEY
)
;
/*
Error Code: 1142. CREATE command denied to user 'user_maire'@'localhost' for table 'testmaire'
*/

------------------------------
-- MySQL Workbench / user_prefet@localhost --
-----------------------------
DELETE FROM bdSAE.Gare 
WHERE codeGare = 2365
;
/*
1 tuple effacé <-> 1 row(s) affected
*/

INSERT INTO Annee VALUES (2024, 3.4)
;
/*
Error Code: 1142. INSERT command denied to user 'user_prefet'@'localhost' for table 'annee'
*/

------------------------------
-- MySQL Workbench / user_appadmin@localhost --
-----------------------------
INSERT INTO Annee VALUES (2024, 3.4)
;
/*
1 tuple ajouté <-> 1 row(s) affected
*/

CREATE TABLE TestAdmin (
	idAdmin INT PRIMARY KEY
)
;
/*
Error Code: 1142. CREATE command denied to user 'user_appadmin'@'localhost' for table 'testadmin'
*/

------------------------------
-- MySQL Workbench / user_dbadmin@localhost --
-----------------------------
CREATE TABLE TestAdmin (
	idAdmin INT PRIMARY KEY
)
;
/*
1 table créée <-> 19:19:08	CREATE TABLE TestAdmin (  idAdmin INT PRIMARY KEY )	0 row(s) affected	0.078 sec
*/

DROP TABLE TestAdmin
;
/*
1 table effacée <-> 19:20:29	DROP TABLE TestAdmin	0 row(s) affected	0.031 sec
*/

CREATE DATABASE testDBAdmin
;
/*
Error Code: 1044. Access denied for user 'user_dbadmin'@'localhost' to database 'testdbadmin'
*/