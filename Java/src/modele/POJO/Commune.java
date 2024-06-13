package modele.POJO;
import java.util.ArrayList;

/**
 * Une instance de cette classe permet de représenter une commune.
 * Cette classe implémente l'interface Comparable pour permettre la comparaison des communes
 * selon différents critères définis par un filtre
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 * @see Comparable
 */
public class Commune implements Comparable<Commune> {
    /** Filtre de base */
    private static String currentFilter = "idCommune";
    /** Liste des filtres autorisés */
    private static final String[] FILTER_LIST = new String[]{"idCommune", "nomCommune", "leDepartement"};
    /** Identifiant de la commune */
    private int idCommune;
    /** Nom de la commune */
    private String nomCommune;
    /** Liste des gares de la commune */
    private ArrayList<Gare> gares = new ArrayList<>();
    /** Liste des communes voisines */
    private ArrayList<Commune> voisins = new ArrayList<>();
    /** Instance de la classe Departement associée aux données */
    private Departement leDepartement;

    /**
     * Permet d'initialiser une instance de la classe Commune.
     * La liste des gares ainsi que la liste des voisins d'une commune sont initialisées à vide
     * @param idCommune L'identifiant de la commune
     * @param nomCommune Le nom de la commune
     * @param leDepartement L'instance de la classe Departement associée aux données
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public Commune(int idCommune, String nomCommune, Departement leDepartement) throws IllegalArgumentException {
        if (idCommune <= 0) {
            throw new IllegalArgumentException("Commune.java : paramètre idCommune invalide");
        }
        if (nomCommune == null || nomCommune.isEmpty()) {
            throw new IllegalArgumentException("Commune.java : paramètre nomCommune invalide");
        }
        if (leDepartement == null) {
            throw new IllegalArgumentException("Commune.java : paramètre leDepartement invalide");
        }
        this.idCommune = idCommune;
        this.nomCommune = nomCommune;
        this.leDepartement = leDepartement;
    }

    /**
     * Renvoie l'identifiant de la commune
     * @return L'identifiant de la commune
     */
    public int getIdCommune() {
        return this.idCommune;
    }

    /**
     * Permet de définir le nouvel identifiant de la commune
     * @param idCommune Le nouvel identifiant de la commune
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setIdCommune(int idCommune) throws IllegalArgumentException {
        if (idCommune <= 0) {
            throw new IllegalArgumentException("Commune.java : paramètre idCommune invalide");
        }
        this.idCommune = idCommune;
    }

    /**
     * Renvoie le nom de la commune
     * @return Le nom de la commune
     */
    public String getNomCommune() {
        return this.nomCommune;
    }

    /**
     * Permet de définir le nouveau nom de la commune
     * @param nomCommune Le nouveau nom de la commune
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setNomCommune(String nomCommune) throws IllegalArgumentException {
        if (nomCommune == null || nomCommune.isEmpty()) {
            throw new IllegalArgumentException("Commune.java : paramètre nomCommune invalide");
        }
        this.nomCommune = nomCommune;
    }

    /**
     * Renvoie la liste des gares de la commune
     * @return La liste des gares de la commune
     */
    public ArrayList<Gare> getGares() {
        return this.gares;
    }

    /**
     * Permet d'ajouter une gare à la liste des gares de la commune concernée par l'appel de la méthode
     * @param gare La gare qu'on veut ajouter à la liste des gares de la commune concernée par l'appel de la méthode
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void addGare(Gare gare) throws IllegalArgumentException {
        if (gare == null) {
            throw new IllegalArgumentException("Commune.java : paramètre gare invalide");
        }
        this.gares.add(gare);
    }

    /**
     * Permet de retirer une gare de la liste des gares de la commune concernée par l'appel de la méthode
     * @param gare La gare qu'on veut retirer de la liste des gares de la commune concernée par l'appel de la méthode
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void removeGare(Gare gare) throws IllegalArgumentException {
        if (gare == null) {
            throw new IllegalArgumentException("Commune.java : paramètre gare invalide");
        }
        boolean ret = this.gares.remove(gare);
        if (!ret) {
            System.out.println("La gare passée en paramètre n'est pas une des gares de la commune de " + this.nomCommune);
        }
    }

    /**
     * Renvoie la liste des communes voisines
     * @return La liste des communes voisines
     */
    public ArrayList<Commune> getVoisins() {
        return this.voisins;
    }

