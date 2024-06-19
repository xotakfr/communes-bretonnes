package modele.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import modele.data.DonneesAnnuelles;

/**
 * Classe DAO pour le type DonneesAnnuelles.
 * Hérite de DAO
 * @see DAO
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class DonneesAnnuellesDAO extends DAO<DonneesAnnuelles> {
    /** Filtre actuel - Voir comparableTo  */
    private static String currentFilter = "laCommune";
    /** Liste des filtres posibles */
    private static String[] filtersList = new String[]{"idDep", "nomDep", "invesCulturel2019"};

    /**
     * Lance une requête SQL
     * @param connection La connexion à la base de données
     * @param sql La requête SQL
     * @return Le résultat de la requête SQL
     * @throws Exception - quand un problème est détecté avec la base de données
     */
    protected ArrayList<DonneesAnnuelles> runSQLQuery(Connection connection, String sql) throws Exception {
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

    /**
     * Permet de retrouver l'ensemble des instances de type DonneesAnnuelles de la base de données
     * @param c La connexion à la base de données
     * @return L'ensemble des instances de type DonneesAnnuelles de la base de données
     */
    @Override
    public ArrayList<DonneesAnnuelles> findAll(Connection c) {
        ArrayList<DonneesAnnuelles> arr = new ArrayList<DonneesAnnuelles>();

        try {
            arr = runSQLQuery(c, "SELECT * FROM DonneesAnnuelles;");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return arr;
    }

    /**
     * Renvoie l'instance de type DonneesAnnuelles correspondant à l'ID
     * @param c La connexion à la base de données
     * @param id L'ID 
     * @return L'instance de type DonneesAnnuelles correspondant à l'ID
     */
    public DonneesAnnuelles findByID(Connection c, long id) {
        ArrayList<DonneesAnnuelles> arr = new ArrayList<DonneesAnnuelles>();

        try {
            arr = runSQLQuery(c, "SELECT * FROM DonneesAnnuelles WHERE \"DonneesAnnuelles.laCommune\" = "+id+";");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return arr.get(0);
    }

    /**
     * Renvoie la ou les instances de correspondant au filtre
     * @param filter Le paramètre du filtre
     * @param filterSelect La valeur du filtre
     * @return La ou les instances correspondant au filtre
     */
    public ArrayList<DonneesAnnuelles> findByFilter(String filter, String filterSelect) {
        Connection co = getConnection();
        ArrayList<DonneesAnnuelles> arr = new ArrayList<DonneesAnnuelles>();

        try {
            arr = runSQLQuery(co, "SELECT * FROM DonneesAnnuelles WHERE \"DonneesAnnuelles."+filter+"\""+filterSelect+";");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return arr;
    }

    /**
     * Renvoie la ou les instances correspondant à la commune ET à l'année
     * @param c La connexion à la base de données
     * @param id L'ID de la commune
     * @param annee L'année
     * @return La ou les instances correspondant à la commune ET à l'année
     */
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

    /**
     * Permet de mettre à jour les données d'une instance
     * @param id L'ID de l'instance
     * @param donneesAnnuelles L'instance qui va remplacer celle qu'on recherche
     */
    public void update(Connection co, long id, long commune, DonneesAnnuelles donneesAnnuelles) {
        String sql = "UPDATE DonneesAnnuelles SET lAnnee = ?, laCommune = ?, nbMaison = ?, nbAppart = ?, prixMoyen = ?, prixM2Moyen = ?, SurfaceMoy = ?, depensesCulturellesTotales = ?, budgetTotal = ?, population = ? WHERE lAnnee = ? AND laCommune = ?";

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
            pstmt.setLong(12, commune);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de créer une instance
     * @param donneesAnnuelles L'instance qu'on va créer
     */
    public void create(Connection co, DonneesAnnuelles donneesAnnuelles) {
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

    /**
     * Permet d'effacer une instance
     * @param donneesAnnuelles L'instance qu'on va effacer
     */
    public void delete(Connection co, DonneesAnnuelles donneesAnnuelles) {
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