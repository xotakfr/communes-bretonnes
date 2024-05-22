package modele;

import tri.*;

import java.util.ArrayList;

/**
 * Représente une commune.
 */
public class Commune implements Comparable<Commune>{
    /** Filtre actuel - Voir comparableTo et SwitecherFilter */
    private static String currentFilter = "idCommune";
    /** Liste des filtres possibles */
    private static String[] filtersList = new String[]{"idCommune", "nomCommune", "voisins","population"};
    /** Identifiant de la commune */
    private int idCommune;

    /** Nom de la commune */
    private String nomCommune;

    /** Liste des communes voisines */
    private ArrayList<Commune> voisins;



    private int population = -1;

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
     * Méthode pour obtenir une représentation textuelle des voisins.
     * Utilisée pour éviter un stackoverflow et avoir un bel affichage
     * @return Une chaîne de caractères représentant les voisins.
     */
    public String voisinAsString() {
        String s = "";
        for (Commune voisin : this.voisins) {
            s=s+voisin.nomCommune+"("+voisin.idCommune+");";
        }
        return s;
    }

    /**
     * Méthode pour récupérer la population 2019 depuis DonneesAnnuelles
     * @return population int
     */
    public int getPopulation() {
        int pop = this.population;
        if (this.population==-1) {
            DonneesAnnuelles.setFilter("laCommune");
            TriRapide<DonneesAnnuelles> trieur = new TriRapide<DonneesAnnuelles>(DataLoader.getDonneesAnnuelles());
            trieur.trier();
            BinarySearcher<DonneesAnnuelles> searcher = new BinarySearcher<DonneesAnnuelles>();
            int idA = searcher.search(DataLoader.getDonneesAnnuelles(),new DonneesAnnuelles(0,this.idCommune, 0, 0, 0f, 0f,0f, 0f, 0));
            pop = DataLoader.getDonneesAnnuelles().get(idA).getPopulation();
        }
        return pop;
    }
    public void setPopulation(int a) {
        this.population = a;
    }

    /**
     * Méthode pour obtenir une représentation textuelle de la commune.
     * @return Une chaîne de caractères représentant la commune.
     */
    public String toString() {
        return "Commune{" +
                "idCommune=" + idCommune +
                ", nomCommune='" + nomCommune + '\'' +
                ", voisins=" + voisinAsString() +
                '}';
    }

    /**
     * Implémentation de Comparable
     * Comparaison basé sur le filtre - voir classe SwitcherFilter
     * @param o Autre Commune à comparer
     */
    public int compareTo(Commune o) {
        int ret = 0;

        if (currentFilter.equals("idCommune")) {
           ret = Integer.compare(this.idCommune, o.idCommune);
        }
        if (currentFilter.equals("nomCommune")) {
            ret = this.nomCommune.compareTo(o.nomCommune);
        }
        if (currentFilter.equals("voisins")) {
            ret = Integer.compare(this.voisins.size(), o.voisins.size());
        }
        if (currentFilter.equals("population")) {
            ret = Integer.compare(this.getPopulation(), o.getPopulation());
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