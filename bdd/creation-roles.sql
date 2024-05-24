CREATE ROLE 'connecte', 'adjoint', 'maire', 'prefet', 'appadmin', 'dbadmin';

GRANT SELECT
    ON bdSAE.*
    TO 'connecte'
;

GRANT 'connecte'
    TO 'adjoint'
;

GRANT UPDATE(nbMaison, nbAppart, prixMoyen, prixM2Moyen, SurfaceMoy)
    ON bdSAE.DonneesAnnuelles
    TO 'adjoint'
;

GRANT 'adjoint'
    TO 'maire';

GRANT CREATE, UPDATE
    ON bdSAE.DonneesAnnuelles
    TO 'maire'
;

GRANT CREATE, UPDATE
    ON bdSAE.Gare
    TO 'maire'
;

GRANT CREATE, UPDATE
    ON bdSAE.Aeroport
    TO 'maire'
;

GRANT 'maire'
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

GRANT CREATE, UPDATE, DELETE
    ON bdSAE.Communes
    TO 'prefet'
;

GRANT CREATE, UPDATE, DELETE
    ON bdSAE.*
    TO 'appadmin'

GRANT CREATE, UPDATE, DELETE, ALTER, DROP
    ON bdSAE.*
    TO 'dbadmin'
    WITH GRANT OPTION
;



-- Parties pour les VUES :

-- VUES pour gerer les contraintes :

-- VUE: Vue pour s'assurer que les communes ont une population non negative
CREATE VIEW V_PopulationPositive AS
SELECT * FROM Communes WHERE population >= 0;

-- VUE: Vue pour verifier que les prix des maisons et appartements ne sont pas negatifs
CREATE VIEW V_PrixNonNegatifs AS
SELECT * FROM DonneesAnnuelles WHERE nbMaison >= 0 AND nbAppart >= 0 AND prixMoyen >= 0 AND prixM2Moyen >= 0 AND SurfaceMoy >= 0;


-- VUES pour gerer les infos derivables :

-- VUE: Vue pour calculer la moyenne des prix des maisons et appartements par commune
CREATE VIEW V_MoyennePrixParCommune AS
SELECT commune, AVG(prixMoyen) AS PrixMoyen, AVG(prixM2Moyen) AS PrixM2Moyen
FROM DonneesAnnuelles
GROUP BY commune;

-- VUE: Vue pour lister les gares et aéroports par commune
CREATE VIEW V_GaresAeroportsParCommune AS
SELECT c.commune, g.nom AS Gare, a.nom AS Aeroport
FROM Communes c
LEFT JOIN Gare g ON c.commune = g.commune
LEFT JOIN Aeroport a ON c.commune = a.commune;



-- Test des PERMISSIONS :


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


-- Tests de permissions :

-- Test des permissions pour l'utilisateur 'user_connecte'
SELECT * FROM bdSAE.Communes;

-- Test des permissions pour l'utilisateur 'user_adjoint'
UPDATE bdSAE.DonneesAnnuelles SET nbMaison = 100 WHERE id = 1;

-- Test des permissions pour l'utilisateur 'user_maire'
CREATE TABLE TestMaire (id INT);

-- Test des permissions pour l'utilisateur 'user_prefet'
DELETE FROM bdSAE.DonneesAnnuelles WHERE id = 1;

-- Test des permissions pour l'utilisateur 'user_appadmin'
CREATE TABLE TestAppAdmin (id INT);

-- Test des permissions pour l'utilisateur 'user_dbadmin'
DROP TABLE bdSAE.TestMaire;
