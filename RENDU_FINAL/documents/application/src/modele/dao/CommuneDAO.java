package modele.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import modele.data.Commune;

/**
 * Classe DAO pour le type Commune.
 * Hérite de DAO
 * @see DAO
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class CommuneDAO extends DAO<Commune> {
    /** Filtre actuel - Voir comparableTo */
    private static String currentFilter = "idCommune";
    /** Liste des filtres possibles */
    private static String[] filtersList = new String[]{"idCommune", "nomCommune", "voisins","population"};

     /**
     * Lance une requête SQL
     * @param connection La connexion à la base de données
     * @param sql La requête SQL
     * @return Le résultat de la requête SQL
     * @throws Exception - quand un problème est détecté avec la base de données
     */
    protected ArrayList<Commune> runSQLQuery(Connection connection, String sql) throws Exception {
        ArrayList<Commune> results = new ArrayList<Commune>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        
        while (resultSet.next()) {
            Commune com = new Commune(
                            resultSet.getInt(1),
                            resultSet.getString(2), 
                            new DepartementDAO().findByID(connection, resultSet.getInt(3))
                        );
            results.add(com);
        }
        
        resultSet.close();
        statement.close();
        return results;
    }

    /**
     * Permet de retrouver l'ensemble des instances de type Commune de la base de données
     * @param c La connexion à la base de données
     * @return L'ensemble des instances de type Commune de la base de données
     */
    public  ArrayList<Commune> findAll(Connection c) {
        ArrayList<Commune> arr = new ArrayList<Commune>();
        try {
            arr = runSQLQuery(c, "SELECT * FROM Commune;");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    /**
     * Renvoie l'instance de type Commune correspondant à l'ID
     * @param c La connexion à la base de données
     * @param id L'ID 
     * @return L'instance de type Commune correspondant à l'ID
     */
    public Commune findByID(Connection c, long id) {
        ArrayList<Commune> arr = new ArrayList<>();
        try {
            arr = runSQLQuery(c, "SELECT * FROM Commune WHERE idCommune = "+id+";");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return arr.get(0);
    }

    /**
     * Renvoie la ou les instances de type correspondant au filtre
     * @param filter Le paramètre du filtre
     * @param filterSelect La valeur du filtre
     * @return La ou les instances correspondant au filtre
     */
    public ArrayList<Commune> findByFilter(String filter, String filterSelect) {
        Connection co = getConnection();
        ArrayList<Commune> arr = new ArrayList<Commune>();

        try {
            arr = runSQLQuery(co, "SELECT * FROM Commune WHERE \"Communes."+filter+"\""+filterSelect+";");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return arr;
    }

    /**
     * Renvoie la ou les instances de type Commune correspondant au nom
     * @param c La connexion à la base de données
     * @param name Le nom
     * @return La ou les instances de type Commune correspondant au nom
     */
    public Commune findByName(Connection c, String name) {
        ArrayList<Commune> arr = new ArrayList<>();
        try {
            arr = runSQLQuery(c, "SELECT * FROM Commune WHERE nomCommune LIKE \""+name+"\";");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return arr.get(0);
    }

    /**
     * Permet de mettre à jour les données d'une instance
     * @param co La connexion à la base de données
     * @param id L'ID de l'instance
     * @param commune L'instance qui va remplacer celle qu'on recherche
     */
    public void update(Connection co, long id, Commune commune) {
        String sql = "UPDATE Commune SET nomCommune = ?, leDepartement = ? WHERE idCommune = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setString(1, commune.getNomCommune());
            pstmt.setInt(2, commune.getLeDepartement().getIdDep());
            pstmt.setLong(3, id);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de créer une instance
     * @param co La connexion à la base de données
     * @param commune L'instance qu'on va créer
     */
    public void create(Connection co, Commune commune) {
        String sql = "INSERT INTO Commune (idCommune, nomCommune, leDepartement) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setInt(1, commune.getIdCommune());
            pstmt.setString(2, commune.getNomCommune());
            pstmt.setInt(3, commune.getLeDepartement().getIdDep());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet d'effacer une instance
     * @param co La connexion à la base de données
     * @param commune L'instance qu'on va effacer
     */
    public void delete(Connection co, Commune commune) {
        String sql = "DELETE FROM Commune WHERE idCommune = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setInt(1, commune.getIdCommune());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
