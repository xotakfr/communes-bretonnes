package modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Une instance de cette classe permet de représenter un département
 * Cette classe implémente l'interface Comparable pour permettre la comparaison des départements
 * selon différents critères définis par un filtre
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 * @see Comparable
 */
public class Departement implements Comparable<Departement> {
    /** Filtre de base */
    private static String currentFilter = "idDep";
    /** Liste des départements autorisés */
    private static final List<String> DEPARTEMENTS_AUTORISES = Arrays.asList("MORBIHAN", "ILLE-ET-VILAINE", "COTES-D'ARMOR", "FINISTERE");
    /** Liste des filtres autorisés */
    private static final String[] FILTERLIST = new String[]{"idDep", "nomDep", "invesCulturel2019"};
    /** Identifiant du département */
    private int idDep;
    /** Nom du département */
    private String nomDep;
    /** Investissement culturel en 2019 */
    private float invesCulturel2019;
    /** Liste des communes du département */
    private ArrayList<Commune> communes;
    /** Liste des aéroports du département */
    private ArrayList<Aeroport> aeroports;

    /**
     * Permet de construire une instance de la classe Departement
     * @param idDep L'identifiant du département
     * @param nomDep Le nom du département, il est limité au contenu de DEPARTEMENTS_AUTORISES
     * @param invesCulturel2019 L'investissement culturel en 2019
     * @param communes Liste des communes du département
     * @param aeroports Liste des aéroports du département
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public Departement(int idDep, String nomDep, float invesCulturel2019, ArrayList<Commune> communes, ArrayList<Aeroport> aeroports) throws IllegalArgumentException {
        if (idDep <= 0) {
            throw new IllegalArgumentException("Departement.java : paramètre idDep invalide");
        }
        if (!departementEstCorrect(nomDep)) {
            throw new IllegalArgumentException("Departement.java : paramètre nomDep invalide");
        }
        if (invesCulturel2019 < 0) {
            throw new IllegalArgumentException("Departement.java : paramètre invesCulturel2019 invalide"); 
        }
        if (communes == null) {
            throw new IllegalArgumentException("Departement.java : paramètre communes invalide");
        }
        if (aeroports == null) {
            throw new IllegalArgumentException("Departement.java : paramètre aeroports invalide");
        }
        this.nomDep = nomDep;
        this.idDep = idDep;
        this.invesCulturel2019 = invesCulturel2019;
        this.communes = communes;
        this.aeroports = aeroports;
    }

    /**
     * Renvoie l'identifiant du département
     * 
     * @return L'identifiant du département
     */
    public int getIdDep() {
        return this.idDep;
    }

    /**
     * Permet de définir le nouvel identifiant du département
     * @param idDep Le nouvel identifiant du département
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setIdDep(int idDep) throws IllegalArgumentException {
        if (idDep <= 0) {
            throw new IllegalArgumentException("Departement.java : paramètre idDep invalide");
        }
        this.idDep = idDep;
    }

    /**
     * Renvoie le nom du département
     * @return Le nom du département
     */
    public String getNomDep() {
        return this.nomDep;
    }

    /**
     * Permet de définir le nouveau nom du département
     * @param nomDep Le nouveau nom du département
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setNomDep(String nomDep) throws IllegalArgumentException {
        if (!departementEstCorrect(nomDep)) {
            throw new IllegalArgumentException("Departement.java : paramètre nomDep invalide");
        }
        this.nomDep = nomDep.toUpperCase();
    }

    /**
     * Renvoie l'investissement culturel en 2019
     * @return L'investissement culturel en 2019
     */
    public float getInvesCulturel2019() {
        return this.invesCulturel2019;
    }

    /**
     * Permet de définir le nouvel investissement culturel en 2019
     * @param invesCulturel2019 Le nouvel investissement culturel en 2019
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setInvesCulturel2019(float invesCulturel2019) throws IllegalArgumentException {
        if (invesCulturel2019 < 0) {
            throw new IllegalArgumentException("Departement.java : paramètre invesCulture2019 invalide");
        }
        this.invesCulturel2019 = invesCulturel2019;
    }

    /**
     * Renvoie la liste des communes du département
     * @return La liste des communes du département
     */
    public ArrayList<Commune> getCommunes() {
        return this.communes;
    }

    /**
     * Permet de définir la nouvelle liste des communes du département
     * @param communes La nouvelle liste des communes du département
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setCommunes(ArrayList<Commune> communes) throws IllegalArgumentException {
        if (communes == null) {
            throw new IllegalArgumentException("Departement.java : paramètre communes invalide");
        }
        this.communes = communes;
    }

    /**
     * Renvoie la liste des aéroports du département
     * @return La liste des aéroports du département
     */
    public ArrayList<Aeroport> getAeroports() {
        return this.aeroports;
    }

    /**
     * Permet de définir la nouvelle liste des aéroports du département
     * @param aeroports La nouvelle liste des aéroports du département
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setAeroports(ArrayList<Aeroport> aeroports) throws IllegalArgumentException {
        if (aeroports == null) {
            throw new IllegalArgumentException("Departement.java : paramètre aeroports invalide");
        }
        this.aeroports = aeroports;
    } 

    /**
     * Renvoie la liste des filtres autorisés.
     * @return La liste des filtres autorisés
     */
    public static String[] getAllFilter() {
        return FILTERLIST;
    }

    /**
     * Permet de définir le filtre à utiliser
     * @param filter Le filtre à utiliser
     */
    public static void setFilter(String filter) {
        for (String s : FILTERLIST) {
            if (s.equals(filter)) {
                currentFilter = filter;
            }
        }
    }

    /**
     * Permet de vérifier si le nom du département est correct
     * @param nomDep Le nom du département à vérifier
     * @return true si le département est autorisé, sinon false
     */
    public static boolean departementEstCorrect(String nomDep) {
        return DEPARTEMENTS_AUTORISES.contains(nomDep.toUpperCase());
    }

    /**
     * Implémentation de l'interface Comparable
     * Comparaison basée sur le filtre actuel
     * @param o Autre instance de la classe Departement à comparer
     * @return Un entier représentant le résultat de la comparaison
     */
    public int compareTo(Departement o) {
        int ret = 0;
        if (currentFilter.equals("idDep")) {
           ret = Integer.compare(this.idDep, o.idDep);
        }
        if (currentFilter.equals("nomDep")) {
            ret = this.nomDep.compareTo(o.nomDep);
        }
        if (currentFilter.equals("invesCulturel2019")) {
            ret = Float.compare(this.invesCulturel2019, o.invesCulturel2019);
        }
        return ret;
    }

    /**
     * Renvoie une représentation textuelle d'une instance de la classe Departement
     * @return Une représentation textuelle d'une instance de la classe Departement
     */
    public String toString() {
        return "Departement{" +
                "idDep = " + this.idDep +
                ", nomDep = " + this.nomDep +
                ", invesCulturel2019 = " + this.invesCulturel2019 +
                "} ";
    }
    // TODO : modifier le toString pour rajouter les listes 
}
