package modele;

import java.util.ArrayList;

/**
 * Représente une commune.
 */
public class Commune implements Comparable<Commune>{
    /** Identifiant de la commune */
    private int idCommune;

    /** Nom de la commune */
    private String nomCommune;

    /** Liste des communes voisines */
    private ArrayList<Commune> voisins;

    /**
     * Constructeur de la classe Commune.
     *
     * @param idCommune L'identifiant de la commune.
     * @param nomCommune Le nom de la commune.
     * @param voisins La liste des communes voisines.
     */
    public Commune(int idCommune, String nomCommune, ArrayList<Commune> voisins) {
        this.idCommune = idCommune;
        this.nomCommune = nomCommune;
        this.voisins = voisins;
    }

    /**
     * Obtient l'identifiant de la commune.
     * @return L'identifiant de la commune.
     */
    public int getIdCommune() {
        return this.idCommune;
    }

    /**
     * Obtient le nom de la commune.
     * @return Le nom de la commune.
     */
    public String getNomCommune() {
        return this.nomCommune;
    }

    /**
     * Obtient la liste des communes voisines.
     * @return La liste des communes voisines.
     */
    public ArrayList<Commune> getVoisins() {
        return this.voisins;
    }

    /**
     * Définit l'identifiant de la commune.
     * @param idCommune Le nouvel identifiant de la commune.
     */
    public void setIdCommune(int idCommune) {
        this.idCommune = idCommune;
    }

    /**
     * Définit le nom de la commune.
     * @param nomCommune Le nouveau nom de la commune.
     */
    public void setNomCommune(String nomCommune) {
        this.nomCommune = nomCommune;
    }

    /**
     * Définit la liste des communes voisines.
     * @param voisins La nouvelle liste des communes voisines.
     */
    public void setVoisins(ArrayList<Commune> voisins) {
        this.voisins = voisins;
    }

    /**
     * Méthode pour obtenir une représentation textuelle des voisins.
     * Utilisée pour éviter un stackoverflow et avoir un bel affichage
     * @return Une chaîne de caractères représentant les voisins.
     */
    public String voisinAsString() {
        String s = "";
        for (Commune voisin : this.voisins) {
            s=s+voisin.nomCommune+"("+voisin.idCommune+");";
        }
        return s;
    }

    /**
     * Méthode pour obtenir une représentation textuelle de la commune.
     * @return Une chaîne de caractères représentant la commune.
     */
    public String toString() {
        return "Commune{" +
                "idCommune=" + idCommune +
                ", nomCommune='" + nomCommune + '\'' +
                ", voisins=" + voisinAsString() +
                '}';
    }

    /**
     * Implémentation de Comparable
     * Comparaison basé uniquement sur le CODE INSEE pour l'instant
     * à baser sur le filtre
     * @param o Autre commune à comparer
     */
    public int compareTo(Commune o) {
        int ret = 0;
        if (this.idCommune>o.idCommune) {
            ret = -1;
        } else if (this.idCommune<o.idCommune) {
            ret = 1;
        }
        return ret;
    }
}