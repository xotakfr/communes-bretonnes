package modele.classesDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import modele.classesModele.User;

/**
 * Data Access for User
 * Use all fonction in DAO for the User objects
 */
public class UserDAO {
    /** Filtre actuel - Voir comparableTo et SwitecherFilter */
    private static String currentFilter = "idUser";
    /** Liste des filtres possibles */
    private static String[] filtersList = new String[]{"idUser", "nomUser","communes"};


    protected  String runSQLQuery(Connection connection, String sql) throws Exception {
        ArrayList<User> results = new ArrayList<User>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        
        while (resultSet.next()) {
            String com = resultSet.getString(0);
            results.add(com);
        }
        
        resultSet.close();
        statement.close();
        return results;
    }

    public String getName(long id) {
        Connection co = getConnection();

        try {
            String str = runSQLQuery(co, "SELECT nomUser FROM Users WHERE \"Users.idUser\" = "+id+";");
        } catch (Exception e) {
            e.printStackTrace();
        }


        return str;
    }


    public ArrayList<Commune> getAccesCommunes(long id) {
        Connection co = getConnection();
        ArrayList<Commune> arr = new ArrayList<Commune>();

        try {
            String str = runSQLQuery(co, "SELECT * FROM Users WHERE \"Users.idUser\""+filterSelect+";");
            String[] a = str.split(";");
            for (String s : a) {
                arr.add(new Commune(s,"Commune Sans Nom", new Departement(56, "Département Sans Nom", 0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return arr;
    }
}
