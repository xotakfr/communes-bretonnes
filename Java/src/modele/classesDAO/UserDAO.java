package modele.classesDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import modele.classesModele.Departement;
import modele.classesModele.Commune;
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
}
