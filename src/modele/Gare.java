package modele;

/**
 * Classe représentant une gare.
 */
public class Gare {
    /** Code de la gare */
    private int codeGare;

    /** Nom de la gare */
    private String nomGare;

    /** Indique si la gare est destinée au fret */
    private boolean estFret;

    /** Indique si la gare est destinée aux voyageurs */
    private boolean estVoyageur;

    /** Commune d'origine de la gare */
    private Commune communeOrigine;

    /**
     * Constructeur de la classe Gare.
     * @param codeGare Le code de la gare.
     * @param nomGare Le nom de la gare.
     * @param estFret Indique si la gare est destinée au fret.
     * @param estVoyageur Indique si la gare est destinée aux voyageurs.
     * @param communeOrigine La commune d'origine de la gare.
     */
    public Gare(int codeGare, String nomGare, boolean estFret, boolean estVoyageur, Commune communeOrigine) {
        this.codeGare = codeGare;
        this.nomGare = nomGare;
        this.estFret = estFret;
        this.estVoyageur = estVoyageur;
        this.communeOrigine = communeOrigine;
    }

    /**
     * Obtient le code de la gare.
     * @return Le code de la gare.
     */
    public int getCodeGare() {
        return this.codeGare;
    }

    /**
     * Obtient le nom de la gare.
     * @return Le nom de la gare.
     */
    public String getNomGare() {
        return this.nomGare;
    }

    /**
     * Vérifie si la gare est destinée au fret.
     * @return true si la gare est destinée au fret, sinon false.
     */
    public boolean isEstFret() {
        return this.estFret;
    }

    /**
     * Vérifie si la gare est destinée aux voyageurs.
     * @return true si la gare est destinée aux voyageurs, sinon false.
     */
    public boolean isEstVoyageur() {
        return this.estVoyageur;
    }

    /**
     * Obtient la commune d'origine de la gare.
     * @return La commune d'origine de la gare.
     */
    public Commune getCommuneOrigine() {
        return this.communeOrigine;
    }

    /**
     * Définit le code de la gare.
     * @param codeGare Le nouveau code de la gare.
     */
    public void setCodeGare(int codeGare) {
        this.codeGare = codeGare;
    }

    /**
     * Définit le nom de la gare.
     * @param nomGare Le nouveau nom de la gare.
     */
    public void setNomGare(String nomGare) {
        this.nomGare = nomGare;
    }

    /**
     * Définit si la gare est destinée au fret.
     * @param estFret true si la gare est destinée au fret, sinon false.
     */
    public void setEstFret(boolean estFret) {
        this.estFret = estFret;
    }

    /**
     * Définit si la gare est destinée aux voyageurs.
     * @param estVoyageur true si la gare est destinée aux voyageurs, sinon false.
     */
    public void setEstVoyageur(boolean estVoyageur) {
        this.estVoyageur = estVoyageur;
    }

    /**
     * Définit la commune d'origine de la gare.
     * @param communeOrigine La nouvelle commune d'origine de la gare.
     */
    public void setCommuneOrigine(Commune communeOrigine) {
        this.communeOrigine = communeOrigine;
    }

    /**
     * Méthode pour obtenir une représentation textuelle de la gare.
     * @return Une chaîne de caractères représentant la gare.
     */
    public String toString() {
        return "Gare{" +
                "codeGare=" + codeGare +
                ", nomGare='" + nomGare + '\'' +
                ", estFret=" + estFret +
                ", estVoyageur=" + estVoyageur +
                ", communeOrigine=" + communeOrigine +
                '}';
    }
}
