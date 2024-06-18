package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe nous permet de vérifier si la connexion à notre BDD MySQL est fonctionnelle en utilisant JDBC
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class TestDriver {
    /** Chemin vers la BDD MySQL */
    private static String url = "jdbc:mysql://127.0.0.1:3306/BDSAE";

    /** Nom d'utilisateur pour la connexion à la base de données */
    private static String user = "user";

    /** Mot de passe pour la connexion à la base de données */
    private static String password = "password";

    /**
     * Permer de créer et de renvoyer une connexion à la BDD MySQL
     * @return Une instance de Connection représentant la connexion à la base de données
     */
    protected static Connection getConnection() { 
        Connection ret = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ret = DriverManager.getConnection(url, user, password);
        } 
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
        catch (SQLException e) {
            System.err.println("Invalid login or password");
        }
        return ret;
    }
    
    /**
     * Point d'entrée du programme
     * @param args Les arguments de la ligne de commande
     */
    public static void main (String[] args) {
        getConnection();
    }
}