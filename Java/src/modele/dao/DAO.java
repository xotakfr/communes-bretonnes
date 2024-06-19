package modele.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Data Access Object
 * 
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public abstract class DAO<T> {
    private static String url = "jdbc:mysql://127.0.0.1:3306/BDSAE";
    private static String user = "";
    private static String password = "";
    
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
     * Utilitaire de lancement d'une requête SQL
     * Voir exemple implémentation dans CommuneDAO
     */
    //protected abstract List<T> runSQLQuery(Connection connection, String sql) throws Exception;

    public abstract ArrayList<T> findAll(Connection c);

    /**
    public abstract T findById(long id);
    public abstract void update(T element); 
    public abstract void create(T element);
    public abstract void delete(T element);
    */

    /**
     * Voir documentation de SwitcherFilter
     * 
     * Est censé proposer un accès de recherche en limitant les instructions SQL "dangeureuses"
     * @param filter Filtre de recherche (liste de filtres accessibles dans ObjectDAO.filtersList)
     * @param filterSelect Filtre de séléction (Accepte plusieurs instructions via ;)
     */
    //public abstract T findByFilter(String filter, String filterSelect);
    //protected abstract static String[] filtersList;
    //protected abstract static String currentFilter;

    
}