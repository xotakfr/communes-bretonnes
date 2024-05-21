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
            Scanner scanner = new Scanner(new File("../../src/localization/"+lang+".loc"));
            while(scanner.hasNextLine()) {
                decodeLine(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException e) {}
    }

    private static void decodeLine()
}