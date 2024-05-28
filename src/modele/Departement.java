package modele;

import java.util.Arrays;
import java.util.List;

/**
 * Une instance de cette classe permet de représenter un département
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class Departement implements Comparable<Departement>, SwitcherFilter{
    /** Filtre de base - voir implémentation de comparableTo.java et SwitecherFilter.java */
    private static String currentFilter = "idDep";
    /** Identifiant du département */
    private int idDep;
    /** Nom du département. Il est limité au contenu de DEPARTEMENTS_AUTORISES */
    private String nomDep;
    /** Investissement culturel en 2019 */
    private float invesCulturel2019;
    /** Liste des départements autorisés */
    private static final List<String> DEPARTEMENTS_AUTORISES = Arrays.asList("MORBIHAN", "ILLE-ET-VILAINE", "COTES-D'ARMOR", "FINISTERE");
    /** Liste des filtres autorisés */
    private static final String[] FILTERLIST = new String[]{"idDep", "nomDep", "invesCulturel2019"};

    /**
     * Constructeur de la classe Departement
     * @param idDep L'identifiant du département
     * @param nomDep Le nom du département. Il est limité au contenu de DEPARTEMENTS_AUROTISES
     * @param invesCulturel2019 L'investissement culturel en 2019
     * @throws // TODO 
     */
    public Departement(int idDep, String nomDep, float invesCulturel2019) throws IllegalArgumentException {
        if (idDep <= 0) {
            throw new IllegalArgumentException("Departement.java : paramètre idDep invalide");
        }
        if (!departementEstCorrect(nomDep)) {
            throw new IllegalArgumentException("Departement.java : paramètre nomDep invalide");
        }
        if (invesCulturel2019 < 0) {
            throw new IllegalArgumentException("Departement.java : paramètre invesCulturel2019 invalide"); 
        }
        setNomDep(nomDep);
        this.idDep = idDep;
        this.invesCulturel2019 = invesCulturel2019;
    }

    /**
     * Renvoie l'identifiant du département
     * @return L'identifiant du département
     */
    public int getIdDep() {
        return this.idDep;
    }

    /**
     * Permet de définir le nouvel identifiant du département
     * @param idDep Le nouvel identifiant du département
     * @throws // TODO 
     */
    public void setIdDep(int idDep) throws Exception {
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
     * @throws // TODO 
     */
    public void setNomDep(String nomDep) {
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
     * @throws // TODO 
     */
    public void setInvesCulturel2019(float invesCulturel2019) {
        if (invesCulturel2019 < 0) {
            throw new IllegalArgumentException("Departement.java : paramètre invesCulture2019 invalide");
        }
        this.invesCulturel2019 = invesCulturel2019;
    }

    /**
     * Renvoie la liste des filtres autorisés
     * @return La liste des filtres autorisés
     */
    public static String[] getAllFilter() {
        return FILTERLIST;
    }
    /**
     * Permet de définir le filtre a utiliser
     * @param filter Le filtre a utiliser
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
     * Comparaison basée sur le filtre - voir SwitcherFilter.java
     * @param o Autre instance de la classe Departement à comparer
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
                ", nomDep = '" + this.nomDep +
                ", invesCulturel2019 = " + this.invesCulturel2019 +
                "}";
    }
}
