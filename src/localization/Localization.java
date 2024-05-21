package localization;

import java.util.ArrayList;
import java.util.HashMap;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Localization {
    private static String defaut = "fr";
    private static HashMap text;

    /**
     * Load the user language
     * @param lang String user language
     */
    public static boolean loadLocales(String lang) {
        try {
            Scanner scanner = new Scanner(new File("../../src/localization/loc/"+lang+".loc"));
            while(scanner.hasNextLine()) {
                decodeLine(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException e) {
            try {
                lang = defaut;
                Scanner scanner = new Scanner(new File("../../src/localization/loc/"+lang+".loc"));
                while(scanner.hasNextLine()) {
                    decodeLine(scanner.nextLine());
                }
            } catch (IOException er) {
                System.err.println("Impossible de charger un langague\n"+er);
            }
        }
    }

    /**
     * Fonction utilitaire pour décoder les lignes et les stocker dans la hashmap
     * @param line String
     */
    private static void decodeLine(String line) {
        if (!line.charAt(0)=='#')) {
            String[] l = line.split(" ", 2);

            text.put(l[0],l[1]);
        }
    }

    /**
     * Getter for the translation according to the provided ID
     * @param ID String
     */
    public static String getL(String ID) {
        String t = ""+text.get(ID);
        if (t==null) {
            t=ID;
        }
        return t;
    }
}