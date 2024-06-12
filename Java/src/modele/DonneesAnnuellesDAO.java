package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access for DonneesAnnuelle
 * Use all fonction in DAO for the DonneesAnnuelle objects
 */
public class DonneesAnnuelleDAO extends DAO<DonneesAnnuelle> {
    /** Filtre actuel - Voir comparableTo et SwitecherFilter */
    private static String currentFilter = "idCommune";
    /** Liste des filtres possibles */
    private static String[] filtersList = new String[]{"idDep", "nomDep", "invesCulturel2019"};

    protected static ArrayList<DonneesAnnuelle> runSQLQuery(Connection connection, String sql) throws Exception {
        ArrayList<DonneesAnnuelle> results = new ArrayList<DonneesAnnuelle>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        
        while (resultSet.next()) {
            DonneesAnnuelle com = new DonneesAnnuelle(resultSet.getInt(0),resultSet.getObject(1), resultSet.getObject(2));
            results.add(com);
        }
        
        resultSet.close();
        statement.close();
        return results;
    }

    public static ArrayList<DonneesAnnuelle> findAll() {
        Connection co = getConnection();
        ArrayList<DonneesAnnuelle> arr = new ArrayList<DonneesAnnuelle>();

        try {
            arr = runSQLQuery(co, "SELECT * FROM DonneesAnnuelles;");
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
        for (Commune commune : arr) {
            ArrayList<Commune> voisines = new ArrayList<Commune>();
            int com = Integer.parseInt(da[0]);
            String[] near_c = commune.split("\\|");// Séparation de chaque ville depuis le CSV
            for (String nea : near_c) {
                Commune voisine = communes.get(searcher.search(communes, new Commune(Integer.parseInt(nea), "Searching", null)));
                voisines.add(voisine); // Ajout de la voisine dans la liste des voisines
            }
            current.setVoisins(voisines);
        */

        return arr;
    }

    public static DonneesAnnuelle findByID(int Id) {
        Connection co = getConnection();
        ArrayList<DonneesAnnuelle> arr = new ArrayList<DonneesAnnuelle>();

        try {
            arr = runSQLQuery(co, "SELECT * FROM DonneesAnnuelles WHERE \"DonneesAnnuelles.idCommune\" = "+Id+";");
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
        for (Commune commune : arr) {
            ArrayList<Commune> voisines = new ArrayList<Commune>();
            int com = Integer.parseInt(da[0]);
            String[] near_c = commune.split("\\|");// Séparation de chaque ville depuis le CSV
            for (String nea : near_c) {
                Commune voisine = communes.get(searcher.search(communes, new Commune(Integer.parseInt(nea), "Searching", null)));
                voisines.add(voisine); // Ajout de la voisine dans la liste des voisines
            }
            current.setVoisins(voisines);
        */

        return arr.get(0);
    }

    public static ArrayList<DonneesAnnuelle> findByFilter(String filter, String instruction) {
        Connection co = getConnection();
        ArrayList<DonneesAnnuelle> arr = new ArrayList<DonneesAnnuelle>();

        try {
            arr = runSQLQuery(co, "SELECT * FROM DonneesAnnuelles WHERE \"DonneesAnnuelles."+filter+"\""+instruction+";");
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
        for (Commune commune : arr) {
            ArrayList<Commune> voisines = new ArrayList<Commune>();
            int com = Integer.parseInt(da[0]);
            String[] near_c = commune.split("\\|");// Séparation de chaque ville depuis le CSV
            for (String nea : near_c) {
                Commune voisine = communes.get(searcher.search(communes, new Commune(Integer.parseInt(nea), "Searching", null)));
                voisines.add(voisine); // Ajout de la voisine dans la liste des voisines
            }
            current.setVoisins(voisines);
        */

        return arr;
    }


}