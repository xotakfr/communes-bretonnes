// Importation des autres module
import localization.*;// Utilitaire de traduction
import modele.*;// Modèle de Base pour MVC
import tri.*;//Algorithme de tri utiles

public class Scenario {
    public static void main(String[] args) {
        testLocalization();
        testModele();
        testTri();
    }

    private static void testLocalization() {
        Localization.loadLocales("fr");
        System.out.println("Français : "+Localization.getL("header.welcome")+" Jean Dupont\n"+Localization.getL("header.whatsee"));
        Localization.loadLocales("en");
        System.out.println("English : "+Localization.getL("header.welcome")+" Jean Dupont\n"+Localization.getL("header.whatsee"));
    }

    private static void testModele() {
        System.out.println("à faire");
    }
    private static void testTri() {
        System.out.println("à faire");
    }
}