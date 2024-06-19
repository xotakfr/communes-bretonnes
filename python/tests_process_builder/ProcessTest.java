package python.tests_process_builder;
import java.io.*;
import java.util.*;

public class ProcessTest {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "carte.py", "hey");
            pb.directory(new File("../."));
            pb.inheritIO();
            Process pr = pb.start();
            pr.waitFor();
            System.out.println("j'ai reussi");
        }
        catch(Exception e) {
            System.out.println("j'ai pas reussi chef");
        }
    }
}