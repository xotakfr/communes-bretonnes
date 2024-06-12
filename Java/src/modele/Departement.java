package modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Une instance de cette classe permet de représenter un département
 * Cette classe implémente l'interface Comparable pour permettre la comparaison des départements
 * selon différents critères définis par un filtre.
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 * @see Comparable
 */
public class Departement implements Comparable<Departement> {
    /** Filtre de base */
    private static String currentFilter = "idDep";
    /** Liste des départements autorisés */
    private static final List<String> DEPARTEMENTS_AUTORISES = Arrays.asList("MORBIHAN", "ILLE-ET-VILAINE", "COTES-D'ARMOR", "FINISTERE");
    /** Liste des filtres autorisés */
    private static final String[] FILTER_LIST = new String[]{"idDep", "nomDep", "invesCulturel2019"};
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
     * Permet de construire une instance de la classe Departement.
     * Cette classe implémente l'interface Comparable pour permettre la comparaison de départements
     * selon différents critères définis par un filtre.
     * La liste des communes ainsi que la liste des aéroports associés au département sont initialisées à vide.
     * @param idDep L'identifiant du département
     * @param nomDep Le nom du département, il est limité au contenu de DEPARTEMENTS_AUTORISES
     * @param invesCulturel2019 L'investissement culturel en 2019
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
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
        this.nomDep = nomDep;
        this.idDep = idDep;
        this.invesCulturel2019 = invesCulturel2019;
        this.communes = new ArrayList<Commune>();
        this.aeroports = new ArrayList<Aeroport>();
    }

    /**
     * Renvoie l'identifiant du département.
     * @return L'identifiant du département
     */
    public int getIdDep() {
        return this.idDep;
    }

    /**
     * Permet de définir le nouvel identifiant du département.
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
     * Permet d'ajouter une commune à la liste des commune du département concerné par l'appel de la méthode
     * @param commune La commune qu'on veut ajouter à la liste des communes du département concerné par l'appel de la méthode
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void ajouteCommune(Commune commune) throws IllegalArgumentException {
        if (commune == null) {
            throw new IllegalArgumentException("Departement.java : paramètre commune invalide");
        }
        this.communes.add(commune);
    }

    /**
     * Permet de retirer une commune de la liste des communes du département concerné par l'appel de la méthode
     * @param commune La commune qu'on veut retirer de la liste des communes du département concerné par l'appel de la méthode
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void retireCommune(Commune commune) throws IllegalArgumentException {
        if (commune == null) {
            throw new IllegalArgumentException("Departement.java : paramètre commune invalide");
        }
        boolean ret = this.communes.remove(commune);
        if (!ret) {
            System.out.println("La commune passée en paramètre n'est pas une des communes du département suivant : " + this.nomDep);
        }
    }

    /**
     * Renvoie la liste des aéroports du département
     * @return La liste des aéroports du département
     */
    public ArrayList<Aeroport> getAeroports() {
        return this.aeroports;
    }

    /**
     * Permet d'ajouter un aéroport à la liste des aéroports du département concerné par l'appel de la méthode
     * @param aeroport L'aéroport qu'on veut ajouter à la liste des aéroports du département concerné par l'appel de la méthode
     * @throws IllegalArgumentException - qaund un paramètre invalide est utilisé
     */
    public void ajouteAeroport(Aeroport aeroport) throws IllegalArgumentException {
        if (aeroport == null) {
            throw new IllegalArgumentException("Departement.java : paramètre aeroport invalide");
        }
        this.aeroports.add(aeroport);
    }

    /**
     * Permet de retirer un aéroport de la liste des aéroports du département concerné par l'appel de la méthode
     * @param aeroport L'aéroport qu'on veut retirer de la liste des aéroports du département concerné par l'appel de la méthode
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void retireAeroport(Aeroport aeroport) throws IllegalArgumentException {
        if (aeroport == null) {
            throw new IllegalArgumentException("Departement.java : paramètre aeroport invalide");
        }
        boolean ret = this.aeroports.remove(aeroport);
        if (!ret) {
            System.out.println("L'aéroport passé en paramètre n'est pas l'un des aéroports du département suivant : " + this.nomDep);
        }
    }

    /**
     * Renvoie la liste des filtres autorisés.
     * @return La liste des filtres autorisés
     */
    public static String[] getAllFilter() {
        return FILTER_LIST;
    }

    /**
     * Permet de définir le filtre à utiliser
     * @param filter Le filtre à utiliser
     */
    public static void setFilter(String filter) {
        for (String s : FILTER_LIST) {
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
     * @throws NullPointerException - si o est à null
     * @return Un entier représentant le résultat de la comparaison
     */
    public int compareTo(Departement o) throws NullPointerException {
        int ret = 0;
        if (o == null) {
            throw new NullPointerException("Departement.java : paramètre o est null");
        }
        else if (currentFilter.equals("idDep")) {
           ret = Integer.compare(this.idDep, o.idDep);
        }
        else if (currentFilter.equals("nomDep")) {
            ret = this.nomDep.compareTo(o.nomDep);
        }
        else if (currentFilter.equals("invesCulturel2019")) {
            ret = Float.compare(this.invesCulturel2019, o.invesCulturel2019);
        }
        return ret;
    }

    /**
     * Renvoie une représentation textuelle des communes du département concerné.
     * Ici, la variable i est utilisée pour modifier l'ajout à la chaîne de caractères lorsqu'on
     * atteint la fin de la liste
     * @return Une chaîne de caractères représentant les communes du département concerné
     */
    public String communesAsString() {
        String s = "";
        int i = 0;
        if (this.communes.isEmpty()) {
            s += "Aucune commune";
        } else {
            for (Commune commune : this.communes) {
                if (i != this.communes.size() - 1) {
                    s += commune.getIdCommune() + " (" + commune.getNomCommune() + ") -";
                } else {
                    s += commune.getIdCommune() + " (" + commune.getNomCommune() + ")";
                }
                i++;
            }
        }
        return s;
    }

    /**
     * Renvoie une représentation textuelle des aéropots du département concerné.
     * Ici, la variable i est utilisée pour modifier l'ajout à la chaîne de caractères lorsqu'on
     * atteint la fin de la liste
     * @return Une chaîne de caractères représentant les aéropots du département concerné
     */
    public String aeroportsAsString() {
        String s = "";
        int i = 0;
        if (this.aeroports.isEmpty()) {
            s += "Aucune gare";
        } else {
            for (Aeroport aeroport : this.aeroports) {
                if (i != this.aeroports.size() - 1) {
                    s += aeroport.getNom() + " (" + aeroport.getAdresse() + ") -";
                } else {
                    s += aeroport.getNom() + " (" + aeroport.getAdresse() + ")";
                }
                i++;
            }
        }
        return s;
    }

    /**
     * Renvoie une représentation textuelle d'une instance de la classe Departement
     * @return Une représentation textuelle d'une instance de la classe Departement
     */
    public String toString() {
        return "Departement{" +
                "idDep = " + this.idDep +
                ", nomCommune = " + this.nomDep +
                ", communes = " + this.communesAsString() +
                ", aeroports = " + this.aeroportsAsString() +
                ", invesCulturel2019 = " + this.invesCulturel2019 +
                "} ";
    }

}
