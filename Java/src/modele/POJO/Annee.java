package modele.POJO;

/**
 * Une instance de cette classe permet de représenter une année.
 * Cette classe implémente l'interface Comparable pour permettre la comparaison entre les années
 * selon différents critères définis par un filtre
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 * @see Comparable
 */
public class Annee implements Comparable<Annee> {
    /** Filtre de base */
    private static String currentFilter = "annee";
    /** Liste des filtres autorisés */
    private static final String[] FILTER_LIST = new String[]{"annee", "tauxInflation"};
    /** Identifiant de l'année (correspond aussi à l'année réelle) */
    private int annee;
    /** Taux d'inflation associé à l'année */
    private float tauxInflation;

    /**
     * Permet d'initialiser une instance de la classe Annee.
     * On considère qu'il n'est pas possible d'utiliser des paramètres invalides puisque
     * l'univers des paramètres possibles est valide dans ce contexte
     * @param annee Identifiant de l'année (correspond aussi à l'année réelle)
     * @param tauxInflation Taux d'inflation associé à l'année
     */
    public Annee(int annee, float tauxInflation) {
        this.annee = annee;
        this.tauxInflation = tauxInflation;
    }

    /**
     * Renvoie l'identifiant de l'année
     * @return L'identifiant de l'année
     */
    public int getAnnee() {
        return this.annee;
    }

    /**
     * Permet de définir le nouveau identifiant de l'année
     * @param annee Le nouveau identifiant de l'année
     */
    public void setAnnee(int annee) {
        this.annee = annee;
    }

    /**
     * Renvoie le taux d'inflation de l'année
     * @return Le taux d'inflation de l'année
     */
    public float getTauxInflation() {
        return this.tauxInflation;
    }

    /**
     * Permet de définir le nouveau taux d'inflation de l'année
     * @param tauxInflation Le nouveau taux d'inflation de l'année
     */
    public void setTauxInflation(float tauxInflation) {
        this.tauxInflation = tauxInflation;
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
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     * @param filter Le nouveau filtre à utiliser
     */
    public static void setFilter(String filter) throws IllegalArgumentException {
        for (String s : FILTER_LIST) {
            if (s.equals(filter)) {
                currentFilter = filter;
                return; // empêche d'atteindre le lancement d'IAE si on trouve le filtre
            }
        }
        throw new IllegalArgumentException("Annee.java : paramètre filter invalide");
    }

    /**
     * Implémentation de l'interface Comparable.
     * Comparaison basée sur le filtre actuellement choisi
     * @param o Une autre instance de la classe Annee à comparer
     * @throws NullPointerException - si o est à null
     * @return Un entier représentant le résultat de la comparaison
     */
    @Override
    public int compareTo(Annee o) throws NullPointerException {
        int ret = 0;
        if (o == null) {
            throw new NullPointerException("Annee.java : paramètre o est null");
        }
        if (currentFilter.equals("annee")) {
           ret = Integer.compare(this.annee, o.annee);
        }
        else if (currentFilter.equals("tauxInflation")) {
            ret = Float.compare(this.tauxInflation, o.tauxInflation);
        }
        return ret;
    }

    /**
     * Renvoie une représentation textuelle de l'année concernée 
     * @return Une chaîne de caractères représentant l'année concernée
     */
    public String toString() {
        return "Annee{" +
                "annee = " + this.annee +
                ", tauxInflation = " + this.tauxInflation +
                "} ";
    }
}
