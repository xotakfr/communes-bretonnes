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

        //System.out.println();
        DataLoader.loadCommunes();
    }
    private static void testTri() {
        System.out.println("à faire");
    }
}