package modele.data;

/**
 * Une instance de cette classe permet de représenter un aéroport.
 * Cette classe implémente l'interface Comparable pour permettre la comparaison entre les aéroports
 * selon différents critères définis par un filtre
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 * @see Comparable
 */
public class Aeroport implements Comparable<Aeroport> {
    /** Filtre de base */
    private static String currentFilter = "nom";
    /** Liste des filtres autorisés */
    private static final String[] FILTER_LIST = new String[]{"nom", "adresse", "leDepartement"};
    /** Nom de l'aéroport */
    private String nom;
    /** Adresse de l'aéroport */
    private String adresse;
    /** Departement de l'aéroport */
    private Departement leDepartement;

    /**
     * Permet d'initialiser une instance de la classe Aeroport
     * @param nom Nom de l'aéroport
     * @param adresse Adresse de l'aéroport
     * @param leDepartement Departement de l'aéroport
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public Aeroport(String nom, String adresse, Departement leDepartement) throws IllegalArgumentException {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Aeroport.java : paramètre nom invalide");
        }
        if (adresse == null || adresse.isEmpty()) {
            throw new IllegalArgumentException("Aeroport.java : paramètre adresse invalide");
        }
        if (leDepartement == null) {
            throw new IllegalArgumentException("Aeroport.java : paramètre leDepartement invalide");
        }
        this.nom = nom;
        this.adresse = adresse;
        this.leDepartement = leDepartement;
    }

    /**
     * Renvoie le nom de l'aéroport
     * @return Le nom de l'aéroport
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Permet de définir le nouveau nom de l'aéroport
     * @param nom Le nouveau nom de l'aéroport
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setNom(String nom) throws IllegalArgumentException {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Aeroport.java : paramètre nom invalide");
        }
        this.nom = nom;
    }
    
    /**
     * Renvoie l'adresse de l'aéroport
     * @return L'adresse de l'aéroport
     */
    public String getAdresse(){
        return this.adresse;
    }
    
    /**
     * Permet de définir la nouvelle adresse de l'aéroport
     * @param adresse La nouvelle adresse de l'aéroport
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setAdresse(String adresse) throws IllegalArgumentException {
        if (adresse == null || adresse.isEmpty()) {
            throw new IllegalArgumentException("Aeroport.java : paramètre adresse invalide");
        }
        this.adresse = adresse;
    }

    /**
     * Renvoie le département de l'aéroport
     * @return Le département de l'aéroport
     */
    public Departement getLeDepartement() {
        return this.leDepartement;
    }

    /**
     * Permet de définir le nouveau département de l'aéroport
     * @param leDepartement Le nouveau département de l'aéroport
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setLeDepartement(Departement leDepartement) throws IllegalArgumentException {
        if (leDepartement == null) {
            throw new IllegalArgumentException("Aeroport.java : paramètre leDepartement invalide");
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
        throw new IllegalArgumentException("Aeroport.java : paramètre filter invalide");
    }

    /**
     * Implémentation de l'interface Comparable.
     * Comparaison basée sur le filtre actuellement choisi
     * @param o Une autre instance de la classe Aeroport à comparer
     * @throws NullPointerException - si o est à null
     * @return Un entier représentant le résultat de la comparaison
     */
    @Override
    public int compareTo(Aeroport o) throws NullPointerException {
        int ret = 0;
        if (o == null) {
            throw new NullPointerException("Aeroport.java : paramètre o est null");
        }
        ret = switch (currentFilter) {
            case "nom" -> this.nom.compareTo(o.nom);
            case "adresse" -> this.adresse.compareTo(o.adresse);
            case "leDepartement" -> Integer.compare(this.leDepartement.getIdDep(), o.leDepartement.getIdDep());
            default -> ret;
        };
        return ret;
    }

    /**
     * Renvoie une représentation textuelle de l'aéroport concerné
     * @return Une chaîne de caractères représentant l'aéroport concerné
     */
    public String toString() {
        return "Aeroport{" +
                "nom = " + this.nom +
                ", adresse = " + this.adresse +
                ", leDepartement = " + this.leDepartement.getIdDep() +
                "} ";
    }
}