    /**
     * Permet d'ajouter une commune à la liste des communes voisines de la commune concernée par l'appel de la méthode
     * @param voisin La commune qu'on veut ajouter à la liste des communes voisines de la commune concernée par l'appel de la méthode
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void addVoisin(Commune voisin) throws IllegalArgumentException {
        if (voisin == null) {
            throw new IllegalArgumentException("Commune.java : paramètre voisin invalide");
        }
        this.voisins.add(voisin);
    }

    /**
     * Permet de retirer une commune de la liste des communes voisines de la commune concernée par l'appel de la méthode
     * @param voisin La commune qu'on veut retirer de la liste des communes voisines de la commune concernée par l'appel de la méthode
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void removeVoisin(Commune voisin) throws IllegalArgumentException {
        if (voisin == null) {
            throw new IllegalArgumentException("Commune.java : paramètre voisin invalide");
        }
        boolean ret = this.voisins.remove(voisin);
        if (!ret) {
            System.out.println("La commune passée en paramètre n'est pas une commune voisine de la commune de " + this.nomCommune);
        }
    }

    /**
     * Renvoie l'instance de la classe Departement associée aux données
     * @return L'instance de la classe Departement associée aux données
     */
    public Departement getLeDepartement() {
        return this.leDepartement;
    }

    /**
     * Permet de définir la nouvelle instance de la classe Departement associée aux données
     * @param leDepartement La nouvelle instance de la classe Departement associée aux données
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setLeDepartement(Departement leDepartement) throws IllegalArgumentException {
        if (leDepartement == null) {
            throw new IllegalArgumentException("Commune.java : paramètre leDepartement invalide");
        }
        this.leDepartement = leDepartement;
    }

    /**
     * Renvoie la liste de tous les filtres autorisés
     * @return La liste de tous les filtres autorisés
     */
    public static String[] getAllFilter() {
        return FILTER_LIST.clone();
    }

    /**
     * Permet de définir le filtre à utiliser
     * @param filter Le nouveau filtre à utiliser
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public static void setFilter(String filter) {
        for (String s : FILTER_LIST) {
            if (s.equals(filter)) {
                currentFilter = filter;
                return; // empêche d'atteindre le lancement d'IAE si on trouve le filtre
            }
        }
        throw new IllegalArgumentException("Commune.java : paramètre filter invalide");
    }

    /**
     * Implémentation de l'interface Comparable.
     * Comparaison basée sur le filtre actuellement choisi
     * @param o Une autre instance de la classe Commune à comparer
     * @throws NullPointerException - si o est à null
     * @return Un entier représentant le résultat de la comparaison
     */
    public int compareTo(Commune o) throws NullPointerException {
        int ret = 0;
        if (o == null) {
            throw new NullPointerException("Commune.java : paramètre o invalide");
        }
        ret = switch (currentFilter) {
            case "idCommune" -> Integer.compare(this.idCommune, o.idCommune);
            case "nomCommune" -> this.nomCommune.compareTo(o.nomCommune);
            case "leDepartement" -> Integer.compare(this.leDepartement.getIdDep(), o.leDepartement.getIdDep());
            default -> ret;
        };
        return ret;
    }

    /**
     * Renvoie une représentation textuelle des gares de la commune concernée.
     * Ici, la variable i est utilisée pour modifier l'ajout à la chaîne de caractères lorsqu'on
     * atteint la fin de la liste
     * @return Une chaîne de caractères représentant les gares de la commune concernée
     */
    public String garesAsString() {
        String s = "";
        int i = 0;
        if (this.gares.size() == 0) {
            s += "Aucune gare";
        } else {
            for (Gare gare : this.gares) {
                if (i != this.gares.size() - 1) {
                    s += gare.getCodeGare() + " (" + gare.getNomGare() + ") -";
                } else {
                    s += gare.getCodeGare() + " (" + gare.getNomGare() + ")";
                }
                i++;
            }
        }
        return s;
    }

    /**
     * Renvoie une représentation textuelle des voisins de la commune concernée.
     * Ici, la variable i est utilisée pour modifier l'ajout à la chaîne de caractères lorsqu'on
     * atteint la fin de la liste
     * @return Une chaîne de caractères représentant les voisins de la commune concernée
     */
    public String voisinAsString() {
        String s = "";
        int i = 0;
        if (this.voisins.size() == 0) {
            s += "Aucun voisin";
        } else {
            for (Commune voisin : this.voisins) {
                if (i != this.voisins.size() - 1) {
                    s += voisin.idCommune + " (" + voisin.nomCommune + ") -";
                } else {
                    s += voisin.idCommune + " (" + voisin.nomCommune + ")";
                }
                i++;
            }
        }
        return s;
    }

    /**
     * Renvoie une représentation textuelle de la commune concernée
     * @return Une chaîne de caractères représentant la commune concernée
     */
    public String toString() {
        return "Commune{" +
                "idCommune = " + this.idCommune +
                ", nomCommune = " + this.nomCommune +
                ", gares = " + this.garesAsString() +
                ", voisins = " + this.voisinAsString() +
                ", leDepartement = " + this.leDepartement.getIdDep() +
                "} ";
    }
}