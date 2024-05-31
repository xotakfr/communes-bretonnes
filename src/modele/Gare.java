package modele;

/**
 * Classe représentant une gare.
 */
public class Gare {
    /** Filtre actuel - Voir comparableTo et SwitecherFilter */
    private static String currentFilter = "codeGare";

    /** Liste des filtres possibles */
    private static String[] filtersList = new String[]{"codeGare", "nomGare", "estFret", "estVoyageur", "communeOrigine"};

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
     * 
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
     * 
     * @return Le code de la gare.
     */
    public int getCodeGare() {
        return this.codeGare;
    }

    /**
     * Obtient le nom de la gare.
     * 
     * @return Le nom de la gare.
     */
    public String getNomGare() {
        return this.nomGare;
    }

    /**
     * Vérifie si la gare est destinée au fret.
     * 
     * @return true si la gare est destinée au fret, sinon false.
     */
    public boolean isEstFret() {
        return this.estFret;
    }

    /**
     * Vérifie si la gare est destinée aux voyageurs.
     * 
     * @return true si la gare est destinée aux voyageurs, sinon false.
     */
    public boolean isEstVoyageur() {
        return this.estVoyageur;
    }

    /**
     * Obtient la commune d'origine de la gare.
     * 
     * @return La commune d'origine de la gare.
     */
    public Commune getCommuneOrigine() {
        return this.communeOrigine;
    }

    /**
     * Définit le code de la gare.
     * 
     * @param codeGare Le nouveau code de la gare.
     */
    public void setCodeGare(int codeGare) {
        this.codeGare = codeGare;
    }

    /**
     * Définit le nom de la gare.
     * 
     * @param nomGare Le nouveau nom de la gare.
     */
    public void setNomGare(String nomGare) {
        this.nomGare = nomGare;
    }

    /**
     * Définit si la gare est destinée au fret.
     * 
     * @param estFret true si la gare est destinée au fret, sinon false.
     */
    public void setEstFret(boolean estFret) {
        this.estFret = estFret;
    }

    /**
     * Définit si la gare est destinée aux voyageurs.
     * 
     * @param estVoyageur true si la gare est destinée aux voyageurs, sinon false.
     */
    public void setEstVoyageur(boolean estVoyageur) {
        this.estVoyageur = estVoyageur;
    }

    /**
     * Définit la commune d'origine de la gare.
     * 
     * @param communeOrigine La nouvelle commune d'origine de la gare.
     */
    public void setCommuneOrigine(Commune communeOrigine) {
        this.communeOrigine = communeOrigine;
    }

    /**
     * Méthode pour obtenir une représentation textuelle de la gare.
     * 
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

    /**
     * Implémentation de Comparable
     * Comparaison basé sur le filtre - voir classe SwitcherFilter
     * 
     * @param o Autre Gare à comparer
     */
    public int compareTo(Gare o) {
        int ret = 0;

        if (currentFilter.equals("codeGare")) {
           ret = Integer.compare(this.codeGare, o.codeGare);
        }
        if (currentFilter.equals("nomGare")) {
            ret = this.nomGare.compareTo(o.nomGare);
        }
        if (currentFilter.equals("estFret")) {
            ret = Boolean.compare(this.estFret, o.estFret);
        }
        if (currentFilter.equals("estVoyageur")) {
            ret = Boolean.compare(this.estVoyageur, o.estVoyageur);
        }
        if (currentFilter.equals("communeOrigine")) {
            ret = this.communeOrigine.compareTo(o.communeOrigine);
        }
        return ret;
    }

    /**
     * Obtient la liste des filtres possibles pour la classe Gare.
     * 
     * @return Un tableau de chaînes de caractères représentant les filtres possibles.
     */
    public static String[] getAllFilter() {
        return filtersList;
    }
    
    /**
     * Définit le filtre à utiliser pour la comparaison des gares.
     * 
     * @param filter Le nom du filtre à utiliser.
     */
    public static void setFilter(String filter) {
        for (String s : filtersList) {
            if (s.equals(filter)) {
                currentFilter = filter;
            }
        }
    }
    // TODO : tout trier
}
