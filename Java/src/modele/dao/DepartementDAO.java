package modele.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import modele.data.Departement;

/**
 * Classe DAO pour le type Departement
 * Hérite de DAO
 * @see DAO
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class DepartementDAO extends DAO<Departement> {
    /** Filtre actuel - Voir comparableTo et SwitecherFilter */
    private static String currentFilter = "idCommune";
    /** Liste des filtres possibles */
    private static String[] filtersList = new String[]{"idDep", "nomDep", "invesCulturel2019"};

    /**
     * Lance une requête SQL
     * @param connection La connexion à la base de données
     * @param sql La requête SQL
     * @return Le résultat de la requête SQL
     * @throws Exception - quand un problème est détecté avec la base de données
     */
    protected ArrayList<Departement> runSQLQuery(Connection connection, String sql) throws Exception {
        ArrayList<Departement> results = new ArrayList<Departement>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Departement com = new Departement(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getFloat(3)
                            );
            results.add(com);
        }
    
        resultSet.close();
        statement.close();
        return results;
    }

    /**
     * Permet de retrouver l'ensemble des instances de type Departement de la base de données
     * @param c La connexion à la base de données
     * @return L'ensemble des instances de type Departement de la base de données
     */
    @Override
    public ArrayList<Departement> findAll(Connection c) {
        ArrayList<Departement> arr = new ArrayList<>();
        try {
            arr = runSQLQuery(c, "SELECT * FROM Departement;");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    /**
     * Renvoie l'instance de type Departement correspondant à l'ID
     * @param c La connexion à la base de données
     * @param id L'ID 
     * @return L'instance de type Departement correspondant à l'ID
     */
    public Departement findByID(Connection c, long id) {
        ArrayList<Departement> arr = new ArrayList<Departement>();

        try {
            arr = runSQLQuery(c, "SELECT * FROM Departement WHERE idDep = "+id+";");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return arr.get(0);
    }

    /**
     * Renvoie la ou les instances correspondant au filtre
     * @param co La connexion à la base de données
     * @param filter Le paramètre du filtre
     * @param filterSelect La valeur du filtre
     * @return La ou les instances correspondant au filtre
     */
    public  ArrayList<Departement> findByFilter(Connection co, String filter, String filterSelect) {
        ArrayList<Departement> arr = new ArrayList<Departement>();

        try {
            arr = runSQLQuery(co, "SELECT * FROM Departement WHERE \"Departements."+filter+"\""+filterSelect+";");
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
     * @param departement L'instance qui va remplacer celle qu'on recherche
     */
    public void update(Connection co, long id, Departement departement) {
        String sql = "UPDATE Departement SET nomDep = ?, investissementCulturel2019 = ? WHERE idDep = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setString(1, departement.getNomDep());
            pstmt.setFloat(2, departement.getInvesCulturel2019());
            pstmt.setLong(3, id);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de créer une instance
     * @param co La connexion à la base de données
     * @param departement L'instance qu'on va créer
     */
    public void create(Connection co, Departement departement) {
        String sql = "INSERT INTO Departement (idDep, nomDep, investissementCulturel2019) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setInt(1, departement.getIdDep());
            pstmt.setString(2, departement.getNomDep());
            pstmt.setFloat(3, departement.getInvesCulturel2019());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet d'effacer une instance
     * @param co La connexion à la base de données
     * @param departement L'instance qu'on va effacer
     */
    public void delete(Connection co, Departement departement) {
        String sql = "DELETE FROM Departement WHERE idDep = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setInt(1, departement.getIdDep());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}