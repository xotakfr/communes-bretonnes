package modele;

// TODO : javadoc ici 
/**
 * Classe représentant les données annuelles.
 * Cette classe implémente l'interface Comparable pour permettre la comparaison des données annuelles
 * selon différents critères définis par un filtre.
 * 
 * @see Comparable
 */
public class DonneesAnnuelles implements Comparable<DonneesAnnuelles>{
    /** Filtre de base - Voir comparableTo.java et SwitecherFilter.java */
    private static String currentFilter = "lAnnee";
    /** Liste des filtres autorisés */
    private static String[] filtersList = new String[]{"lAnnee", "laCommune", "nbMaison", "nbAppart", "prixMoyen", "prixM2Moyen","depensesCulturellesTotales","budgetTotal", "population"};

    /** Année des données */
    private int lAnnee;

    /** Identifiant de la commune */
    private int laCommune;

    /** Nombre de maisons */
    private int nbMaison;

    /** Nombre d'appartements */
    private int nbAppart;

    /** Prix moyen des biens immobiliers */
    private float prixMoyen;

    /** Prix moyen au mètre carré */
    private float prixM2Moyen;

    /** Dépenses culturelles totales */
    private float depensesCulturellesTotales;

    /** Budget total */
    private float budgetTotal;

    /** Population de la commune */
    private int population;

    /**
     * Constructeur de la classe DonneesAnnuelles.
     * 
     * @param lAnnee L'année des données
     * @param laCommune L'identifiant de la commune
     * @param nbMaison Le nombre de maisons
     * @param nbAppart Le nombre d'appartements
     * @param prixMoyen Le prix moyen des biens immobiliers
     * @param prixM2Moyen Le prix moyen au mètre carré
     * @param depensesCulturellesTotales Les dépenses culturelles totales
     * @param budgetTotal Le budget total
     * @param population La population de la commune
     */
    public DonneesAnnuelles(int lAnnee, int laCommune, int nbMaison, int nbAppart, float prixMoyen, float prixM2Moyen, float depensesCulturellesTotales, float budgetTotal, int population) {
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
     * Renvoie l'année des données.
     * 
     * @return L'année des données
     */
    public int getAnnee() {
        return this.lAnnee;
    }

    /**
     * Renvoie l'identifiant de la commune.
     * 
     * @return L'identifiant de la commune
     */
    public int getCommune() {
        return this.laCommune;
    }

    /**
     * Renvoie le nombre de maisons.
     * 
     * @return Le nombre de maisons
     */
    public int getNbMaison() {
        return nbMaison;
    }

    /**
     * Renvoie le nombre d'appartements.
     * 
     * @return Le nombre d'appartements
     */
    public int getNbAppart() {
        return this.nbAppart;
    }

    /**
     * Renvoie le prix moyen des biens immobiliers.
     * 
     * @return Le prix moyen des biens immobiliers
     */
    public float getPrixMoyen() {
        return this.prixMoyen;
    }

    /**
     * Renvoie le prix moyen au mètre carré.
     * 
     * @return Le prix moyen au mètre carré
     */
    public float getPrixM2Moyen() {
        return this.prixM2Moyen;
    }

    /**
     * Renvoie les dépenses culturelles totales.
     * 
     * @return Les dépenses culturelles totales
     */
    public float getDepensesCulturellesTotales() {
        return this.depensesCulturellesTotales;
    }

    /**
     * Renvoie le budget total.
     * 
     * @return Le budget total
     */
    public float getBudgetTotal() {
        return this.budgetTotal;
    }

    /**
     * Renvoie la population de la commune.
     * 
     * @return La population de la commune
     */
    public int getPopulation() {
        return this.population;
    }

    /**
     * Définit l'année des données.
     * 
     * @param lAnnee L'année des données
     */
    public void setAnnee(int lAnnee) {
        this.lAnnee = lAnnee;
    }

    /**
     * Définit l'identifiant de la commune.
     * 
     * @param laCommune L'identifiant de la commune
     */
    public void setCommune(int laCommune) {
        this.laCommune = laCommune;
    }

    /**
     * Définit le nombre de maisons.
     * 
     * @param nbMaison Le nombre de maisons
     */
    public void setNbMaison(int nbMaison) {
        this.nbMaison = nbMaison;
    }

    /**
     * Définit le nombre d'appartements.
     * 
     * @param nbAppart Le nombre d'appartements
     */
    public void setNbAppart(int nbAppart) {
        this.nbAppart = nbAppart;
    }

    /**
     * Définit le prix moyen des biens immobiliers.
     * 
     * @param prixMoyen Le prix moyen des biens immobiliers
     */
    public void setPrixMoyen(float prixMoyen) {
        this.prixMoyen = prixMoyen;
    }

    /**
     * Définit le prix moyen au mètre carré.
     * 
     * @param prixM2Moyen Le prix moyen au mètre carré
     */
    public void setPrixM2Moyen(float prixM2Moyen) {
        this.prixM2Moyen = prixM2Moyen;
    }

    /**
     * Définit les dépenses culturelles totales.
     * 
     * @param depensesCulturellesTotales Les dépenses culturelles totales
     */
    public void setDepensesCulturellesTotales(float depensesCulturellesTotales) {
        this.depensesCulturellesTotales = depensesCulturellesTotales;
    }

    /**
     * Définit le budget total.
     * 
     * @param budgetTotal Le budget total
     */
    public void setBudgetTotal(float budgetTotal) {
        this.budgetTotal = budgetTotal;
    }

    /**
     * Définit la population de la commune.
     * 
     * @param population La population de la commune
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * Renvoie une représentation textuelle d'une instance de la classe DonneesAnnuelles.
     * 
     * @return Une représentation textuelle d'une instance de la classe DonneesAnnuelles
     */
    public String toString() {
        return "Donnees Annuelles {" +
                "Annee=" + lAnnee +
                ", Commune=" + laCommune +
                ", Nombre de Maisons=" + nbMaison +
                ", Nombre d'Appartements=" + nbAppart +
                ", Prix Moyen=" + prixMoyen +
                ", Prix Moyen au metre carre=" + prixM2Moyen +
                ", Depenses Culturelles Totales=" + depensesCulturellesTotales +
                ", Budget Total=" + budgetTotal +
                ", Population=" + population +
                '}';
    }

    /**
     * Implémentation de Comparable.
     * Comparaison basée sur le filtre - voir classe SwitcherFilter
     * 
     * @param o Autre instance de DonneesAnnuelles à comparer
     * @return Un entier représentant le résultat de la comparaison :
     * - négatif si this < o
     * - zéro si this == o
     * - positif si this > o
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

    /**
     * Renvoie la liste des filtres possibles pour la comparaison des données annuelles.
     * 
     * @return Un tableau de String contenant les filtres possibles
     */
    public static String[] getAllFilter() {
        return filtersList;
    }
    
    /**
     * Permet de définir le filtre à utiliser pour la comparaison des données annuelles.
     * 
     * @param filter Le filtre à utiliser
     */
    public static void setFilter(String filter) {
        for (String s : filtersList) {
            if (s.equals(filter)) {
                currentFilter = filter;
            }
        }
    }
}
