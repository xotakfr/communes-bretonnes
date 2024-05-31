// Importation des autres module
import localization.*;// Utilitaire de traduction
import modele.*;// Modèle de Base pour MVC
import tri.*;//Algorithme de tri utiles

import java.util.ArrayList;

/**
 * Classe Scenario qui simule une utilisation normale des autres classes
 * 
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class Scenario {
    /**
     * Méthode principale pour exécuter les tests de localisation, modèle et tri
     *
     * @param args Arguments de la ligne de commande
     */
    public static void main(String[] args) {
        testLocalization();
        testModele();
        testTri();
    }

    /**
     * Méthode de test pour les localisations
     */
    private static void testLocalization() {
        Localization.loadLocales("fr");
        System.out.println("Français : "+Localization.getL("header.welcome")+" Jean Dupont\n"+Localization.getL("header.whatsee")+"\n");
        Localization.loadLocales("en");
        System.out.println("English : "+Localization.getL("header.welcome")+" John Doe\n"+Localization.getL("header.whatsee")+"\n");
    }

    /**
     * Méthode de test pour le modèle
     */
    private static void testModele() {
        DataLoader.loadAll();

        for (Commune co : DataLoader.getCommunes()) {
            System.out.println(co);
        }
        for (Departement de : DataLoader.getDepartements()) {
            System.out.println(de);
        }

        ArrayList<Commune> d = DataLoader.getCommunes("idCommune", ">29000;<30000");
        for (Commune co : d) {
            System.out.println(co);
        }
        
        System.out.println("\n\n\nTests Filtres");
        System.out.println("Filtre selon le nom (Exemple, l'utilisateur va chercher toute les communes qui commencent par \"QU\")");
        ArrayList<Commune> d1 = DataLoader.getCommunes("nomCommune", ">QT;<QV");// Recherche de toute les communes dont le nom commence par QU
        for (Commune co : d1) {
            System.out.println(co);
        }

        System.out.println("Filtre selon la population (Exemple, l'utilisateur va chercher toute les communes qui ont entre 50000 et 100000 de population)");
        ArrayList<Commune> d2 = DataLoader.getCommunes("population", ">50000;<100000");// entre 5000 et 100000
        for (Commune co : d2) {
            System.out.println(co);
        }
    }

    /**
     * Méthode statique de test pour les différents tris
     */
    private static void testTri() {
        System.out.println("Tri Departement selon nom");
        Departement.setFilter("nomDep");
        TriRapide<Departement> trieur = new TriRapide<Departement>(DataLoader.getDepartements());
        trieur.trier();
        for (Departement de : DataLoader.getDepartements()) {
            System.out.println(de);
        }

        System.out.println("Tri Departement selon code INSEE");
        Departement.setFilter("idDep");
        trieur.trier();
        for (Departement de : DataLoader.getDepartements()) {
            System.out.println(de);
        }

        System.out.println("Tri Departement selon invesCulturel2019");
        Departement.setFilter("invesCulturel2019");
        trieur.trier();
        for (Departement de : DataLoader.getDepartements()) {
            System.out.println(de);
        }

    }
}