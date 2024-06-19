package modele.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import modele.data.Commune;
/**
 * Data Access for User
 * Use all fonction in DAO for the User objects
 */
public class UserDAO {
    /** Filtre actuel - Voir comparableTo et SwitecherFilter */
    private static String currentFilter = "idUser";
    /** Liste des filtres possibles */
    private static String[] filtersList = new String[]{"idUser", "nomUser","communes"};


    protected static String runSQLQuery(Connection connection, String sql) throws Exception {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        String com = "";

        
        while (resultSet.next()) {
            com = resultSet.getString(1);
        }
        
        resultSet.close();
        statement.close();
        return com;
    }

    public static String getName(Connection co, String username) {
        String str = "";

        try {
            str = runSQLQuery(co, "SELECT nomUser FROM Users WHERE username LIKE \""+username+"\";");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }


        return str;
    }


    public static ArrayList<Commune> getAccesCommunes(Connection co, String username) {
        ArrayList<Commune> arr = new ArrayList<Commune>();

        try {
            String str = runSQLQuery(co, "SELECT communes FROM Users WHERE username LIKE \""+username+"\";");
            String[] a = str.split(";");
            for (String s : a) {
                //arr.add(new Commune(Integer.parseInt(s),"Commune Sans Nom", new Departement(56, "Département Sans Nom", 0)));
                arr.add(new CommuneDAO().findByID(co, Long.parseLong(s)));
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }


        return arr;
    }

    public void update(Connection co, int id, String username, String communes) {
        String sql = "UPDATE Users SET username = ?, communes = ? WHERE id = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, communes);
            pstmt.setInt(3, id);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(Connection co, int id, String username, String communes) {
        String sql = "INSERT INTO Users (id, username, communes) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, username);
            pstmt.setString(3, communes);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Connection co, int id) {
        String sql = "DELETE FROM Users WHERE id = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
