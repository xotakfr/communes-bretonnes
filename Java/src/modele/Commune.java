package modele;

import java.util.ArrayList;

/**
 * Une instance de cette classe permet de représenter une commune.
 * Cette classe implémente l'interface Comparable pour permettre la comparaison des communes
 * selon différents critères définis par un filtre
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 * @see Comparable
 */
public class Commune implements Comparable<Commune> {
    /** Filtre de base */
    private static String currentFilter = "idCommune";
    /** Liste des filtres autorisés */
    private static String[] FILTERLIST = new String[]{"idCommune", "nomCommune", "leDepartement"};
    /** Identifiant de la commune */
    private int idCommune;
    /** Nom de la commune */
    private String nomCommune;
    /** Liste des gares de la commune */
    private ArrayList<Gare> gares;
    /** Liste des communes voisines */
    private ArrayList<Commune> voisins; // TODO : voisins vide au début et méthode d'ajout de voisins, pareil pour toutes les listes 
    /** Instance de la classe Departement associée aux données */
    private Departement leDepartement;

    /**
     * Permet d'initialiser une instance de la classe Commune
     * @param idCommune L'identifiant de la commune
     * @param nomCommune Le nom de la commune
     * @param gares La liste des gares de la commune
     * @param voisins La liste des communes voisines
     * @param leDepartement L'instance de la classe Departement associée aux données
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public Commune(int idCommune, String nomCommune, ArrayList<Gare> gares, ArrayList<Commune> voisins, Departement leDepartement) throws IllegalArgumentException {
        if (idCommune <= 0) {
            throw new IllegalArgumentException("Commune.java : paramètre idCommune invalide");
        }
        if (nomCommune.equals("") || nomCommune == null) {
            throw new IllegalArgumentException("Commune.java : paramètre nomCommune invalide");
        }
        if (gares == null) {
            throw new IllegalArgumentException("Commune.java : paramètre gares invalide");
        }
        if (voisins == null) {
            throw new IllegalArgumentException("Commune.java : paramètre voisins invalide");
        }
        if (leDepartement == null) {
            throw new IllegalArgumentException("Commune.java : paramètre leDepartement invalide");
        }
        this.idCommune = idCommune;
        this.nomCommune = nomCommune;
        this.gares = gares;
        this.voisins = voisins;
        this.leDepartement = leDepartement;
    }

    /**
     * Renvoie l'identifiant de la commune
     * @return L'identifiant de la commune
     */
    public int getIdCommune() {
        return this.idCommune;
    }

    /**
     * Permet de définir le nouvel identifiant de la commune
     * @param idCommune Le nouvel identifiant de la commune
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setIdCommune(int idCommune) throws IllegalArgumentException {
        if (idCommune <= 0) {
            throw new IllegalArgumentException("Commune.java : paramètre idCommune invalide");
        }
        this.idCommune = idCommune;
    }

    /**
     * Renvoie le nom de la commune
     * @return Le nom de la commune
     */
    public String getNomCommune() {
        return this.nomCommune;
    }

    /**
     * Permet de définir le nouveau nom de la commune
     * @param nomCommune Le nouveau nom de la commune
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setNomCommune(String nomCommune) throws IllegalArgumentException {
        if (nomCommune.contains("") || nomCommune == null) {
            throw new IllegalArgumentException("Commune.java : paramètre nomCommune invalide");
        }
        this.nomCommune = nomCommune;
    }

    /**
     * Renvoie la liste des gares de la commune
     * @return La liste des gares de la commune
     */
    public ArrayList<Gare> getGares() {
        return this.gares;
    }

    /**
     * Permet de définir la nouvelle liste des gares de la commune
     * @param gares La nouvelle liste des gares de la commune
     * @throws IllegalArgumentException  quand un paramètre invalide est utilisé
     */
    public void setGares(ArrayList<Gare> gares) throws IllegalArgumentException {
        if (gares == null) {
            throw new IllegalArgumentException("Commune.java : paramètre gares invalide");
        }
        this.gares = gares;
    }

