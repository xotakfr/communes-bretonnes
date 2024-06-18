package modele.classesDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import modele.classesModele.Annee;

/**
 * Data Access for Annee
 * Use all fonction in DAO for the Annee objects
 */
public class AnneeDAO {
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
}