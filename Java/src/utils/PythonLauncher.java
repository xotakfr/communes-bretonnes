package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.utils.ArrayList;

/**
 * Public class python laucher
 */
public class PythonLauncher {
    /**
     * private string path
     */
    private static String path = "../../../python";

    /**
     * Fonction utilitaire de lancement de script
     * @param script string
     * @param args string[]
     */
    public static void launch(String script,String[] args) throws Exception {
        // Préparation de l'éxécution
        String[] call = {"python3", path+script};

        // Fusion des deux tableaux en une seule ArrayList
        ArrayList<String> listeFusionnee = new ArrayList<String>();
        for (String element : call) {
            listeFusionnee.add(element);
        }
        for (String element : args) {
            listeFusionnee.add(element);
        }

        // Conversion de l'ArrayList en tableau
        String[] callAndArgs = listeFusionnee.toArray(new String[0]);

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
        try {
            launch("carte.py", new String[] {"../../../python/image.png"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
