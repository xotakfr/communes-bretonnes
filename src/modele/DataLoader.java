package modele;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;

import tri.*;

public class DataLoader {
    private static final String PATH = "../data/";
    private static final String AIRPORT_PATH = "aeroport.csv"; //Name; Departement number; location
    private static final String MUNICIPALITY_PATH = "communesBretonnes.csv";//Departement number; insee; name
    private static final String DEPARTEMENTS = "departement.csv"; //departement; name
    private static final String CULTURAL = "depensesCulturellesParCommune.csv"; // Year ;name;insee;popu;total_cultu;total_b
    private static final String TRAIN ="gare.csv"; //Code; name;fret;voya;commune;dep;code_co
    private static final String CULTURAL_DEP = "investissementCulturelParDep.csv"; // Year ;name;insee;popu;total_cultu;total_b


    private static final String NEAR = "voisinageCommunesBretonnes.csv";//insee;nom;nbvoisins; insee voisins

    private static ArrayList<Commune> communes = new ArrayList<Commune>();
    private static ArrayList<Departement> departements = new ArrayList<Departement>();
    private static ArrayList<DonneesAnnuelles> donneesAnnuelles = new ArrayList<DonneesAnnuelles>();
    private static ArrayList<Gare> gares = new ArrayList<Gare>();


    private static String[] decodeLine(String line) {
        String[] t = line.split(";");
        return t;
    }
    private static ArrayList<String[]> CSVReader(String f) {
        ArrayList<String[]> az = new ArrayList<String[]>();
        try {
            Scanner scanner = new Scanner(new File(PATH+f));
            while(scanner.hasNextLine()) {
                String[] t = decodeLine(scanner.nextLine());
                az.add(t);
            }
            scanner.close();
        } catch (IOException e) {
            System.err.println("An error occured while trying to load the data");
        }
        return az;
    }

    public static void loadCommunes() {
        ArrayList<String[]> data = CSVReader(MUNICIPALITY_PATH);
        for (String[] d : data) {
            ArrayList<Commune> n = new ArrayList<Commune>();
            communes.add(new Commune(d[1], d[2], n));
        }
        ArrayList<String[]> data_n = CSVReader(NEAR);
    }
}

