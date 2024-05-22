// Importation des autres module
import localization.*;// Utilitaire de traduction
import modele.*;// Modèle de Base pour MVC
import tri.*;//Algorithme de tri utiles

import java.util.ArrayList;

public class Scenario {
    public static void main(String[] args) {
        testLocalization();
        testModele();
        testTri();
    }

    private static void testLocalization() {
        Localization.loadLocales("fr");
        System.out.println("Français : "+Localization.getL("header.welcome")+" Jean Dupont\n"+Localization.getL("header.whatsee")+"\n");
        Localization.loadLocales("en");
        System.out.println("English : "+Localization.getL("header.welcome")+" Jean Dupont\n"+Localization.getL("header.whatsee")+"\n");
    }

    private static void testModele() {
        //Commune c = new Commune(545454, "Commune de test 1", new ArrayList<Commune>());

        DataLoader.loadAll();

        for (Commune co : DataLoader.getCommunes()) {
            System.out.println(co);
        }
        for (Departement de : DataLoader.getDepartements()) {
            System.out.println(de);
        }
    }
    private static void testTri() {
        System.out.println("\n\n\nTestTri");

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