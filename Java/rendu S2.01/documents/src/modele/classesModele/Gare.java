package modele.data;

/**
 * Une instance de cette classe permet de représenter une gare.
 * Cette classe implémente l'interface Comparable pour permettre la comparaison entre les gares
 * selon différents critères définis par un filtre
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 * @see Comparable
 */
public class Gare implements Comparable<Gare> {
    /** Filtre de base */
    private static String currentFilter = "codeGare";
    /** Liste des filtres autorisés */
    private static final String[] FILTER_LIST = new String[]{"codeGare", "nomGare", "estFret", "estVoyageur", "laCommune"};
    /** Code de la gare */
    private int codeGare;
    /** Nom de la gare */
    private String nomGare;
    /** Indique si la gare est destinée au fret */
    private boolean estFret;
    /** Indique si la gare est destinée aux voyageurs */
    private boolean estVoyageur;
    /** Commune associée à la gare */
    private Commune laCommune;

    /**
     * Permet d'initialiser une instance de la classe Gare
     * @param codeGare Le code de la gare
     * @param nomGare Le nom de la gare
     * @param estFret Indique si la gare est destinée au fret
     * @param estVoyageur Indique si la gare est destinée aux voyageurs
     * @param laCommune La commune associée à la gare
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public Gare(int codeGare, String nomGare, boolean estFret, boolean estVoyageur, Commune laCommune) {
        if (codeGare <= 0) {
            throw new IllegalArgumentException("Gare.java : paramètre codeGare invalide");
        }
        if (nomGare == null || nomGare.isEmpty()) {
            throw new IllegalArgumentException("Gare.java : paramètre nomGare invalide");
        }
        if (laCommune == null) {
            throw new IllegalArgumentException("Gare.java : paramètre laCommune invalide");
        }
        this.codeGare = codeGare;
        this.nomGare = nomGare;
        this.estFret = estFret;
        this.estVoyageur = estVoyageur;
        this.laCommune = laCommune;
    }

    /**
     * Renvoie le code de la gare
     * @return Le code de la gare
     */
    public int getCodeGare() {
        return this.codeGare;
    }

    /**
     * Permet de définir le nouveau code de la gare
     * @param codeGare Le nouveau code de la gare
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setCodeGare(int codeGare) throws IllegalArgumentException {
        if (codeGare <= 0) {
            throw new IllegalArgumentException("Gare.java : paramètre codeGare invalide");
        }
        this.codeGare = codeGare;
    }

    /**
     * Renvoie le nom de la gare
     * @return Le nom de la gare
     */
    public String getNomGare() {
        return this.nomGare;
    }

    /**
     * Permet de définir le nouveau nom de la gare
     * @param nomGare Le nouveau nom de la gare
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setNomGare(String nomGare) throws IllegalArgumentException {
        if (nomGare == null || nomGare.isEmpty()) {
            throw new IllegalArgumentException("Gare.java : paramètre nomGare invalide");
        }
        this.nomGare = nomGare;
    }

    /**
     * Renvoie si la gare est destinée au fret
     * @return true si la gare est destinée au fret, sinon false
     */
    public boolean getFretValue() {
        return this.estFret;
    }

    /**
     * Permet de redéfinir si la gare est destinée au fret
     * @param estFret true si la gare est destinée au fret, sinon false
     */
    public void setFretValue(boolean estFret) {
        this.estFret = estFret;
    }

    /**
     * Renvoie si la gare est destinée aux voyageurs
     * @return true si la gare est destinée aux voyageurs, sinon false
     */
    public boolean getVoyageurValue() {
        return this.estVoyageur;
    }

    /**
     * Permet de redéfinir si la gare est destinée aux voyageurs
     * @param estVoyageur true si la gare est destinée aux voyageurs, sinon false.
     */
    public void setVoyageurValue(boolean estVoyageur) {
        this.estVoyageur = estVoyageur;
    }

    /**
     * Renvoie la commune associée à la gare
     * @return La commune associée à la gare
     */
    public Commune getLaCommune() {
        return this.laCommune;
    }

    /**
     * Permet de définir la nouvelle commune associée à la gare
     * @param laCommune La nouvelle commune associée à la gare
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setLaCommune(Commune laCommune) throws IllegalArgumentException {
        if (laCommune == null) {
            throw new IllegalArgumentException("Gare.java : paramètre laCommune invalide");
        }
        this.laCommune = laCommune;
    }

    /**
     * Renvoie la liste de tous les filtres autorisés
     * @return La liste de tous les filtres autorisés
     */
    public static String[] getAllFilter() {
        return FILTER_LIST.clone();
    }

    /**
     * Permet de définir le nouveau filtre à utiliser
     * @param filter Le nouveau filtre à utiliser
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public static void setFilter(String filter) throws IllegalArgumentException {
        for (String s : FILTER_LIST) {
            if (s.equals(filter)) {
                currentFilter = filter;
                return;
            }
        }
        throw new IllegalArgumentException("Gare.java : paramètre filter invalide");
    }

    /**
     * Implémentation de l'interface Comparable.
     * Comparaison basée sur le filtre actuellement choisi
     * @param o Une autre instance de la classe Gare à comparer
     * @throws NullPointerException - si o est à null
     * @return Un entier représentant le résultat de la comparaison
     */
    public int compareTo(Gare o) throws NullPointerException {
        int ret = 0;
        if (o == null) {
            throw new NullPointerException("Gare.java : paramètre o invalide");
        }
        ret = switch (currentFilter) {
            case "codeGare" -> Integer.compare(this.codeGare, o.codeGare);
            case "nomGare" -> this.nomGare.compareTo(o.nomGare);
            case "estFret" -> Boolean.compare(this.estFret, o.estFret);
            case "estVoyageur" -> Boolean.compare(this.estVoyageur, o.estVoyageur);
            case "laCommune" -> Integer.compare(this.laCommune.getIdCommune(), o.laCommune.getIdCommune());
            default -> ret;
        };
        return ret;
    }

    /**
     * Renvoie une représentation textuelle de la gare concernée
     * @return Une chaîne de caractères représentant la gare concernée
     */
    public String toString() {
        return "Gare{" +
                "codeGare = " + this.codeGare +
                ", nomGare = " + this.nomGare +
                ", estFret = " + this.estFret +
                ", estVoyageur = " + this.estVoyageur +
                ", laCommune = " + this.laCommune.getIdCommune() +
                "} ";
    }
}