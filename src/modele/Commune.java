package modele;

import java.util.ArrayList;

/**
 * Une instance de cette classe permet de représenter une commune
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 * @see comparableTo
 * @see SwitcherFilter
 */
public class Commune implements Comparable<Commune> {
    /** Filtre de base */
    private static String currentFilter = "idCommune";
    /** Liste des filtres autorisés */
    private static String[] filtersList = new String[]{"idCommune", "nomCommune", "voisins","population"};
    /** Identifiant de la commune */
    private int idCommune;
    /** Nom de la commune */
    private String nomCommune;
    /** Liste des communes voisines */
    private ArrayList<Commune> voisins;

    /**
     * Permet d'initialiser une instance de la classe Commune
     * @param idCommune L'identifiant de la commune
     * @param nomCommune Le nom de la commune
     * @param voisins La liste des communes voisines
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public Commune(int idCommune, String nomCommune, ArrayList<Commune> voisins) throws IllegalArgumentException {
        if (idCommune <= 0) {
            throw new IllegalArgumentException("Commune.java : paramètre idCommune invalide");
        }
        if (nomCommune.equals("") || nomCommune == null) {
            throw new IllegalArgumentException("Commune.java : paramètre nomCommune invalide");
        }
        if (voisins == null) {
            throw new IllegalArgumentException("Commune.java : paramètre voisins invalide");
        }
        this.idCommune = idCommune;
        this.nomCommune = nomCommune;
        this.voisins = voisins;
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
     * Renvoie la liste de tous les filtres autorisés
     * @return La liste de tous les filtres autorisés
     */
    public static String[] getAllFilter() {
        return filtersList;
    }
    /**
     * Permet de définir le nouveau filtre à utiliser
     * @param filter Le nouveau filtre à utiliser
     */
    public static void setFilter(String filter) {
        for (String s : filtersList) {
            if (s.equals(filter)) {
                currentFilter = filter;
            }
        }
    }

    /**
     * Implémentation de l'interface Comparable.
     * Comparaison basée sur le filtre actuellement choisi
     * @param o Une autre instance de la classe Commune à comparer
     * @see SwitcherFilter
     */
    public int compareTo(Commune o) {
        int ret = 0;
        if (currentFilter.equals("idCommune")) {
           ret = Integer.compare(this.idCommune, o.idCommune);
        }
        if (currentFilter.equals("nomCommune")) {
            ret = this.nomCommune.compareTo(o.nomCommune);
        }
        return ret;
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
                s+= voisin.idCommune + " (" + voisin.nomCommune + ") -";
            } else {
                s+= voisin.idCommune + " (" + voisin.nomCommune + ")";
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
                "idCommune = " + idCommune +
                ", nomCommune = '" + nomCommune +
                ", voisins = " + voisinAsString() +
                "} ";
    }
}
