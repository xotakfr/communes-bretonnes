package modele;

/**
 * Une instance de cette classe permet de représenter les données annuelles.
 * Cette classe implémente l'interface Comparable pour permettre la comparaison des données annuelles
 * selon différents critères définis par un filtre.
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 * @see Comparable
 */
public class DonneesAnnuelles implements Comparable<DonneesAnnuelles>{
    /** Filtre de base */
    private static String currentFilter = "lAnnee";
    /** Liste des filtres autorisés */
    private static final String[] FILTER_LIST = new String[]{"lAnnee", "laCommune", "nbMaison", "nbAppart", "prixMoyen", "prixM2Moyen","depensesCulturellesTotales","budgetTotal", "population"};
    /** Instance de la classe Annee associée aux données */
    private Annee lAnnee;
    /** Instance de la classe Commune associée aux données */
    private Commune laCommune;
    /** Nombre de maisons dans la commune */
    private int nbMaison;
    /** Nombre d'appartements dans la commune */
    private int nbAppart;
    /** Prix moyen des biens immobiliers dans la commune */
    private float prixMoyen;
    /** Prix moyen au mètre carré dans la commune */
    private float prixM2Moyen;
    /** Dépenses culturelles totales dans la commune*/
    private float depensesCulturellesTotales;
    /** Budget total de la commune */
    private float budgetTotal;
    /** Population de la commune */
    private int population;

    /**
     * Permet de construire une instance de la classe DonneesAnnuelles.
     * Cette classe implémente l'interface Comparable pour permettre la comparaison des données annuelles
     * selon différents critères définis par un filtre
     * @param lAnnee Instance de la classe Annee associée aux données
     * @param laCommune Instance de la classe Commune associée aux données
     * @param nbMaison Le nombre de maisons dans la commune
     * @param nbAppart Le nombre d'appartements dans la commune
     * @param prixMoyen Le prix moyen des biens immobiliers dans la commune
     * @param prixM2Moyen Le prix moyen au mètre carré dans la commune
     * @param depensesCulturellesTotales Les dépenses culturelles totales de la commune
     * @param budgetTotal Le budget total de la commune
     * @param population La population de la commune
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public DonneesAnnuelles(Annee lAnnee, Commune laCommune, int nbMaison, int nbAppart, float prixMoyen, float prixM2Moyen, float depensesCulturellesTotales, float budgetTotal, int population) throws IllegalArgumentException {
        if (lAnnee == null) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre lAnnee invalide");
        }
        if (laCommune == null) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre laCommune invalide");
        }
        if (nbMaison < 0) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre nbMaison invalide");
        }
        if (nbAppart < 0) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre nbAppart invalide");
        }
        if (prixMoyen < 0) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre prixMoyen invalide");
        }
        if (prixM2Moyen < 0) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre prixM2Moyen invalide");
        }
        if (depensesCulturellesTotales < 0) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre depensesCulturellesTotales invalide");
        }
        if (budgetTotal < 0) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre budgetTotal invalide");
        }
        if (population < 0) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre population invalide");
        }
        this.lAnnee = lAnnee;
        this.laCommune = laCommune;
        this.nbMaison = nbMaison;
        this.nbAppart = nbAppart;
        this.prixMoyen = prixMoyen;
        this.prixM2Moyen = prixM2Moyen;
        this.depensesCulturellesTotales = depensesCulturellesTotales;
        this.budgetTotal = budgetTotal;
        this.population = population;
    }

    /**
     * Renvoie l'instance de la classe Annee associée aux données
     * @return L'instance de la classe Annee associée aux données
     */
    public Annee getAnnee() {
        return this.lAnnee;
    }

    /**
     * Permet de définir la nouvelle instance de la classe Annee associée aux données
     * @param lAnnee La nouvelle instance de la classe Annee associée aux données
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setAnnee(Annee lAnnee) throws IllegalArgumentException {
        if (lAnnee == null) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre lAnnee invalide");
        }
        this.lAnnee = lAnnee;
    }

    /**
     * Renvoie l'instance de la classe Commune associée aux données
     * @return L'instance de la classe Commune associée aux données
     */
    public Commune getCommune() {
        return this.laCommune;
    }

    /**
     * Permet de définir la nouvelle instance de la classe Commune associée aux données
     * @param laCommune La nouvelle instance de la classe Commune associée aux données
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setCommune(Commune laCommune) throws IllegalArgumentException {
        if (laCommune == null) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre laCommune invalide");
        }
        this.laCommune = laCommune;
    }

    /**
     * Renvoie le nombre de maisons
     * @return Le nombre de maisons
     */
    public int getNbMaison() {
        return this.nbMaison;
    }

    /**
     * Permet de définir le nouveau nombre de maisons dans une commune
     * @param nbMaison Le nouveau nombre de maisons dans une commune 
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setNbMaison(int nbMaison) throws IllegalArgumentException {
        if (nbMaison < 0) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre nbMaison invalide");
        }
        this.nbMaison = nbMaison;
    }

    /**
     * Renvoie le nombre d'appartements
     * @return Le nombre d'appartements
     */
    public int getNbAppart() {
        return this.nbAppart;
    }

    /**
     * Permet de définir le nouveau nombre d'appartements dans une commune
     * @param nbAppart Le nouveau nombre d'appartements dans une commune
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setNbAppart(int nbAppart) throws IllegalArgumentException {
        if (nbAppart < 0) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre nbAppart invalide");
        }
        this.nbAppart = nbAppart;
    }

    /**
     * Renvoie le prix moyen des biens immobiliers
     * @return Le prix moyen des biens immobiliers
     */
    public float getPrixMoyen() {
        return this.prixMoyen;
    }

