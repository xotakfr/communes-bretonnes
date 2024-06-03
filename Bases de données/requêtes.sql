/*
SAE 2.04, partie 2 : Exploitation d'une base de données, requêtes
Guhéneuf-Le Brec Nathan
Emeraud Jean-Louis
Patinec-Haxel François
Gomez--Jego Iñaki
Groupe 1A
*/

-- Les requêtes sont proposées dans le même ordre que suggéré dans la consigne. 
-- Dans le cas où plus de 10 tuples sont sélectionnés, on n'affiche que les 10 premiers tuples (sauf question 3) dans les commentaires par soucis de lissibilité
-- cependant on affiche aussi le quantité total de tuple sélectionnés dans les commentaires ainsi il est possible de vérifier en exécutant le programme.

/* Question 1 : Afficher le numéro de département et le nom de tous les aéroports situés dans un département dont l'investissement culturel en 2019 est strictement supérieur à 10000000€. */

SELECT DISTINCT leDepartement, nom
FROM Aeroport, Departement
WHERE investissementCulturel2019 > 10000000
AND Departement.idDep = Aeroport.leDepartement
;

/*
nombre de tuples : 5

# leDepartement, nom
'29', 'BREST-BRETAGNE'
'29', 'MORLAIX-PLOUJEAN'
'29', 'QUIMPER-PLUGUFFAN'
'35', 'DINARD-PLEURTUIT-ST-MALO'
'35', 'RENNES-ST-JACQUES'
*/

/* Question 2 : Afficher le numéro de département de tous les départements ayant au moins 2 aéroports. */

SELECT DISTINCT A1.leDepartement
FROM Aeroport A1, Aeroport A2
WHERE A1.leDepartement = A2.leDepartement
AND A1.nom != A2.nom
;

/*
nombre de tuples : 3

# leDepartement
'22'
'29'
'35'
*/

/* Question 3 : Pour chaque année, afficher l'ensemble des données annuelles disponibles (éventuellement aucune) */
-- IMPORTANT : Dans le cas de cette question, il est nécessaire d'afficher 28 tuples dans la réponse afin de montrer l'absence de données annuelles disponibles de 1991 jusqu'à 2018

SELECT DISTINCT annee, DonneesAnnuelles.*
FROM Annee
	LEFT JOIN DonneesAnnuelles ON DonneesAnnuelles.lAnnee = Annee.annee
;

/*
4832 tuples sélectionnés 

# annee, lAnnee, laCommune, nbMaison, nbAppart, prixMoyen, prixM2Moyen, SurfaceMoy, depensesCulturellesTotales, budgetTotal, population
'1991', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'1992', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'1993', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'1994', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'1995', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'1996', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'1997', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'1998', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'1999', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'2000', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'2001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'2002', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'2003', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'2004', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'2005', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'2006', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'2007', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'2008', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'2009', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'2010', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'2011', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'2012', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'2013', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'2014', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'2015', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'2016', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'2017', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL
'2018', '2018', '22001', '3', '0', '80966.7', '650.333', '123.333', '-1', '-1', '-1'
*/

/* Question 4 : Pour chaque commune du Morbihan (56), afficher l'identifiant et le nom de la ou les gares auxquelles elle est rattachée (éventuellement aucune) */

SELECT DISTINCT idCommune, nomCommune, codeGare
FROM Commune
	LEFT JOIN Gare ON Gare.laCommune = Commune.idCommune
WHERE leDepartement = 56
;

/*
253 tuples sélectionnés 

# idCommune, nomCommune, codeGare
'56001', 'ALLAIRE', NULL
'56002', 'AMBON', NULL
'56003', 'ARRADON', NULL
'56004', 'ARZAL', NULL
'56005', 'ARZON', NULL
'56006', 'AUGAN', NULL
'56007', 'AURAY', '87476200'
'56008', 'BADEN', NULL
'56009', 'BANGOR', NULL
'56010', 'BAUD', NULL
*/

/* Question 5 : Combiens de communes se trouve dans un département avec un investissement culturel en 2019 strictement supérieur à 10000000€ ? */

SELECT COUNT(idCommune)
FROM Commune
WHERE leDepartement IN (SELECT DISTINCT idDep
						FROM Departement
						WHERE investissementCulturel2019 > 10000000
						)
;

/*
1 tuple sélectionné

# COUNT(idCommune)
'610'
*/

/* Question 6 : Combiens de gares ne sont pas rattachées à des communes qui se trouvent dans un département avec un investissement culturel en 2019 strictement supérieur à 10000000€ ? */

