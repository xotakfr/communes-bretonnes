package modele;

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
    private static String[] FILTERLIST = new String[]{"nom", "adresse", "leDepartement"};
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
        if (nom == null || nom.equals("")) {
            throw new IllegalArgumentException("Aeroport.java : paramètre nom invalide");
        }
        if (adresse == null || adresse.equals("")) {
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
     * @param codeGare Le nouveau nom de l'aéroport
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setNom(String nom) throws IllegalArgumentException {
        if (nom == null || nom.equals("")) {
            throw new IllegalArgumentException("Aeroport.java : paramètre nom invalide");
        }
        this.nom = nom;
    }
    
    /**
     * Renvoie l'addresse de l'aéroport
     * @return L'addresse de l'aéroport
     */
    public String getAdresse(){
        return this.adresse;
    }
    
    /**
     * Permet de définir la nouvelle addresse de l'aéroport
     * @param codeGare La nouvelle addresse de l'aéroport
     * @throws IllegalArgumentException - quand un paramètre invalide est utilisé
     */
    public void setAdresse(String adresse) throws IllegalArgumentException {
        if (adresse == null || adresse.equals("")) {
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
        return FILTERLIST;
    }

    /**
     * Permet de définir le nouveau filtre à utiliser
     * @param filter Le nouveau filtre à utiliser
     */
    public static void setFilter(String filter) {
        for (String s : FILTERLIST) {
            if (s.equals(filter)) {
                currentFilter = filter;
            }
        }
    }

    /**
     * Implémentation de l'interface Comparable.
     * Comparaison basée sur le filtre actuellement choisi
     * @param o Une autre instance de la classe Aeroport à comparer
     */
    public int compareTo(Aeroport o) {
        int ret = 0;
        if (currentFilter.equals("nom")) {
            ret = this.nom.compareTo(o.nom);
        }
        if (currentFilter.equals("adresse")) {
            ret = this.adresse.compareTo(o.adresse);
        }
        if (currentFilter.equals("leDepartement")) {
            ret = Integer.compare(this.leDepartement.getIdDep(), o.leDepartement.getIdDep());
        }
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
                ", leDepartement = " + this.leDepartement +
                "} ";
    }
}
