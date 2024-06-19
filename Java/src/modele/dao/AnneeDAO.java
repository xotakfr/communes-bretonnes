package modele.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;


import modele.data.Annee;
import modele.data.Commune;

/**
 * Data Access for Annee
 * Use all fonction in DAO for the Annee objects
 */
public class AnneeDAO extends DAO<Annee> {
    /** Filtre actuel - Voir comparableTo et SwitecherFilter */
    private static String currentFilter = "annee";
    /** Liste des filtres possibles */
    private static String[] filtersList = new String[]{"annee", "tauxInflation"};


    protected static ArrayList<Annee> runSQLQuery(Connection connection, String sql) throws Exception {
        ArrayList<Annee> results = new ArrayList<Annee>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        Annee com;

        while (resultSet.next()) {
            com = new Annee(
                    resultSet.getInt(0), 
                    resultSet.getFloat(1)
                );
            results.add(com);
        }
        
        resultSet.close();
        statement.close();
        return results;
    }

    public  ArrayList<Annee> findAll(Connection c) {
        ArrayList<Annee> arr = new ArrayList<Annee>();
        try {
            arr = runSQLQuery(c, "SELECT * FROM Annee;");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    public static ArrayList<Annee> getFromId(Connection co, int id) {
        ArrayList<Annee> str = null;

        try {
            str = runSQLQuery(co, "SELECT * FROM Annee WHERE \"Annee.annee\" LIKE \""+id+"\";");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }


        return str;
    }

    public void update(Connection co, int id, Annee annee) {
        String sql = "UPDATE Annees SET tauxInflation = ? WHERE annee = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setFloat(1, annee.getTauxInflation());
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(Connection co, Annee annee) {
        String sql = "INSERT INTO Annees (annee, tauxInflation) VALUES (?, ?)";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setInt(1, annee.getAnnee());
            pstmt.setFloat(2, annee.getTauxInflation());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Connection co, Annee annee) {
        String sql = "DELETE FROM Annees WHERE annee = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setInt(1, annee.getAnnee());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}