SELECT COUNT(codeGare)
FROM Gare
WHERE laCommune NOT IN (SELECT idCommune
						FROM Commune
						WHERE leDepartement IN (SELECT DISTINCT idDep
												FROM Departement
												WHERE investissementCulturel2019 > 10000000
												)
						)
;

/*
1 tuple sélectionné

# COUNT(codeGare)
'64'
*/

/* Question 7 : Quels sont les prix au mètre carré moyens relatifs à au moins 2 communes en 2020 ? On n'oubliera pas de les trier dans l'ordre croissant. */

SELECT DISTINCT D1.prixM2Moyen
FROM DonneesAnnuelles D1
WHERE EXISTS (SELECT *
			  FROM DonneesAnnuelles D2
              WHERE D1.prixM2Moyen = D2.prixM2Moyen
              AND D1.laCommune != D2.laCommune
			  )
AND D1.lAnnee = 2020
ORDER BY D1.prixM2Moyen
; 

/*
51 tuples sélectionnés 

# prixM2Moyen
'784.5'
'858'
'898'
'1019.62'
'1023'
'1055'
'1061'
'1061.3'
'1066'
'1077.75'
*/

/* Question 8 : Quels sont les communes de Morbihan (56) ayant strictement moins de 100 logements (logements = maisons + appartements) en 2020 ? */

SELECT DISTINCT idCommune, nomCommune
FROM Commune
WHERE NOT EXISTS(SELECT *
				 FROM DonneesAnnuelles
                 WHERE DonneesAnnuelles.laCommune = Commune.idCommune
                 AND (nbMaison + nbAppart) >= 100
                 AND lAnnee = 2020
                 )
AND leDepartement = 56
;

/*
226 tuples sélectionnés

# idCommune, nomCommune
'56001', 'ALLAIRE'
'56002', 'AMBON'
'56003', 'ARRADON'
'56004', 'ARZAL'
'56006', 'AUGAN'
'56008', 'BADEN'
'56009', 'BANGOR'
'56010', 'BAUD'
'56011', 'BEGANNE'
'56012', 'BEIGNON'
*/

/* Question 9 : Afficher l'identifiant des communes dont la quantité de logements (maisons + appartements) en 2020 dépasse ou est égale à la moyenne par commune en 2020  */

SELECT DISTINCT laCommune, (nbMaison + nbAppart) AS logements
FROM DonneesAnnuelles
WHERE lAnnee = 2020
AND (nbMaison + nbAppart) >= (SELECT AVG(nbMaison + nbAppart)
							  FROM DonneesAnnuelles
                              WHERE lAnnee = 2020
                              )
;

/*
269 tuples sélectionnés 

# laCommune, logements
'22004', '84'
'22025', '52'
'22046', '115'
'22050', '348'
'22054', '123'
'22055', '160'
'22070', '122'
'22081', '51'
'22093', '237'
'22106', '95'
*/

/* Question 10: Afficher l'identifiant des communes dont le prix au mètre carré est strictement inférieur à la moyenne par commune en 2020 */

SELECT DISTINCT laCommune, prixM2Moyen
FROM DonneesAnnuelles
WHERE lAnnee = 2020
AND prixM2Moyen < (SELECT AVG(prixM2Moyen)
							  FROM DonneesAnnuelles
                              WHERE lAnnee = 2020
                              )
;

/*
735 tuples sélectionnés

# laCommune, prixM2Moyen
'22001', '1378.14'
'22002', '1623'
'22003', '1449'
'22004', '1301.13'
'22005', '1081.2'
'22006', '1305.25'
'22009', '1185'
'22011', '1219.67'
'22012', '1420.46'
'22013', '1137.48'
*/

/* Question 11 : Afficher la quantité de gares par commune ayant une gare */

SELECT DISTINCT idCommune, nomCommune, COUNT(codeGare) AS totalGares
FROM Commune
	JOIN Gare ON Gare.laCommune = Commune.idCommune
GROUP BY idCommune
;

/*
120 tuples sélectionnés

# idCommune, nomCommune, totalGares
'22013', 'BOURBRIAC', '1'
'22020', 'BROONS', '1'
'22025', 'CALLAC', '2'
'22032', 'CAULNES', '1'
'22050', 'DINAN', '1'
'22052', 'DUAULT', '1'
'22070', 'GUINGAMP', '2'
'22093', 'LAMBALLE-ARMOR', '1'
'22096', 'LANDEBIA', '1'
'22105', 'LANGUENAN', '1'
*/

/* Question 12 : Afficher la quantité d'aéroports par département ayant un aéroport */

SELECT DISTINCT idDep, nomDep, COUNT(nom) AS totalAeroports
FROM Departement
	JOIN Aeroport ON Aeroport.leDepartement = Departement.idDep
GROUP BY idDep
;

