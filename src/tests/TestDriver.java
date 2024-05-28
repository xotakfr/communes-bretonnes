package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de test pour vérifier la connexion à une base de données MySQL en utilisant JDBC.
 */
public class TestDriver {
    /** URL de la base de données MySQL */
    private static String url = "jdbc:mysql://127.0.0.1:3306/BDSAE";

    /** Nom d'utilisateur pour la connexion à la base de données */
    private static String user = "user";

    /** Mot de passe pour la connexion à la base de données */
    private static String password = "password";

    /**
     * Obtient une connexion à la base de données.
     *
     * @return Une instance de Connection représentant la connexion à la base de données.
     */
    protected static Connection getConnection() { 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Return null or throw an exception based on your requirement
            return null;
        } catch (SQLException e) {
            System.err.println("Invalid login or password");
            // Return null or throw an exception based on your requirement
            return null;
        }
    }
    
    /**
     * Point d'entrée de l'application de test.
     *
     * @param args Les arguments de la ligne de commande (non utilisés dans cette application).
     */
    public static void main (String[] args) {
        getConnection();
    }
}