package modele.data;

import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import modele.dao.*;

/**
 * Classe de gestion des données
 * Permet le chargement des données depuis des fichiers CSV
 * 
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class DataLoader {
    /** Instance de type CommuneDAO */
    private static CommuneDAO communeDAO = new CommuneDAO();
    /** Instance de type DepartementDAO */
    private static DepartementDAO departementDAO = new DepartementDAO();
    /** Instance de type GareDAO */
    private static GareDAO gareDAO = new GareDAO();
    /** Instance de type DonneesAnnuellesDAO */
    private static DonneesAnnuellesDAO donneesAnnuellesDAO = new DonneesAnnuellesDAO();

    /**
     * Décode une ligne CSV
     * 
     * @param line La ligne CSV à décoder
     * @return Un tableau de chaînes de caractères
     */
    private static String[] decodeLine(String line) {
        String[] t = line.split(",");
        return t;
    }

    /**
     * Encode une ligne CSV
     *
     * @param params Les paramètres à encodés
     * @return Une ligne CSV encodée
     */
    private static String encodeLine(ArrayList<String> params) {
        String ret = "";
        for (String param : params) {
            ret += param + ",";
        }
        return ret;
    }
    
    /**
     * Lit dans un fichier CSV
     * @param f Nom du fichier
     * @param subsplit Vrai si un split sur "-" doit être effectué
     * @return Une ArrayList de tableaux de chaînes de caractères
     */
    private static ArrayList<String[]> CSVReader(String f) {
        ArrayList<String[]> az = new ArrayList<String[]>();
        try {
            Scanner scanner = new Scanner(new File(f));
            scanner.nextLine();//On skip la première ligne
            while(scanner.hasNextLine()) {
                String[] t = decodeLine(scanner.nextLine());
                az.add(t);
            }
            scanner.close();
        } 
        catch (IOException e) {
            System.err.println("An error occured while trying to load the data");
        }
        return az;
    }

    /**
     * Ecrit dans un fichier CSV 
     * @param f Nom du fichier
     * @param lesObjets La liste d'objets à écrire dans le fichier
     * @param i Un numéro qui permet d'indiquer quel type d'objets nous avons affaire à 
     */
    public static void CSVEncoder(String f, ArrayList<?> lesObjets, int i) {
        try {
            String textOutput;
            ArrayList<String> params;
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(f)));
            for (Object leObjet : lesObjets) {
                params = new ArrayList<String>();
                switch(i) {
                    // Pour Aeroport.java
                    case 1: 
                        Aeroport notreAeroport = (Aeroport) leObjet;
                        params.add(notreAeroport.getNom());
                        params.add(notreAeroport.getAdresse());
                        params.add(Integer.toString(notreAeroport.getLeDepartement().getIdDep()));
                        break;
                    // Pour Annee.java
                    case 2:
                        Annee notreAnnee = (Annee) leObjet;
                        params.add(Integer.toString(notreAnnee.getAnnee()));
                        params.add(Float.toString(notreAnnee.getTauxInflation()));
                        break;
                    // Pour Commune.java
                    case 3:
                        Commune notreCommune = (Commune) leObjet;
                        params.add(Integer.toString(notreCommune.getIdCommune()));
                        params.add(notreCommune.getNomCommune());
                        params.add(notreCommune.garesAsString());
                        params.add(notreCommune.voisinsAsString());
                        params.add(Integer.toString(notreCommune.getLeDepartement().getIdDep()));
                        break;
                    // Pour Departement.java
                    case 4:
                        Departement notreDepartement = (Departement) leObjet;
                        params.add(Integer.toString(notreDepartement.getIdDep()));
                        params.add(notreDepartement.getNomDep());
                        params.add(Float.toString(notreDepartement.getInvesCulturel2019()));
                        params.add(notreDepartement.communesAsString());
                        params.add(notreDepartement.aeroportsAsString());
                        break;
                    // Pour DonneesAnnuelles.java
                    case 5:
                        DonneesAnnuelles nosDA = (DonneesAnnuelles) leObjet;
                        params.add(Integer.toString(nosDA.getAnnee().getAnnee()));
                        params.add(Integer.toString(nosDA.getCommune().getIdCommune()));
                        params.add(Integer.toString(nosDA.getNbMaison()));
                        params.add(Integer.toString(nosDA.getNbAppart()));
                        params.add(Float.toString(nosDA.getPrixMoyen()));
                        params.add(Float.toString(nosDA.getPrixM2Moyen()));
                        params.add(Float.toString(nosDA.getSurfaceMoyenne()));
                        params.add(Float.toString(nosDA.getDepensesCulturellesTotales()));
                        params.add(Float.toString(nosDA.getBudgetTotal()));
                        params.add(Integer.toString(nosDA.getPopulation()));
                        break;
                    // Pour Gare.java
                    case 6:
                        Gare notreGare = (Gare) leObjet;
                        params.add(Integer.toString(notreGare.getCodeGare()));
                        params.add(notreGare.getNomGare());
                        params.add(Boolean.toString(notreGare.getFretValue()));
                        params.add(Boolean.toString(notreGare.getVoyageurValue()));
                        params.add(Integer.toString(notreGare.getLaCommune().getIdCommune()));
                        break;
                    default:
                        break;
                }
                textOutput = encodeLine(params);
                out.println(textOutput);
            }
            out.close();
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Charge les communes
     */
    /*
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
*/
    /**
     * Charge les départements
     */
    /*
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
*/
    /**
     * Charge les gares
     */
    /*
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
    */

    /**
     * Charge les données annuelles
     */
    /*
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
    */

    /** 
     * Charge toutes les données
     */
    /*
    public static void loadAll() {
        loadCommunes();
        loadDepartements();
        loadGares();
        loadDonneesAnnuelles();
    }
    */

    /**
     * Récupère l'index d'un élément dans un tableau de chaînes de caractères
     * 
     * @param array Le tableau de chaînes de caractères
     * @param target La chaîne de caractères cible
     * @return L'index de la chaîne de caractères cible dans le tableau, ou -1 si elle n'est pas trouvée
     */
    /*
    private static int getIndexOf(String[] array, String target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(target)) {
                return i;
            }
        }
        return -1; // Return -1 if the target string is not found in the array
    }
    */


    /**
     * Getter pour les communes avec un filtre
     * @param filter Le filtre à appliquer (nom de la colonne)
     * @param filterSelect La condition de sélection (par exemple : "<54", ">-1", "=0", "<541;>54")
     * @return Une ArrayList de communes correspondant au filtre
     */
    public static ArrayList<Commune> getCommunes(String filter, String filterSelect) {
        ArrayList<Commune> nec;
        // à faire : implémenter une sanitization des inputs de filtres
        nec = communeDAO.findByFilter(filter, filterSelect);
        /**
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
        */
        

        return nec;
    }

    /**
     * Getter pour les communes
     * 
     * @return Une ArrayList de communes
     */
    public static ArrayList<Commune> getCommunes(Connection c) {
        ArrayList<Commune> communes;
        communes = communeDAO.findAll(c);
        return communes;
    }

    /**
     * Getter pour les départements
     * 
     * @return Une ArrayList de départements
     */
    public static ArrayList<Departement> getDepartements(Connection c) {
        ArrayList<Departement> departements;
        departements = departementDAO.findAll(c);
        return departements;
    }

    /**
     * Getter pour les gares
     * 
     * @return Une ArrayList de gares
     */
    public static ArrayList<Gare> getGares(Connection c) {
        ArrayList<Gare> gares;
        gares = gareDAO.findAll(c);
        return gares;
    }
    
    /**
     * Getter pour les données annuelles
     * 
     * @return Une ArrayList de données annuelles
     */
    public static ArrayList<DonneesAnnuelles> getDonneesAnnuelles(Connection c) {
        ArrayList<DonneesAnnuelles> donneesAnnuelles;
        donneesAnnuelles = donneesAnnuellesDAO.findAll(c);
        return donneesAnnuelles;
    }
}