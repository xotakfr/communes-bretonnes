package modele.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.util.ArrayList;

import modele.data.Annee;

/**
 * Classe DAO pour le type Annee
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class AnneeDAO {

    /**
     * Lance une requête SQL
     * @param connection La connexion à la base de données
     * @param sql La requête SQL
     * @return Le résultat de la requête SQL
     * @throws Exception - quand un problème est détecté avec la base de données
     */
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

    /**
     * Permet de retrouver toute les instances
     * @param co La connexion à la base de données
     * @param id L'ID qu'on doit rechercher
     * @return L'instance correspondante à l'ID
     */
    public static ArrayList<Annee> getFromId(Connection co, int id) {
        ArrayList<Annee> ann = new ArrayList<Annee>();

        try {
            ann = runSQLQuery(co, "SELECT * FROM Annee;");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }

    /**
     * Permet de retrouver une instance à partir de son ID
     * @param co La connexion à la base de données
     * @param id L'ID qu'on doit rechercher
     * @return L'instance correspondante à l'ID
     */
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

    /**
     * Permet de mettre à jour les données d'une instance
     * @param co La connexion à la base de données
     * @param id L'ID de l'instance
     * @param annee L'instance qui va remplacer celle qu'on recherche
     */
    public void update(Connection co, int id, Annee annee) {
        String sql = "UPDATE Annees SET tauxInflation = ? WHERE annee = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setFloat(1, annee.getTauxInflation());
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de créer une instance
     * @param co La connexion à la base de données
     * @param annee L'instance qu'on va créer
     */
    public void create(Connection co, Annee annee) {
        String sql = "INSERT INTO Annees (annee, tauxInflation) VALUES (?, ?)";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setInt(1, annee.getAnnee());
            pstmt.setFloat(2, annee.getTauxInflation());

            pstmt.executeUpdate();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet d'effacer une instance
     * @param co La connexion à la base de données
     * @param annee L'instance qu'on va effacer
     */
    public void delete(Connection co, Annee annee) {
        String sql = "DELETE FROM Annees WHERE annee = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setInt(1, annee.getAnnee());

            pstmt.executeUpdate();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}