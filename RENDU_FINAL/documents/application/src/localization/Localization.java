package localization;

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * Classe de gestion de la localisation des textes dans différentes langues
 * 
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class Localization {
    /** Langue par défaut si la langue spécifiée n'est pas trouvée */
    private static String defaut = "fr";

    /** Dictionnaire contenant les traductions pour la langue courante */
    private static HashMap<String,String> text;

    /**
     * Charge la localisation pour la langue de l'utilisateur
     *
     * @param lang Langue de l'utilisateur sous forme de chaîne de caractères
     */
    public static void loadLocales(String lang) {
        text = new HashMap<String,String>();
        try {
            Scanner scanner = new Scanner(new File("../src/localization/loc/"+lang+".loc"));
            while(scanner.hasNextLine()) {
                decodeLine(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException e) {
            try {
                Scanner scanner = new Scanner(new File("../src/localization/loc/"+defaut+".loc"));
                while(scanner.hasNextLine()) {
                    decodeLine(scanner.nextLine());
                }
                scanner.close();
            } catch (IOException er) {
                System.err.println("Impossible de charger un langague\n"+er);
            }
        }
    }

    /**
     * Fonction utilitaire pour décoder les lignes et les stocker dans la hashmap
     *
     * @param line Ligne de texte à décoder
     */
    private static void decodeLine(String line) {
        if (!(line.equals(""))) {
            if (!(line.charAt(0)=='#')) {
                String[] l = line.split(" ", 2);

                text.put(l[0],l[1]);
            }
        }
    }

    /**
     * Récupère la traduction correspondant à l'ID fourni
     *
     * @param ID Identifiant de la chaîne de caractères à traduire
     * @return La traduction associée à l'ID ou l'ID lui-même si aucune traduction n'est trouvée
     */
    public static String getL(String ID) {
        String t = text.get(ID);
        if (t==null) {
            t=ID;
        }
        return t;
    }
}