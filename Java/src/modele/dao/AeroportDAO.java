package modele.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.util.ArrayList;

import modele.data.Aeroport;

/**
 * Classe DAO pour le type Aeroport
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class AeroportDAO {

    /**
     * Lance une requête SQL
     * @param connection La connexion à la base de données
     * @param sql La requête SQL
     * @return Le résultat de la requête SQL
     * @throws Exception - quand un problème est détecté avec la base de données
     */
    protected static ArrayList<Aeroport> runSQLQuery(Connection connection, String sql) throws Exception {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<Aeroport> ann = new ArrayList<Aeroport>();

        while (resultSet.next()) {
            Aeroport com = new Aeroport(resultSet.getString(0), resultSet.getString(1), new DepartementDAO().findByID(resultSet.getInt(2)));
            ann.add(com);
        }      

        resultSet.close();
        statement.close();
        return ann;
    }

    /**
     * Permet de retrouver toute les instances
     * @param co La connexion à la base de données
     * @param id L'ID qu'on doit rechercher
     * @return L'instance correspondante à l'ID
     */
    public static ArrayList<Aeroport> findAll(Connection co) {
        ArrayList<Aeroport> ann = new ArrayList<Aeroport>();

        try {
            ann = runSQLQuery(co, "SELECT * FROM Aeroport;");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return ann;
    }

    /**
     * Permet de retrouver une instance à partir de son nom
     * @param co La connexion à la base de données
     * @param nom Le nom qu'on doit rechercher
     * @return L'instance correspondante à l'ID
     */
    public static Aeroport getFromId(Connection co, String nom) {
        Aeroport str = null;

        try {
            str = runSQLQuery(co, "SELECT * FROM Aeroport WHERE \"Aeroport.nom\" LIKE \""+nom+"\";").get(0);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }

    /**
     * Permet de mettre à jour les données d'une instance
     * @param co La connexion à la base de données
     * @param nom Le nom de l'instance
     * @param Aeroport L'instance qui va remplacer celle qu'on recherche
     */
    public void update(Connection co, String nom, Aeroport Aeroport) {
        String sql = "UPDATE Aeroport SET adresse = ?, leDepartement = ? WHERE nom = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setString(1, Aeroport.getAdresse());
            pstmt.setInt(2, Aeroport.getLeDepartement().getIdDep());
            pstmt.setString(3, nom);

            pstmt.executeUpdate();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de créer une instance
     * @param co La connexion à la base de données
     * @param Aeroport L'instance qu'on va créer
     */
    public void create(Connection co, Aeroport Aeroport) {
        String sql = "INSERT INTO Aeroport (nom, adresse, tauxInflation) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setString(1, Aeroport.getNom());
            pstmt.setString(2, Aeroport.getAdresse());
            pstmt.setInt(3, Aeroport.getLeDepartement().getIdDep());

            pstmt.executeUpdate();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet d'effacer une instance
     * @param co La connexion à la base de données
     * @param Aeroport L'instance qu'on va effacer
     */
    public void delete(Connection co, Aeroport Aeroport) {
        String sql = "DELETE FROM Aeroport WHERE Aeroport.nom = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setString(1, Aeroport.getNom());

            pstmt.executeUpdate();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}