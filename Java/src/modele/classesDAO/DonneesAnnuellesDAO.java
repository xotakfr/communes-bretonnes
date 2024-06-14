package modele.classesDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import modele.classesModele.DonneesAnnuelles;

/**
 * Data Acces for DonneesAnnuelles
 * Use all fonction in DAO for the DonneesAnnuelles objects
 */
public class DonneesAnnuellesDAO extends DAO<DonneesAnnuelles> {
    /** Filtre actuel - Voir comparableTo et SwitecherFilter */
    private static String currentFilter = "idCommune";
    /** Liste des filtres posibles */
    private static String[] filtersList = new String[]{"idDep", "nomDep", "invesCulturel2019"};


    protected  ArrayList<DonneesAnnuelles> runSQLQuery(Connection connection, String sql) throws Exception {
        ArrayList<DonneesAnnuelles> results = new ArrayList<DonneesAnnuelles>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        /**
         * à refaire via le shéma SQL
        while (resultSet.next()) {
            DonneesAnnuelles com = new DonneesAnnuelles(resultSet.getInt(0),resultSet.getObject(1), resultSet.getObject(2));
            results.add(com);
        }
        */
        
        resultSet.close();
        statement.close();
        return results;
    }

    @Override
    public  ArrayList<DonneesAnnuelles> findAll() {
        Connection co = getConnection();
        ArrayList<DonneesAnnuelles> arr = new ArrayList<DonneesAnnuelles>();

        try {
            arr = runSQLQuery(co, "SELECT * FROM DonneesAnnuelles;");
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


    public  DonneesAnnuelles findByID(long id) {
        Connection co = getConnection();
        ArrayList<DonneesAnnuelles> arr = new ArrayList<DonneesAnnuelles>();

        try {
            arr = runSQLQuery(co, "SELECT * FROM DonneesAnnuelles WHERE \"DonneesAnnuelles.idCommune\" = "+id+";");
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


    public  ArrayList<DonneesAnnuelles> findByFilter(String filter, String filterSelect) {
        Connection co = getConnection();
        ArrayList<DonneesAnnuelles> arr = new ArrayList<DonneesAnnuelles>();

        try {
            arr = runSQLQuery(co, "SELECT * FROM DonneesAnnuelles WHERE \"DonneesAnnuelles."+filter+"\""+filterSelect+";");
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