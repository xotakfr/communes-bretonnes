package modele;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;

import tri.*;
/**
 * Classe de gestions des données
 * Permet le chargement des données
 */
public class DataLoader {
    /** Chemin des données */
    private static final String PATH = "../data/";
    /** fichiers data */
    private static final String AIRPORT_PATH = "aeroport.csv"; //Name; Departement number; location
    private static final String MUNICIPALITY_PATH = "communesBretonnes.csv";//Departement number; insee; name
    private static final String DEPARTEMENTS = "departement.csv"; //departement; name
    private static final String CULTURAL = "depensesCulturellesParCommune.csv"; // Year ;name;insee;popu;total_cultu;total_b
    private static final String TRAIN ="gare.csv"; //Code; name;fret;voya;commune;dep;code_co
    private static final String CULTURAL_DEP = "investissementCulturelParDep.csv"; // Year ;name;insee;popu;total_cultu;total_b
    private static final String PRICE = "prixParCommune.csv";//INSEE_COM;Annee;NbMaisonsVendues;NbAppartsVendus;PrixMoyen;Prixm2Moyen;SurfaceMoy
    private static final String INFLATION = "tauxInflationParAn.csv";//Annee; taux
    private static final String NEAR = "voisinageCommunesBretonnes.csv";//insee;nom;nbvoisins; insee voisins

    /** The data as arraylist */
    private static ArrayList<Commune> communes = new ArrayList<Commune>();
    private static ArrayList<Departement> departements = new ArrayList<Departement>();
    private static ArrayList<DonneesAnnuelles> donneesAnnuelles = new ArrayList<DonneesAnnuelles>();
    private static ArrayList<Gare> gares = new ArrayList<Gare>();

    /**
     * Decode a CSV line
     * @param line String
     * @return String[]
     */
    private static String[] decodeLine(String line) {
        String[] t = line.split(";");
        return t;
    }
    /**
     * Read a CSV file and parse it
     * @param f String file name constant
     * @return arraylist of string
     */
    private static ArrayList<String[]> CSVReader(String f) {
        ArrayList<String[]> az = new ArrayList<String[]>();
        try {
            Scanner scanner = new Scanner(new File(PATH+f));
            scanner.nextLine();//On skip la première ligne
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

    /**
     * Load the communes
     */
    public static void loadCommunes() {
        ArrayList<String[]> data = CSVReader(MUNICIPALITY_PATH);
        for (String[] d : data) {
            ArrayList<Commune> n = new ArrayList<Commune>();
            communes.add(new Commune(Integer.parseInt(d[1]), d[2], n));
        }

        Commune.setFilter("idCommune");   
        TriRapide<Commune> trieur = new TriRapide<Commune>(communes);
        trieur.trier();
        BinarySearcher<Commune> searcher = new BinarySearcher<Commune>();
        
        // Ajout des communes voisines
        ArrayList<String[]> data_n = CSVReader(NEAR);// Lecture ville voisines CSV
        for (String[] da : data_n) {
            ArrayList<Commune> voisines = new ArrayList<Commune>();
            int com = Integer.parseInt(da[0]);
            Commune current = communes.get(searcher.search(communes, new Commune(com, "Searching", null))); // Recherche de la commune actuelle
            String[] near_c = da[3].split("\\|");// Séparation de chaque ville depuis le CSV
            for (String nea : near_c) {
                Commune voisine = communes.get(searcher.search(communes, new Commune(Integer.parseInt(nea), "Searching", null)));
                voisines.add(voisine); // Ajout de la voisine dans la liste des voisines
            }
            current.setVoisins(voisines);
        }
    }

    /**
     * Load the departements
     */
    public static void loadDepartements() {
        ArrayList<String[]> data = CSVReader(DEPARTEMENTS);
        for (String[] d : data) {
            departements.add(new Departement(Integer.parseInt(d[0]), d[1], 0));
        }

        Departement.setFilter("idDep");
        TriRapide<Departement> trieur = new TriRapide<Departement>(departements);
        trieur.trier();
        ArrayList<String[]> data2 = CSVReader(CULTURAL_DEP);
        BinarySearcher<Departement> searcher = new BinarySearcher<Departement>();
        for (String[] d: data2) {
            Departement current = departements.get(searcher.search(departements, new Departement(Integer.parseInt(d[0]), "FINISTERE", 0)));
            current.setInvesCulturel2019(Float.parseFloat(d[2]));
        }
    }

    /**
     * Load the gares
     */
    public static void loadGares() {
        ArrayList<String[]> data = CSVReader(TRAIN);
        Commune.setFilter("idCommune");
        TriRapide<Commune> trieur = new TriRapide<Commune>(communes);
        trieur.trier();
        BinarySearcher<Commune> searcher = new BinarySearcher<Commune>();
        for (String[] d : data) {
            Commune sea = new Commune(Integer.parseInt(d[6]), "Searching",new ArrayList<Commune>());
            Commune currentCommune = communes.get(searcher.search(communes, sea));
            gares.add(new Gare(Integer.parseInt(d[0]), d[1], Boolean.parseBoolean(d[2]), Boolean.parseBoolean(d[3]), currentCommune));
        }
    }

    /**
     * Load the DonnéesAnnuelles
     */
    public static void loadDonneesAnnuelles() {
        ArrayList<String[]> data = CSVReader(CULTURAL);
        Commune.setFilter("idCommune");
        TriRapide<Commune> trieur = new TriRapide<Commune>(communes);
        trieur.trier();
        BinarySearcher<Commune> searcher = new BinarySearcher<Commune>();
        for (String[] d : data) {
            Commune sea = new Commune(Integer.parseInt(d[2]), "Searching",new ArrayList<Commune>());
            Commune currentCommune = communes.get(searcher.search(communes, sea));
            donneesAnnuelles.add(new DonneesAnnuelles(Integer.parseInt(d[0]), Integer.parseInt(d[2]), -1, -1, -1.0f,-1.0f,Float.parseFloat(d[4]),Float.parseFloat(d[5]),Integer.parseInt(d[3])));
        }
    }

    /** 
     * Load all the data
     */
    public static void loadAll() {
        loadCommunes();
        loadDepartements();
        loadGares();
        loadDonneesAnnuelles();
    }

    /**
     * Getter for communes
     * @return ArrayList commune
     */
    public static ArrayList<Commune> getCommunes() {
        return communes;
    }
    /**
     * Getter for departements
     * @return ArrayList departements
     */
    public static ArrayList<Departement> getDepartements() {
        return departements;
    }
    /**
     * getter for gare
     * @return ArrayList gares
     */
    public static ArrayList<Gare> getGares() {
        return gares;
    }
    /**
     * Getter donneesAnnuelles
     * @return donneesAnnuelles
     */
    public static ArrayList<DonneesAnnuelles> getDonneesAnnuelles() {
        return donneesAnnuelles;
    }
}

