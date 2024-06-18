package modele.classesDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;


import modele.classesModele.Annee;

/**
 * Data Access for Annee
 * Use all fonction in DAO for the Annee objects
 */
public class AnneeDAO extends DAO<Annee>{
    /** Filtre actuel - Voir comparableTo et SwitecherFilter */
    private static String currentFilter = "annee";
    /** Liste des filtres possibles */
    private static String[] filtersList = new String[]{"annee", "tauxInflation"};


    protected static Annee runSQLQuery(Connection connection, String sql) throws Exception {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        Annee com = new Annee(0,0f);

        
        while (resultSet.next()) {
            com = new Annee(resultSet.getInt(0), resultSet.getFloat(1));
        }
        
        resultSet.close();
        statement.close();
        return com;
    }

    public static Annee getFromId(Connection co, int id) {
        Annee str = new Annee(0,0f);

        try {
            str = runSQLQuery(co, "SELECT * FROM Annee WHERE \"Annee.annee\" LIKE \""+id+"\";");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }


        return str;
    }

    public void update(int id, Annee annee) {
        Connection co = getConnection();
        String sql = "UPDATE Annees SET tauxInflation = ? WHERE annee = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setFloat(1, annee.getTauxInflation());
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(Annee annee) {
        Connection co = getConnection();
        String sql = "INSERT INTO Annees (annee, tauxInflation) VALUES (?, ?)";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setInt(1, annee.getAnnee());
            pstmt.setFloat(2, annee.getTauxInflation());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Annee annee) {
        Connection co = getConnection();
        String sql = "DELETE FROM Annees WHERE annee = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setInt(1, annee.getAnnee());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}