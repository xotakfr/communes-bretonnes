// Importation des autres module
import localization.*;// Utilitaire de traduction
import modele.*;// Modèle de Base pour MVC
import tri.*;//Algorithme de tri utiles

public class Scenario {
    public static void main(String[] args) {
        testLocalization();
    }

    private static void testLocalization() {
        loadLocales("fr");
        System.out.println("Français : "+getL("button.save"));
        loadLocales("en");
        System.out.println("English : "+getL("button.save"));
    }
}