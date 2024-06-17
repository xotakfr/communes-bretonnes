package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Public class python laucher
 */
public class PythonLauncher {
    /**
     * private string path
     */
    private String path = "../../../python";

    /**
     * Fonction utilitaire de lancement de script
     * @param script string
     * @param args string[]
     */
    public static void launch(String script,String[] args) throws Exception {
        // Préparation de l'éxécution
        String[] call = {"python3", path+script};

        // Calculer la taille du nouveau tableau
        int tailleNouveauTableau = call.length + args.length;

        // Créer le nouveau tableau avec une taille suffisante
        String[] callAndArgs = new String[tailleNouveauTableau];

        Process p = Runtime.getRuntime().exec(callAndArgs);
        
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
     * Fonction de test de l'écécution de script
     * @param args String[]
     */
    public static void main(String[] args) {
        launch("carte.py", new String[] {"../../../python/image.png"});
    }
}