    /**
     * Permet de définir le nouveau prix moyen des biens immobiliers
     * @param prixMoyen Le nouveau prix moyen des biens immobiliers
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setPrixMoyen(float prixMoyen) throws IllegalArgumentException {
        if (prixMoyen < 0) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre prixMoyen invalide");
        }
        this.prixMoyen = prixMoyen;
    }

    /**
     * Renvoie le prix moyen au mètre carré
     * @return Le prix moyen au mètre carré
     */
    public float getPrixM2Moyen() {
        return this.prixM2Moyen;
    }

    /**
     * Permet de définir le nouveau prix moyen au mètre carré
     * @param prixM2Moyen Le nouveau prix moyen au mètre carré
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setPrixM2Moyen(float prixM2Moyen) throws IllegalArgumentException {
        if (prixM2Moyen < 0) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre prixM2Moyen invalide");
        }
        this.prixM2Moyen = prixM2Moyen;
    }

    /**
     * Renvoie les dépenses culturelles totales
     * @return Les dépenses culturelles totales
     */
    public float getDepensesCulturellesTotales() {
        return this.depensesCulturellesTotales;
    }

    /**
     * Permet de définir la nouvelle quantité des dépenses culturelles totales
     * @param depensesCulturellesTotales La nouvelle quantité des dépenses culturelles totales
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setDepensesCulturellesTotales(float depensesCulturellesTotales) throws IllegalArgumentException {
        if (depensesCulturellesTotales < 0) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre depensesCulturellesTotales invalide");
        }
        this.depensesCulturellesTotales = depensesCulturellesTotales;
    }

    /**
     * Renvoie le budget total
     * @return Le budget total
     */
    public float getBudgetTotal() {
        return this.budgetTotal;
    }

    /**
     * Permet de définir le nouveau budget total
     * @param budgetTotal Le nouveau budget total
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setBudgetTotal(float budgetTotal) throws IllegalArgumentException {
        if (budgetTotal < 0) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre budgetTotal invalide");
        }
        this.budgetTotal = budgetTotal;
    }

    /**
     * Renvoie la population de la commune
     * @return La population de la commune
     */
    public int getPopulation() {
        return this.population;
    }

    /**
     * Permet de définir la nouvelle population de la commune
     * @param population La nouvelle population de la commune
     */
    public void setPopulation(int population) {
        if (population < 0) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre population invalide");
        }
        this.population = population;
    }

    /**
     * Renvoie la liste de tous les filtres autorisés
     * @return La liste de tous les filtres autorisés
     */
    public static String[] getAllFilter() {
        return FILTER_LIST;
    }

    /**
     * Permet de définir le nouveau filtre à utiliser
     * @param filter Le nouveau filtre à utiliser
     */
    public static void setFilter(String filter) {
        for (String s : FILTER_LIST) {
            if (s.equals(filter)) {
                currentFilter = filter;
            }
        }
    }

    /**
     * Implémentation de l'interface Comparable.
     * Comparaison basée sur le filtre actuellement choisi
     * @param o Autre instance de DonneesAnnuelles à comparer
     * @throws NullPointerException - si o est à null
     * @return Un entier représentant le résultat de la comparaison
     */
    public int compareTo(DonneesAnnuelles o) throws NullPointerException {
        int ret = 0;
        if (o == null) {
            throw new NullPointerException("DonneesAnnuelles.java : paramètre o invalide");
        }
        if (currentFilter.equals("lAnnee")) {
           ret = Integer.compare(this.lAnnee.getDateAnnee(), o.lAnnee.getDateAnnee());
        }
        else if (currentFilter.equals("laCommune")) {
           ret = Integer.compare(this.laCommune.getIdCommune(), o.laCommune.getIdCommune());
        }
        else if (currentFilter.equals("nbMaison")) {
           ret = Integer.compare(this.nbMaison, o.nbMaison);
        }
        else if (currentFilter.equals("nbAppart")) {
           ret = Integer.compare(this.nbAppart, o.nbAppart);
        }
        else if (currentFilter.equals("prixMoyen")) {
           ret = Float.compare(this.prixMoyen, o.prixMoyen);
        }
        else if (currentFilter.equals("prixM2Moyen")) {
           ret = Float.compare(this.prixM2Moyen, o.prixM2Moyen);
        }
        else if (currentFilter.equals("depensesCulturellesTotales")) {
           ret = Float.compare(this.depensesCulturellesTotales, o.depensesCulturellesTotales);
        }
        else if (currentFilter.equals("budgetTotal")) {
           ret = Float.compare(this.budgetTotal, o.budgetTotal);
        }
        else if (currentFilter.equals("population")) {
           ret = Integer.compare(this.population, o.population);
        }
        return ret;
    }

    /**
     * Renvoie une représentation textuelle des données annuelles concernées
     * @return Une représentation textuelle des données annuelles concernées
     */
    public String toString() {
        return "DonneesAnnuelles{" +
                "Annee = " + this.lAnnee.getDateAnnee() +
                ", Commune = " + this.laCommune.getIdCommune() +
                ", Nombre de maisons = " + this.nbMaison +
                ", Nombre d'appartements = " + this.nbAppart +
                ", Prix moyen = " + this.prixMoyen +
                ", Prix moyen au metre carre = " + this.prixM2Moyen +
                ", Depenses culturelles totales = " + this.depensesCulturellesTotales +
                ", Budget total = " + this.budgetTotal +
                ", Population = " + this.population +
                "} ";
    }
}
