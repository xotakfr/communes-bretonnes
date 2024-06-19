package modele.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import modele.data.Gare;;

/**
 * Classe DAO pour le type Gare.
 * Hérite de DAO
 * @see DAO
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class GareDAO extends DAO<Gare> {
    /** Filtre actuel - Voir comparableTo */
    private static String currentFilter = "idCommune";
    /** Liste des filtres possibles */
    private static String[] filtersList = new String[]{"codeGare", "nomGare", "estFret", "estVoyageur", "laCommune"};

    /**
     * Lance une requête SQL
     * @param connection La connexion à la base de données
     * @param sql La requête SQL
     * @return Le résultat de la requête SQL
     * @throws Exception - quand un problème est détecté avec la base de données
     */
    protected  ArrayList<Gare> runSQLQuery(Connection connection, String sql) throws Exception {
        ArrayList<Gare> results = new ArrayList<Gare>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        
        while (resultSet.next()) {
            Gare com = new Gare(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getBoolean(3),
                            resultSet.getBoolean(4),
                            new CommuneDAO().findByID(connection, resultSet.getInt(5))
                        );
            results.add(com);
        }
        
        resultSet.close();
        statement.close();
        return results;
    }

    /**
     * Permet de retrouver l'ensemble des instances de type Gare de la base de données
     * @param co La connexion à la base de données
     * @return L'ensemble des instances de type Gare de la base de données
     */
    @Override
    public  ArrayList<Gare> findAll(Connection co) {
        ArrayList<Gare> arr = new ArrayList<Gare>();

        try {
            arr = runSQLQuery(co, "SELECT * FROM Gare;");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return arr;
    }

    /**
     * Renvoie l'instance de type Gare correspondant à l'ID
     * @param co La connexion à la base de données
     * @param id L'ID 
     * @return L'instance de type Gare correspondant à l'ID
     */
    public  Gare findByID(Connection co, long id) {
        ArrayList<Gare> arr = new ArrayList<Gare>();

        try {
            arr = runSQLQuery(co, "SELECT * FROM Gare WHERE Gare.codeGare = "+id+";");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return arr.get(0);
    }

    /**
     * Renvoie la ou les instances de correspondant au filtre
     * @param co La connexion à la base de données
     * @param filter Le paramètre du filtre
     * @param filterSelect La valeur du filtre
     * @return La ou les instances correspondant au filtre
     */
    public ArrayList<Gare> findByFilter(Connection co, String filter, String filterSelect) {
        ArrayList<Gare> arr = new ArrayList<Gare>();

        try {
            arr = runSQLQuery(co, "SELECT * FROM Gare WHERE Gare."+filter+" = "+filterSelect+";");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return arr;
    }

    /**
     * Permet de mettre à jour les données d'une instance
     * @param co La connexion à la base de données
     * @param id L'ID de l'instance
     * @param gare L'instance qui va remplacer celle qu'on recherche
     */
    public void update(Connection co, long id, Gare gare) {
        String sql = "UPDATE Gare SET nomGare = ?, estFret = ?, estVoyageur = ?, laCommune = ? WHERE codeGare = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setString(1, gare.getNomGare());
            pstmt.setBoolean(2, gare.getFretValue());
            pstmt.setBoolean(3, gare.getVoyageurValue());
            pstmt.setInt(4, gare.getLaCommune().getIdCommune());
            pstmt.setLong(5, id);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de créer une instance
     * @param co La connexion à la base de données
     * @param gare L'instance qu'on va créer
     */
    public void create(Connection co, Gare gare) {
        String sql = "INSERT INTO Gare (codeGare, nomGare, estFret, estVoyageur, laCommune) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setInt(1, gare.getCodeGare());
            pstmt.setString(2, gare.getNomGare());
            pstmt.setBoolean(3, gare.getFretValue());
            pstmt.setBoolean(4, gare.getVoyageurValue());
            pstmt.setInt(5, gare.getLaCommune().getIdCommune());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet d'effacer une instance
     * @param co La connexion à la base de données
     * @param donneesAnnuelles L'instance qu'on va effacer
     */
    public void delete(Connection co, Gare gare) {
        String sql = "DELETE FROM Gare WHERE codeGare = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setInt(1, gare.getCodeGare());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}