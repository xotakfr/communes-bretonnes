package python.legit.myJava;
import java.io.File;
import java.io.IOException;

public class POC {
    public static void proceed_visualizations(String[] args) {
        ProcessBuilder pb = null;
        if (args.length == 1) {
            pb = new ProcessBuilder("python", "visualizations.py", args[0]);
        }
        else if (args.length == 2) {
            pb = new ProcessBuilder("python", "visualizations.py", args[0], args[1]);
        }
        else if (args.length == 3) {
            pb = new ProcessBuilder("python", "visualizations.py", args[0], args[1], args[2]);
        }
        if (pb != null) {
            try {
                pb.directory(new File("../myPython/.")); // TODO : dépend de là où on lance l'application
                pb.inheritIO();
                Process pr = pb.start();
                pr.waitFor();
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
            catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}