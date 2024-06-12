package modele;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import tri.*;

/**
 * Classe de gestion des données
 * Permet le chargement des données depuis des fichiers CSV
 * 
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class DataLoader {
    /** Chemin des données */
    private static final String PATH = "../data/";

    /** Chemin du fichier des aéroports */
    private static final String AIRPORT_PATH = "aeroport.csv"; //Name; Departement number; location

    /** Chemin du fichier des communes */
    private static final String MUNICIPALITY_PATH = "communesBretonnes.csv";//Departement number; insee; name

    /** Chemin du fichier des départements */
    private static final String DEPARTEMENTS = "departement.csv"; //departement; name
    
    /** Chemin du fichier des dépenses culturelles par commune */
    private static final String CULTURAL = "depensesCulturellesParCommune.csv"; // Year ;name;insee;popu;total_cultu;total_b
    
    /** Chemin du fichier des gares */
    private static final String TRAIN ="gare.csv"; //Code; name;fret;voya;commune;dep;code_co
    
    /** Chemin du fichier des investissements culturels par département */
    private static final String CULTURAL_DEP = "investissementCulturelParDep.csv"; // Year ;name;insee;popu;total_cultu;total_b
    
    /** Chemin du fichier des prix par commune */
    private static final String PRICE = "prixParCommune.csv";//INSEE_COM;Annee;NbMaisonsVendues;NbAppartsVendus;PrixMoyen;Prixm2Moyen;SurfaceMoy
    
    /** Chemin du fichier des taux d'inflation par an */
    private static final String INFLATION = "tauxInflationParAn.csv";//Annee; taux
    
    /** Chemin du fichier des voisinages des communes bretonnes */
    private static final String NEAR = "voisinageCommunesBretonnes.csv";//insee;nom;nbvoisins; insee voisins

    /** Liste des communes chargées */
    private static ArrayList<Commune> communes = new ArrayList<Commune>();

    /** Liste des départements chargés */
    private static ArrayList<Departement> departements = new ArrayList<Departement>();
    
    /** Liste des données annuelles chargées */
    private static ArrayList<DonneesAnnuelles> donneesAnnuelles = new ArrayList<DonneesAnnuelles>();
    
    /** Liste des gares chargées */
    private static ArrayList<Gare> gares = new ArrayList<Gare>();

    /**
     * Décode une ligne CSV
     * 
     * @param line La ligne CSV à décoder
     * @return Un tableau de chaînes de caractères
     */
    private static String[] decodeLine(String line) {
        String[] t = line.split(";");
        return t;
    }
    
    /**
     * Lit un fichier CSV et le parse
     * 
     * @param f Nom du fichier constant
     * @return Une ArrayList de tableaux de chaînes de caractères
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
     * Charge les communes
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
     * Charge les départements
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
     * Charge les gares
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
     * Charge les données annuelles
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
            float va;
            if (d.length>5) {
                va = Float.parseFloat(d[5]);
            } else {
                va = -1.0f;
            }
            donneesAnnuelles.add(new DonneesAnnuelles(Integer.parseInt(d[0]), Integer.parseInt(d[2]), -1, -1, -1.0f,-1.0f,Float.parseFloat(d[4]),va,Integer.parseInt(d[3])));
        }
    }

    /** 
     * Charge toutes les données
     */
    public static void loadAll() {
        loadCommunes();
        loadDepartements();
        loadGares();
        loadDonneesAnnuelles();
    }

    /**
     * Récupère l'index d'un élément dans un tableau de chaînes de caractères
     * 
     * @param array Le tableau de chaînes de caractères
     * @param target La chaîne de caractères cible
     * @return L'index de la chaîne de caractères cible dans le tableau, ou -1 si elle n'est pas trouvée
     */
    private static int getIndexOf(String[] array, String target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(target)) {
                return i;
            }
        }
        return -1; // Return -1 if the target string is not found in the array
    }


    /**
     * Getter pour les communes avec un filtre
     * @param filter Le filtre à appliquer (nom de la colonne)
     * @param filterSelect La condition de sélection (par exemple : "<54", ">-1", "=0", "<541;>54")
     * @return Une ArrayList de communes correspondant au filtre
     */
    public static ArrayList<Commune> getCommunes(String filter, String filterSelect) {
        ArrayList<Commune> commune_temp = new ArrayList<Commune>();
        ArrayList<Commune> nec = new ArrayList<Commune>();
        for (Commune c : communes) {
            commune_temp.add(c);
        }

        Commune.setFilter(filter);

        // décodage
        String[] a = filterSelect.split(";");
        for (String b : a) {
            if (b.charAt(0)=='>') {
                String str = b.substring(1);// remove the charAt(0)
                nec = new ArrayList<Commune>();

                ArrayList<String> obj = new ArrayList<String>();
                obj.add("0");
                obj.add("Search");

                obj.add("");
                obj.add("0");

                obj.set(getIndexOf(Commune.getAllFilter(), filter), str);

                Commune search_co = new Commune(Integer.parseInt(obj.get(0)), obj.get(1), new ArrayList<Commune>());
                search_co.setPopulation(Integer.parseInt(obj.get(3)));
                for (Commune c : commune_temp) {
                    if (c.compareTo(search_co)>0) {
                        nec.add(c);
                    }
                }
            }
            if (b.charAt(0)=='<') {
                String str = b.substring(1);// remove the charAt(0)
                nec = new ArrayList<Commune>();

                ArrayList<String> obj = new ArrayList<String>();
                obj.add("0");
                obj.add("Search");

                obj.add("");
                obj.add("0");

                obj.set(getIndexOf(Commune.getAllFilter(), filter), str);

                Commune search_co = new Commune(Integer.parseInt(obj.get(0)), obj.get(1), new ArrayList<Commune>());
                search_co.setPopulation(Integer.parseInt(obj.get(3)));
                for (Commune c : commune_temp) {
                    if (c.compareTo(search_co)<0) {
                        nec.add(c);
                    }
                }
            }
            commune_temp = new ArrayList<Commune>();;
            for (Commune c : nec) {
                commune_temp.add(c);
            }
        }
        

        return nec;
    }

    /**
     * Getter pour les communes
     * 
     * @return Une ArrayList de communes
     */
    public static ArrayList<Commune> getCommunes() {
        return communes;
    }

    /**
     * Getter pour les départements
     * 
     * @return Une ArrayList de départements
     */
    public static ArrayList<Departement> getDepartements() {
        return departements;
    }

    /**
     * Getter pour les gares
     * 
     * @return Une ArrayList de gares
     */
    public static ArrayList<Gare> getGares() {
        return gares;
    }
    
    /**
     * Getter pour les données annuelles
     * 
     * @return Une ArrayList de données annuelles
     */
    public static ArrayList<DonneesAnnuelles> getDonneesAnnuelles() {
        return donneesAnnuelles;
    }
}