/*
4 tuples sélectionnés 

# idDep, nomDep, totalAeroports
'22', 'COTES-D\'ARMOR', '2'
'29', 'FINISTERE', '3'
'35', 'ILLE-ET-VILAINE', '2'
'56', 'MORBIHAN', '1'
*/

/* Question 13 : Afficher la commune et la quantité de gares de la commune ayant le plus de gares */

SELECT DISTINCT idCommune, nomCommune, COUNT(codeGare)  AS plusGrandeQuantiteGares
FROM Commune
	JOIN Gare ON Gare.laCommune = Commune.idCommune
GROUP BY idCommune
HAVING plusGrandeQuantiteGares = (SELECT DISTINCT MAX(totalGare)
								  FROM(SELECT COUNT(codeGare) AS totalGare
									   FROM Commune
										 JOIN Gare ON Gare.laCommune = Commune.idCommune
									   GROUP BY idCommune
									 ) AS subtable
								  )
;

/*
1 tuple sélectionné 

# idCommune, nomCommune, plusGrandeQuantiteGares
'56234', 'SAINT-PIERRE-QUIBERON', '4'
*/

/* Question 14 : Afficher le département et la quantité d'aéroports du département ayant le moins d'aéroports */

SELECT DISTINCT idDep, nomDep, COUNT(nom) AS plusPetiteQuantiteAeroports
FROM Departement
	JOIN Aeroport ON Aeroport.leDepartement = Departement.idDep
GROUP BY idDep
HAVING plusPetiteQuantiteAeroports = (SELECT DISTINCT MIN(totalAeroports)
									  FROM (SELECT COUNT(nom) AS totalAeroports
											FROM Departement
												JOIN Aeroport ON Aeroport.leDepartement = Departement.idDep
											GROUP BY idDep
										   ) AS subtable
									 )
;

/*
1 tuple sélectionné

# idDep, nomDep, plusPetiteQuantiteAeroports
'56', 'MORBIHAN', '1'
*/

/* Question 15 : Quels sont les communes dont tous les voisins ont un prix au mètre carré moyen strictement supérieur à celui de la commune concernée en 2020 */

SELECT DISTINCT D1.laCommune
FROM DonneesAnnuelles D1
WHERE NOT EXISTS(SELECT DISTINCT communeVoisine
				 FROM Voisinage
                 WHERE commune = D1.laCommune
                 EXCEPT 
                 SELECT DISTINCT D2.laCommune
                 FROM DonneesAnnuelles D2
                 WHERE D2.prixM2Moyen > D1.prixM2Moyen
                 AND D2.lAnnee = 2020
				 )
AND D1.lAnnee = 2020
;

/*
184 tuples sélectionnés 

# laCommune
'22002'
'22003'
'22012'
'22016'
'22027'
'22036'
'22039'
'22042'
'22043'
'22046'
*/

/* Question 16 : Quels sont les communes dont tous les voisins et uniquement ceux-là ont un prix au mètre carré moyen strictement supérieur à celui de la commune concernée en 2020 */

SELECT DISTINCT D1.laCommune
FROM DonneesAnnuelles D1
WHERE NOT EXISTS(SELECT DISTINCT communeVoisine
				 FROM Voisinage
                 WHERE commune = D1.laCommune
                 EXCEPT 
                 SELECT DISTINCT D2.laCommune
                 FROM DonneesAnnuelles D2
                 WHERE D2.prixM2Moyen > D1.prixM2Moyen
                 AND D2.lAnnee = 2020
				 )
AND NOT EXISTS(SELECT DISTINCT D2.laCommune
			   FROM DonneesAnnuelles D2
               WHERE D2.prixM2Moyen > D1.prixM2Moyen
               AND D2.lAnnee = 2020
               EXCEPT
               SELECT DISTINCT communeVoisine
               FROM Voisinage
               WHERE commune = D1.laCommune
               )
AND D1.lAnnee = 2020
;

/*
1 tuple sélectionné 

# laCommune
'56085'
*/

/* 
Question 17 
On propose comme contrainte que toutes les communes qui possède au moins une gare ont nécessairement une commune voisine ou plus.
En effet, une gare relie deux communes a minima et ces communes deviennent alors voisines de par cette liaison.
La vue suivante permet de détecter les communes qui ne respectent pas cette contrainte.
*/

CREATE OR REPLACE VIEW vue_GaresSansVoisins
(
	sansVoisins
)
AS
SELECT DISTINCT laCommune AS sansVoisins
FROM Gare
-- Ici, on effectue une union car il est possible que la commune concernée soit considérée comme la voisine d'une autre commune
-- sans que la commune concernée considère cette autre commune comme sa voisine ce qui est un autre problème en soi-même
WHERE laCommune NOT IN(SELECT DISTINCT commune
					   FROM Voisinage
                       UNION
                       SELECT DISTINCT communeVoisine
                       FROM Voisinage
                       )
