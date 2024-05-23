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