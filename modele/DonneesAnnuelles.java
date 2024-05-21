package modele;

/**
 * Classe représentant les données annuelles.
 */
public class DonneesAnnuelles {
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
}
