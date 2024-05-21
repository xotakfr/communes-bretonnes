package modele;

import java.util.Arrays;
import java.util.List;

/**
 * Classe représentant un département.
 */
public class Departement {
    /** Identifiant du département */
    private int idDep;

    /** Nom du département */
    private String nomDep;

    /** Investissement culturel en 2019 */
    private float invesCulturel2019;

    /** Liste des départements autorisés */
    private static final List<String> DEPARTEMENTS_AUTORISES = Arrays.asList("MORBIHAN", "ILLE-ET-VILAINE", "COTES-D-ARMOR", "FINISTERE");

    /**
     * Constructeur de la classe Departement.
     * @param idDep L'identifiant du département.
     * @param nomDep Le nom du département.
     * @param invesCulturel2019 L'investissement culturel en 2019.
     */
    public Departement(int idDep, String nomDep, float invesCulturel2019) {
        setNomDep(nomDep);
        this.idDep = idDep;
        this.invesCulturel2019 = invesCulturel2019;
    }

    /**
     * Obtient l'identifiant du département.
     * @return L'identifiant du département.
     */
    public int getIdDep() {
        return this.idDep;
    }

    /**
     * Obtient le nom du département.
     * @return Le nom du département.
     */
    public String getNomDep() {
        return this.nomDep;
    }

    /**
     * Obtient l'investissement culturel en 2019.
     * @return L'investissement culturel en 2019.
     */
    public float getInvesCulturel2019() {
        return this.invesCulturel2019;
    }

    /**
     * Définit l'identifiant du département.
     * @param idDep Le nouvel identifiant du département.
     */
    public void setIdDep(int idDep) {
        this.idDep = idDep;
    }

    /**
     * Définit le nom du département.
     * @param nomDep Le nouveau nom du département.
     */
    public void setNomDep(String nomDep) {
        if (!estCorrect(nomDep)) {
            throw new IllegalArgumentException("Le département spécifié n'est pas valide.");
        }
        this.nomDep = nomDep.toUpperCase();
    }

    /**
     * Définit l'investissement culturel en 2019.
     * @param invesCulturel2019 Le nouvel investissement culturel en 2019.
     */
    public void setInvesCulturel2019(float invesCulturel2019) {
        this.invesCulturel2019 = invesCulturel2019;
    }

    /**
     * Méthode pour obtenir une représentation textuelle du département.
     * @return Une chaîne de caractères représentant le département.
     */
    public String toString() {
        return "Departement{" +
                "idDep=" + idDep +
                ", nomDep='" + nomDep + '\'' +
                ", invesCulturel2019=" + invesCulturel2019 +
                '}';
    }

    /**
     * Vérifie si le nom du département est correct.
     * @param nomDep Le nom du département à vérifier.
     * @return true si le département est autorisé, sinon false.
     */
    public static boolean estCorrect(String nomDep) {
        return DEPARTEMENTS_AUTORISES.contains(nomDep.toUpperCase());
    }
}
