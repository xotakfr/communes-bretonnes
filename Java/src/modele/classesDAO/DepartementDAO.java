package modele.classesDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import modele.classesModele.Departement;

/**
 * Data Access for Departement
 * Use all fonction in DAO for the Departement objects
 */
public class DepartementDAO extends DAO<Departement> {
    /** Filtre actuel - Voir comparableTo et SwitecherFilter */
    private static String currentFilter = "idCommune";
    /** Liste des filtres possibles */
    private static String[] filtersList = new String[]{"idDep", "nomDep", "invesCulturel2019"};


    protected ArrayList<Departement> runSQLQuery(Connection connection, String sql) throws Exception {
        ArrayList<Departement> results = new ArrayList<Departement>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        
        while (resultSet.next()) {
            Departement com = new Departement(resultSet.getInt(1),resultSet.getString(2), resultSet.getFloat(3));
            results.add(com);
        }
        
        resultSet.close();
        statement.close();
        return results;
    }

    @Override
    public ArrayList<Departement> findAll(Connection c) {
        ArrayList<Departement> arr = new ArrayList<>();

        try {
            arr = runSQLQuery(c, "SELECT * FROM Departements;");
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


    public Departement findByID(Connection c, long id) {

        ArrayList<Departement> arr = new ArrayList<Departement>();

        try {
            arr = runSQLQuery(c, "SELECT * FROM Departement WHERE idDep = "+id+";");
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
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


    public  ArrayList<Departement> findByFilter(String filter, String filterSelect) {
        Connection co = getConnection();
        ArrayList<Departement> arr = new ArrayList<Departement>();

        try {
            arr = runSQLQuery(co, "SELECT * FROM Departements WHERE \"Departements."+filter+"\""+filterSelect+";");
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
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