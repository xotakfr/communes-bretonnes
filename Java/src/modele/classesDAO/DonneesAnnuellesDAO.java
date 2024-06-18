package modele.classesDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import modele.classesModele.Annee;
import modele.classesModele.Commune;
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

        while (resultSet.next()) {
            DonneesAnnuelles com = new DonneesAnnuelles(
                                        AnneeDAO.getFromId(connection, resultSet.getInt(1)),
                                        new CommuneDAO().findByID(connection, resultSet.getInt(2)),
                                        resultSet.getInt(3), 
                                        resultSet.getInt(4), 
                                        resultSet.getFloat(5), 
                                        resultSet.getFloat(6), 
                                        resultSet.getFloat(7), 
                                        resultSet.getFloat(8), 
                                        resultSet.getInt(9)
                                    );
            results.add(com);
        }
        
        resultSet.close();
        statement.close();
        return results;
    }

    @Override
    public  ArrayList<DonneesAnnuelles> findAll(Connection c) {
        ArrayList<DonneesAnnuelles> arr = new ArrayList<DonneesAnnuelles>();

        try {
            arr = runSQLQuery(c, "SELECT * FROM DonneesAnnuelles;");
        } 
        catch (Exception e) {
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


    public  DonneesAnnuelles findByID(Connection c, long id) {
        ArrayList<DonneesAnnuelles> arr = new ArrayList<DonneesAnnuelles>();

        try {
            arr = runSQLQuery(c, "SELECT * FROM DonneesAnnuelles WHERE \"DonneesAnnuelles.idCommune\" = "+id+";");
        } 
        catch (Exception e) {
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
        } 
        catch (Exception e) {
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

    public DonneesAnnuelles findByCommuneAndYear(Connection c, int id, int annee) {
        ArrayList<DonneesAnnuelles> arr = new ArrayList<DonneesAnnuelles>();

        try {
            arr = runSQLQuery(c, "SELECT * FROM DonneesAnnuelles WHERE laCommune = " + id + " AND lAnnee = " + annee + ";");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return arr.get(0);
    }

}