    /**
     * Renvoie la liste des communes voisines
     * @return La liste des communes voisines
     */
    public ArrayList<Commune> getVoisins() {
        return this.voisins;
    }

    /**
     * Permet de définir la nouvelle liste des communes voisines
     * @param voisins La nouvelle liste des communes voisines
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setVoisins(ArrayList<Commune> voisins) throws IllegalArgumentException {
        if (voisins == null) {
            throw new IllegalArgumentException("Commune.java : paramètre voisins invalide");
        }
        this.voisins = voisins;
    }

    /**
     * Renvoie l'instance de la classe Departement associée aux données
     * @return L'instance de la classe Departement associée aux données
     */
    public Departement getLeDepartement() {
        return this.leDepartement;
    }

    /**
     * Permet de définir la nouvelle instance de la classe Departement associée aux données
     * @param leDepartement La nouvelle instance de la classe Departement associée aux données
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setLeDepartement(Departement leDepartement) throws IllegalArgumentException {
        if (leDepartement == null) {
            throw new IllegalArgumentException("Commune.java : paramètre leDepartement invalide");
        }
        this.leDepartement = leDepartement;
    }

    /**
     * Renvoie la liste de tous les filtres autorisés
     * @return La liste de tous les filtres autorisés
     */
    public static String[] getAllFilter() {
        return FILTERLIST;
    }

    /**
     * Permet de définir le filtre à utiliser
     * @param filter Le nouveau filtre à utiliser
     */
    public static void setFilter(String filter) {
        for (String s : FILTERLIST) {
            if (s.equals(filter)) {
                currentFilter = filter;
            }
        }
    }

    /**
     * Implémentation de l'interface Comparable.
     * Comparaison basée sur le filtre actuellement choisi
     * @param o Une autre instance de la classe Commune à comparer
     */
    public int compareTo(Commune o) {
        int ret = 0;
        if (currentFilter.equals("idCommune")) {
           ret = Integer.compare(this.idCommune, o.idCommune);
        }
        if (currentFilter.equals("nomCommune")) {
            ret = this.nomCommune.compareTo(o.nomCommune);
        }
        if (currentFilter.equals("leDepartement")) {
            ret = this.leDepartement.compareTo(o.leDepartement);
        }
        return ret;
    }

    /**
     * Renvoie une représentation textuelle des gares de la commune concernée
     * @return Une chaîne de caractères représentant les gares de la commune concernée
     */
    public String garesAsString() {
        String s = "";
        int i = 0;
        for (Gare gare : this.gares) {
            if (i != this.gares.size()) {
                s += gare.getCodeGare() + " (" + gare.getNomGare() + ") -";
            } else {
                s += gare.getCodeGare() + " (" + gare.getNomGare() + ")";
            }
            i++;
        }
        return s;
    }

    /**
     * Renvoie une représentation textuelle des voisins de la commune concernée
     * @return Une chaîne de caractères représentant les voisins de la commune concernée
     */
    public String voisinAsString() {
        String s = "";
        int i = 0;
        for (Commune voisin : this.voisins) {
            if (i != this.voisins.size()) {
                s += voisin.idCommune + " (" + voisin.nomCommune + ") -";
            } else {
                s += voisin.idCommune + " (" + voisin.nomCommune + ")";
            }
            i++;
        }
        return s;
    }

    /**
     * Renvoie une représentation textuelle de la commune concernée
     * @return Une chaîne de caractères représentant la commune concernée
     */
    public String toString() {
        return "Commune{" +
                "idCommune = " + this.idCommune +
                ", nomCommune = " + this.nomCommune +
                ", gares = " + this.garesAsString() +
                ", voisins = " + this.voisinAsString() +
                ", leDepartement = " + this.leDepartement.getIdDep() +
                "} ";
    }
}
