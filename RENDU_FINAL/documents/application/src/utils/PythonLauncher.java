package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Cette classe java nous permet de lancer l'exécution de nos programmes python.
 * Ceux-ci sont nottament utilisés lors de la création de nos graphes
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class PythonLauncher {
    /** Chemin vers le répertoire contenant nos scripts python */
    private static String path = "../src/utils/";

    /**
     * Permet de lancer un script python depuis la classe
     * @param script Le script qu'il veut lancer
     * @param args Arguments additionnels
     */
    public static void launch(String script, String[] args) throws Exception {
        // Préparation à l'exécution du script
        ArrayList<String> commande = new ArrayList<>();
        commande.add("python3");
        commande.add(path + script);

        // Ajout des arguments supplémentaires
        for (String arg : args) {
            commande.add(arg);
        }

        // Utilisation de ProcessBuilder au lieu de Runtime
        ProcessBuilder pb = new ProcessBuilder(commande);
        Process p = pb.start();

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

        // Lecture pour le debug
        String s;
        while ((s = stdInput.readLine())!= null) {
            System.out.println(s);
        }

        // Lecture des erreurs pour le debug
        while ((s = stdError.readLine())!= null) {
            System.out.println(s);
        }
    }

    /**
     * Test de l'exécution d'un script python afin de vérifier le bon fonctionnement du programme
     * @param args Arguments additionnels 
     */
    public static void main(String[] args) {
        try {
            launch("carte.py", new String[]{"../../python/image-test.png"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