;

SELECT * 
FROM vue_GaresSansVoisins
;
/*
aucun tuple sélectionné
*/

/* 
Question 18  
On propose comme contrainte que les données annuelles d'une commune ne peuvent pas être négatives.
Toutes les données de la table DonneesAnnuelles sont concernées par cette vue sauf les données contraignantes 
de la BDD telles que les dépenses culturelles totales, le budget total et la population puisque celles-ci sont négatives. 
La vue suivante permet de détecter les communes qui ne respectent pas cette contrainte ainsi que de répérer laquelle ou lesquelles données sont négatives.
En résumé, cette vue permet de détecter des entrées utilisateur faussées ou impossibles.
*/

CREATE OR REPLACE VIEW vue_DonneesNegatives
AS
SELECT DISTINCT lAnnee, laCommune, nbMaison, nbAppart, prixMoyen, prixM2Moyen, surfaceMoy
FROM DonneesAnnuelles
WHERE lAnnee < 0
OR laCommune < 0
OR nbMaison < 0
OR nbAppart < 0
OR prixMoyen < 0
OR prixM2Moyen < 0
OR surfaceMoy < 0
;

SELECT *
FROM vue_DonneesNegatives
;
/*
aucun tuple sélectionné
*/ 

/*
Question 19
On propose comme attribut dérivé le pourcentage de représentation des gares de fret et des gares de voyageurs parmi les différentes gares existants en Bretagne.
En effet, cette information serait utile à calculer pour une commune car elle permetrait de déterminer le type de gare le plus attractif à un moment T en fonction du type dominant.
La vue suivante permet de calculer ce pourcentage pour les deux types de gares. 
*/
-- Puisque la présence ou l'absence d'un type de gare est noté à partir de 0 ou 1, il est possible d'obtenir la quantité totale pour chaque type
-- en effectuant une somme des valeurs. Par exemple, s'il existe 50 gares de voyageurs alors SUM(estVoyageur) = 50.
-- Pour chaque type de gare, le pourcentage se calcule en divisant cette somme par la quantité totale de gares existantes tout type confondu multiplié par 100.

CREATE OR REPLACE VIEW vue_PourcentageRepresentationTypeGare
(
	pourcentageGareFret,
    pourcentageGareVoyageur
)
AS 
SELECT DISTINCT (SUM(estFret)/COUNT(codeGare))*100, SUM(estVoyageur)/COUNT(codeGare)*100
FROM Gare;
;

SELECT *
FROM vue_PourcentageRepresentationTypeGare
;
/*
1 tuple sélectionné

# pourcentageGareFret, pourcentageGareVoyageur
'35.7664', '90.5109'
*/
 
 /* 
 Question 20 
 On propose comme attribut dérivé le pourcentage de représentation par année du nombre de logements existant sur une commune parmi tous les logements existants sur une année.
 En effet, cet information permet à une commune de visualiser son avancée ou son recul par rapport au logement de ses habitants au fil des années.
 Comme pour les questions précédentes, la quantité de logements se calcule en effectuant nbMaison + nbAppart .
 Ici, le calcul du pourcentage s'effectue en divisant la quantité de logements d'une commune sur une année par la quantité de logements existants sur la même année, multiplié par 100.
 */
 
CREATE OR REPLACE VIEW vue_PourcentageLogementsParAnnee
(
	lAnnee,
	laCommune,
	pourcentageLogement
)
AS
SELECT D1.lAnnee, D1.laCommune, ((D1.nbMaison + D1.nbAppart) / tousLesLogements) * 100 
FROM DonneesAnnuelles D1
	JOIN (SELECT D2.lAnnee, SUM(D2.nbMaison + D2.nbAppart) AS tousLesLogements
		  FROM DonneesAnnuelles D2
          GROUP BY D2.lAnnee
          ) AS calcul ON D1.lAnnee = calcul.lAnnee
;
 
SELECT *
FROM vue_PourcentageLogementsParAnnee
;
 /*
 4803 tuples sélectionnés
 
 # lAnnee, laCommune, pourcentageLogement
'2018', '22001', '0.0060'
'2018', '22002', '0.0302'
'2018', '22003', '0.0202'
'2018', '22004', '0.1431'
'2018', '22005', '0.0403'
'2018', '22006', '0.0081'
'2018', '22008', '0.0222'
'2018', '22009', '0.0060'
'2018', '22011', '0.0141'
'2018', '22012', '0.0262'
 */