package modele.classesDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import modele.classesModele.Gare;;

/**
 * Data Access for Gare
 * Use all fonction in DAO for the Gare objects
 */
public class GareDAO extends DAO<Gare> {
    /** Filtre actuel - Voir comparableTo et SwitecherFilter */
    private static String currentFilter = "idCommune";
    /** Liste des filtres possibles */
    private static String[] filtersList = new String[]{"codeGare", "nomGare", "estFret", "estVoyageur", "laCommune"};


    protected  ArrayList<Gare> runSQLQuery(Connection connection, String sql) throws Exception {
        ArrayList<Gare> results = new ArrayList<Gare>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        
        while (resultSet.next()) {
            Gare com = new Gare(resultSet.getInt(0),resultSet.getString(1), resultSet.getBoolean(2), resultSet.getBoolean(3), new CommuneDAO().findByID(resultSet.getInt(4)));
            results.add(com);
        }
        
        resultSet.close();
        statement.close();
        return results;
    }

    @Override
    public  ArrayList<Gare> findAll() {
        Connection co = getConnection();
        ArrayList<Gare> arr = new ArrayList<Gare>();

        try {
            arr = runSQLQuery(co, "SELECT * FROM Gares;");
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


    public  Gare findByID(long id) {
        Connection co = getConnection();
        ArrayList<Gare> arr = new ArrayList<Gare>();

        try {
            arr = runSQLQuery(co, "SELECT * FROM Gares WHERE \"Gares.idCommune\" = "+id+";");
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


    public  ArrayList<Gare> findByFilter(String filter, String filterSelect) {
        Connection co = getConnection();
        ArrayList<Gare> arr = new ArrayList<Gare>();

        try {
            arr = runSQLQuery(co, "SELECT * FROM Gares WHERE \"Gares."+filter+"\""+filterSelect+";");
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