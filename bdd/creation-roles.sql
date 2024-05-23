
-- Creation des roles
CREATE ROLE 'connecte', 'adjoint', 'maire', 'prefet', 'appadmin', 'dbadmin';

-- Droit de selection sur les utilisateurs connecte
GRANT SELECT
    ON bdSAE.*
    TO 'connecte'
;

-- Les droits du role inferieur sont appliques sur le role adjoint
GRANT 'connecte'
    TO 'adjoint'
;

-- L'adjoint peut modifier nbMaison, nbAppart, prixMoyen, prixM2Moyen, SurfaceMoy dans la table de donnees annuelles
GRANT UPDATE(nbMaison, nbAppart, prixMoyen, prixM2Moyen, SurfaceMoy)
    ON bdSAE.DonneesAnnuelles
    TO 'adjoint'
;

-- Les droits du role inferieur sont appliques sur le role maire
GRANT 'adjoint'
    TO 'maire';

-- Creation de donnees et mise à jour pour toute la table donnees annuelles
GRANT CREATE, UPDATE
    ON bdSAE.DonneesAnnuelles
    TO 'maire'
;

-- Creation de donnees et mise à jour pour toute la table Gare
GRANT CREATE, UPDATE
    ON bdSAE.Gare
    TO 'maire'
;

-- Creation de donnees et mise à jour pour toute la table aeroport
GRANT CREATE, UPDATE
    ON bdSAE.Aeroport
    TO 'maire'
;

-- Les droits du role inferieur sont appliques sur le role prefet
GRANT 'maire'
    TO 'prefet'
;

-- Suppresion pour toute la table donnees annuelles
GRANT DELETE
    ON bdSAE.DonneesAnnuelles
    TO 'prefet'
;

-- Suppression pour toute la table Gare
GRANT DELETE
    ON bdSAE.Gare
    TO 'prefet'
;

-- Suppresion pour toute la table aeroport
GRANT DELETE
    ON bdSAE.Aeroport
    TO 'prefet'
;

-- Creation, suppression de donnees et mise à jour pour toute la table Commune
GRANT CREATE, UPDATE, DELETE
    ON bdSAE.Communes
    TO 'prefet'
;

-- Creation, suppression de donnees et mise à jour pour toute la base de données
GRANT CREATE, UPDATE, DELETE
    ON bdSAE.*
    TO 'appadmin'

-- Creation, suppression de donnees, mise à jour et suppression de table pour toute la table donnees annuelles
GRANT CREATE, UPDATE, DELETE, ALTER, DROP
    ON bdSAE.*
    TO 'dbadmin'
    WITH GRANT OPTION
;