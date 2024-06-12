package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access for Departement
 * Use all fonction in DAO for the Departement objects
 */
public class DepartementDAO extends DAO<Departement> {
    /** Filtre actuel - Voir comparableTo et SwitecherFilter */
    private static String currentFilter = "idCommune";
    /** Liste des filtres possibles */
    private static String[] filtersList = new String[]{"idDep", "nomDep", "invesCulturel2019"};

    protected static ArrayList<Departement> runSQLQuery(Connection connection, String sql) throws Exception {
        ArrayList<Departement> results = new ArrayList<Departement>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        
        while (resultSet.next()) {
            Departement com = new Departement(resultSet.getInt(0),resultSet.getObject(1), resultSet.getObject(2));
            results.add(com);
        }
        
        resultSet.close();
        statement.close();
        return results;
    }

    @Override
    public static ArrayList<Departement> findAll() {
        Connection co = getConnection();
        ArrayList<Departement> arr = new ArrayList<Departement>();

        try {
            arr = runSQLQuery(co, "SELECT * FROM Departements;");
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

    public static Departement findByID(int Id) {
        Connection co = getConnection();
        ArrayList<Departement> arr = new ArrayList<Departement>();

        try {
            arr = runSQLQuery(co, "SELECT * FROM Departements WHERE \"Departements.idCommune\" = "+Id+";");
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

    public static ArrayList<Departement> findByFilter(String filter, String instruction) {
        Connection co = getConnection();
        ArrayList<Departement> arr = new ArrayList<Departement>();

        try {
            arr = runSQLQuery(co, "SELECT * FROM Departements WHERE \"Departements."+filter+"\""+instruction+";");
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