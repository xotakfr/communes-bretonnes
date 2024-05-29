package modele;

// TODO : javadoc ici 
/**
 * Classe représentant les données annuelles.
 */
public class DonneesAnnuelles implements Comparable<DonneesAnnuelles>{
    /** Filtre de base - Voir comparableTo.java et SwitecherFilter.java */
    private static String currentFilter = "lAnnee";
    /** Liste des filtres autorisés */
    private static String[] filtersList = new String[]{"lAnnee", "laCommune", "nbMaison", "nbAppart", "prixMoyen", "prixM2Moyen","depensesCulturellesTotales","budgetTotal", "population"};

    /** */
    private int lAnnee;

    /** */
    private int laCommune;

    /** */
    private int nbMaison;

    /** */
    private int nbAppart;

    /** */
    private float prixMoyen;

    /** */
    private float prixM2Moyen;

    /** */
    private float depensesCulturellesTotales;

    /** */
    private float budgetTotal;

    /** */
    private int population;

    /**
     * 
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
     * 
     */
    public int getAnnee() {
        return this.lAnnee;
    }

    /**
     * 
     */
    public int getCommune() {
        return this.laCommune;
    }

    /**
     * 
     */
    public int getNbMaison() {
        return nbMaison;
    }

    /**
     * 
     */
    public int getNbAppart() {
        return this.nbAppart;
    }

    /**
     * 
     */
    public float getPrixMoyen() {
        return this.prixMoyen;
    }

    /**
     * 
     */
    public float getPrixM2Moyen() {
        return this.prixM2Moyen;
    }

    /**
     * 
     */
    public float getDepensesCulturellesTotales() {
        return this.depensesCulturellesTotales;
    }

    /**
     * 
     */
    public float getBudgetTotal() {
        return this.budgetTotal;
    }

    /**
     * 
     */
    public int getPopulation() {
        return this.population;
    }

    /**
     * 
     */
    public void setAnnee(int lAnnee) {
        this.lAnnee = lAnnee;
    }

    /**
     * 
     */
    public void setCommune(int laCommune) {
        this.laCommune = laCommune;
    }

    /**
     * 
     */
    public void setNbMaison(int nbMaison) {
        this.nbMaison = nbMaison;
    }

    /**
     * 
     */
    public void setNbAppart(int nbAppart) {
        this.nbAppart = nbAppart;
    }

    /**
     * 
     */
    public void setPrixMoyen(float prixMoyen) {
        this.prixMoyen = prixMoyen;
    }

    /**
     * 
     */
    public void setPrixM2Moyen(float prixM2Moyen) {
        this.prixM2Moyen = prixM2Moyen;
    }

    /**
     * 
     */
    public void setDepensesCulturellesTotales(float depensesCulturellesTotales) {
        this.depensesCulturellesTotales = depensesCulturellesTotales;
    }

    /**
     * 
     */
    public void setBudgetTotal(float budgetTotal) {
        this.budgetTotal = budgetTotal;
    }

    /**
     * 
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * 
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
     * Implémentation de Comparable
     * Comparaison basé sur le filtre - voir classe SwitcherFilter
     * @param o Autre Commune à comparer
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
     * Getter Filter
     * @return String[]
     */
    public static String[] getAllFilter() {
        return filtersList;
    }
    /**
     * Setter Filter
     * @param filter String
     */
    public static void setFilter(String filter) {
        for (String s : filtersList) {
            if (s.equals(filter)) {
                currentFilter = filter;
            }
        }
    }
}
