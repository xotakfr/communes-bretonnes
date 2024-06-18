package modele.classesDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import modele.classesModele.Annee;
import modele.classesModele.Commune;
import modele.classesModele.DonneesAnnuelles;

/**
 * Data Acces for DonneesAnnuelles
 * Use all fonction in DAO for the DonneesAnnuelles objects
 */
public class DonneesAnnuellesDAO extends DAO<DonneesAnnuelles> {
    /** Filtre actuel - Voir comparableTo et SwitecherFilter */
    private static String currentFilter = "laCommune";
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
                                        resultSet.getFloat(9),
                                        resultSet.getInt(10)
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
            arr = runSQLQuery(c, "SELECT * FROM DonneesAnnuelles WHERE \"DonneesAnnuelles.laCommune\" = "+id+";");
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

    public void update(long id, DonneesAnnuelles donneesAnnuelles) {
        Connection co = getConnection();
        String sql = "UPDATE DonneesAnnuelles SET lAnnee = ?, laCommune = ?, nbMaison = ?, nbAppart = ?, prixMoyen = ?, prixM2Moyen = ?, SurfaceMoy = ?, depensesCulturellesTotales = ?, budgetTotal = ?, population = ? WHERE id = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setInt(1, donneesAnnuelles.getAnnee().getAnnee());
            pstmt.setInt(2, donneesAnnuelles.getCommune().getIdCommune());
            pstmt.setInt(3, donneesAnnuelles.getNbMaison());
            pstmt.setInt(4, donneesAnnuelles.getNbAppart());
            pstmt.setFloat(5, donneesAnnuelles.getPrixMoyen());
            pstmt.setFloat(6, donneesAnnuelles.getPrixM2Moyen());
            pstmt.setFloat(7, donneesAnnuelles.getSurfaceMoyenne());
            pstmt.setFloat(8, donneesAnnuelles.getDepensesCulturellesTotales());
            pstmt.setFloat(9, donneesAnnuelles.getBudgetTotal());
            pstmt.setInt(10, donneesAnnuelles.getPopulation());
            pstmt.setLong(11, id);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(DonneesAnnuelles donneesAnnuelles) {
        Connection co = getConnection();
        String sql = "INSERT INTO DonneesAnnuelles (lAnnee, laCommune, nbMaison, nbAppart, prixMoyen, prixM2Moyen, SurfaceMoy, depensesCulturellesTotales, budgetTotal, population) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setInt(1, donneesAnnuelles.getAnnee().getAnnee());
            pstmt.setInt(2, donneesAnnuelles.getCommune().getIdCommune());
            pstmt.setInt(3, donneesAnnuelles.getNbMaison());
            pstmt.setInt(4, donneesAnnuelles.getNbAppart());
            pstmt.setFloat(5, donneesAnnuelles.getPrixMoyen());
            pstmt.setFloat(6, donneesAnnuelles.getPrixM2Moyen());
            pstmt.setFloat(7, donneesAnnuelles.getSurfaceMoyenne());
            pstmt.setFloat(8, donneesAnnuelles.getDepensesCulturellesTotales());
            pstmt.setFloat(9, donneesAnnuelles.getBudgetTotal());
            pstmt.setInt(10, donneesAnnuelles.getPopulation());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(DonneesAnnuelles donneesAnnuelles) {
        Connection co = getConnection();
        String sql = "DELETE FROM DonneesAnnuelles WHERE lAnnee = ? AND laCommune = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setLong(1, donneesAnnuelles.getAnnee().getAnnee());
            pstmt.setLong(1, donneesAnnuelles.getCommune().getIdCommune());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}