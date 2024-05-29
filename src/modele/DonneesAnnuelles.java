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
    private static String[] FILTERLIST = new String[]{"lAnnee", "laCommune", "nbMaison", "nbAppart", "prixMoyen", "prixM2Moyen","depensesCulturellesTotales","budgetTotal", "population"};
    /** Année associée aux données */
    private int lAnnee;
    /** Identifiant de la commune */
    private int laCommune;
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
     * @param lAnnee L'année associée aux données
     * @param laCommune L'identifiant de la commune
     * @param nbMaison Le nombre de maisons dans la commune
     * @param nbAppart Le nombre d'appartements dans la commune
     * @param prixMoyen Le prix moyen des biens immobiliers dans la commune
     * @param prixM2Moyen Le prix moyen au mètre carré dans la commune
     * @param depensesCulturellesTotales Les dépenses culturelles totales de la commune
     * @param budgetTotal Le budget total de la commune
     * @param population La population de la commune
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public DonneesAnnuelles(int lAnnee, int laCommune, int nbMaison, int nbAppart, float prixMoyen, float prixM2Moyen, float depensesCulturellesTotales, float budgetTotal, int population) throws IllegalArgumentException {
        this.lAnnee = lAnnee;
        if (laCommune <= 0) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre laCommune invalide");
        }
        this.laCommune = laCommune;
        if (nbMaison < 0) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre nbMaison invalide");
        }
        this.nbMaison = nbMaison;
        if (nbAppart < 0) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre nbAppart invalide");
        }
        this.nbAppart = nbAppart;
        if (prixMoyen < 0) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre prixMoyen invalide");
        }
        this.prixMoyen = prixMoyen;
        if (prixM2Moyen < 0) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre prixM2Moyen invalide");
        }
        this.prixM2Moyen = prixM2Moyen;
        if (depensesCulturellesTotales < 0) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre depensesCulturellesTotales invalide");
        }
        this.depensesCulturellesTotales = depensesCulturellesTotales;
        if (budgetTotal < 0) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre budgetTotal invalide");
        }
        this.budgetTotal = budgetTotal;
        if (population < 0) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre population invalide");
        }
        this.population = population;
    }

    /**
     * Renvoie l'année des données
     * @return L'année des données
     */
    public int getAnnee() {
        return this.lAnnee;
    }

    /**
     * Renvoie l'identifiant de la commune
     * @return L'identifiant de la commune
     */
    public int getCommune() {
        return this.laCommune;
    }

    /**
     * Renvoie le nombre de maisons
     * @return Le nombre de maisons
     */
    public int getNbMaison() {
        return this.nbMaison;
    }

    /**
     * Renvoie le nombre d'appartements
     * @return Le nombre d'appartements
     */
    public int getNbAppart() {
        return this.nbAppart;
    }

    /**
     * Renvoie le prix moyen des biens immobiliers
     * @return Le prix moyen des biens immobiliers
     */
    public float getPrixMoyen() {
        return this.prixMoyen;
    }

    /**
     * Renvoie le prix moyen au mètre carré
     * @return Le prix moyen au mètre carré
     */
    public float getPrixM2Moyen() {
        return this.prixM2Moyen;
    }

    /**
     * Renvoie les dépenses culturelles totales
     * @return Les dépenses culturelles totales
     */
    public float getDepensesCulturellesTotales() {
        return this.depensesCulturellesTotales;
    }

    /**
     * Renvoie le budget total
     * @return Le budget total
     */
    public float getBudgetTotal() {
        return this.budgetTotal;
    }

    /**
     * Renvoie la population de la commune
     * @return La population de la commune
     */
    public int getPopulation() {
        return this.population;
    }

    /**
     * Permet de définir la nouvelle année des données
     * @param lAnnee La nouvelle année des données
     */
    public void setAnnee(int lAnnee) {
        this.lAnnee = lAnnee;
    }

    /**
     * Permet de définir le nouvel identifiant de la commune
     * @param laCommune Le nouvel identifiant de la commune
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setCommune(int laCommune) throws IllegalArgumentException {
        if (laCommune < 0) {
            throw new IllegalArgumentException("DonneesAnnuelles.java : paramètre laCommune invalide");
        }
        this.laCommune = laCommune;
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
     * Renvoie la liste des filtres possibles pour la comparaison des données annuelles
     * @return La liste des filtres possibles pour la comparaison des données annuelles
     */
    public static String[] getAllFilter() {
        return FILTERLIST;
    }
    
    /**
     * Permet de définir le filtre à utiliser pour la comparaison des données annuelles
     * @param filter Le filtre à utiliser pour la comparaison des données annuelles
     */
    public static void setFilter(String filter) {
        for (String s : FILTERLIST) {
            if (s.equals(filter)) {
                currentFilter = filter;
            }
        }
    }

    /**
     * Renvoie une représentation textuelle d'une instance de la classe DonneesAnnuelles.
     * @return Une représentation textuelle d'une instance de la classe DonneesAnnuelles
     */
    public String toString() {
        return "DonneesAnnuelles{" +
                "Annee = " + lAnnee +
                ", Commune = " + laCommune +
                ", Nombre de maisons = " + nbMaison +
                ", Nombre d'appartements = " + nbAppart +
                ", Prix moyen = " + prixMoyen +
                ", Prix moyen au metre carre = " + prixM2Moyen +
                ", Depenses culturelles totales = " + depensesCulturellesTotales +
                ", Budget total = " + budgetTotal +
                ", Population = " + population +
                "} ";
    }

    /**
     * Implémentation de l'interface Comparable.
     * Comparaison basée sur le filtre
     * @param o Autre instance de DonneesAnnuelles à comparer
     * @return Un entier représentant le résultat de la comparaison :
     */
    public int compareTo(DonneesAnnuelles o) {
        int ret = 0;

        if (currentFilter.equals("lAnnee")) {
           ret = Integer.compare(this.lAnnee, o.lAnnee);
        }
        if (currentFilter.equals("laCommune")) {
           ret = Integer.compare(this.laCommune, o.laCommune);
        }
        if (currentFilter.equals("nbMaison")) {
           ret = Integer.compare(this.nbMaison, o.nbMaison);
        }
        if (currentFilter.equals("nbAppart")) {
           ret = Integer.compare(this.nbAppart, o.nbAppart);
        }
        if (currentFilter.equals("prixMoyen")) {
           ret = Float.compare(this.prixMoyen, o.prixMoyen);
        }
        if (currentFilter.equals("prixM2Moyen")) {
           ret = Float.compare(this.prixM2Moyen, o.prixM2Moyen);
        }
        if (currentFilter.equals("depensesCulturellesTotales")) {
           ret = Float.compare(this.depensesCulturellesTotales, o.depensesCulturellesTotales);
        }
        if (currentFilter.equals("budgetTotal")) {
           ret = Float.compare(this.budgetTotal, o.budgetTotal);
        }
        if (currentFilter.equals("nbAppart")) {
           ret = Integer.compare(this.population, o.population);
        }
        return ret;
    }
}
