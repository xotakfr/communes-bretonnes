package modele.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite DAO 
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public abstract class DAO<T> {
    /** L'url vers la base de données */
    private static String url = "jdbc:mysql://127.0.0.1:3306/BDSAE";
    /** L'username de l'utilisateur */
    private static String user = "";
    /** Le mot de passe de l'utilisateur */
    private static String password = "";
    
    /**
     * Renvoie la connexion à la base de données
     * @return La connexion à la base de données
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
     * Lance une requête SQL
     * @param connection La connexion à la base de données
     * @param sql La requête SQL
     * @returns Le résultat de la requête SQL
     * @throws Exception - quand un problème est détecté avec la base de données
     */
    protected abstract List<T> runSQLQuery(Connection connection, String sql) throws Exception;

    /**
     * Permet de retrouver l'ensemble des instances de même type de la base de données
     * @param c La connexion à la base de données
     * @return L'ensemble des instances de même type de la base de données
     */
    public abstract ArrayList<T> findAll(Connection c);
}