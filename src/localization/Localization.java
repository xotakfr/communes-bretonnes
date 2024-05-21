package localization;

import java.util.ArrayList;
import java.util.HashMap;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class localization {
    private static String defaut = "fr";
    private static HashMap text;

    public static boolean load(String lang) {
        try {
            Scanner scanner = new Scanner(new File("../../src/localization/loc/"+lang+".loc"));
            while(scanner.hasNextLine()) {
                decodeLine(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException e) {}
    }

    /**
     * Fonction utilitaire pour décoder les lignes et les stocker dans la hashmap
     * @param line String
     */
    private static void decodeLine(String line) {
        if (!line[0].equals("#")) {
            String[] l = line.split(" ", 2);

            text.put()
        }
    }
}