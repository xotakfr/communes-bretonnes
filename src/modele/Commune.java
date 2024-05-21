package modele;

import java.util.ArrayList;

/**
 * Représente une commune.
 */
public class Commune {
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
     * Méthode pour obtenir une représentation textuelle de la commune.
     * @return Une chaîne de caractères représentant la commune.
     */
    public String toString() {
        return "Commune{" +
                "idCommune=" + idCommune +
                ", nomCommune='" + nomCommune + '\'' +
                ", voisins=" + voisins +
                '}';
    }
}