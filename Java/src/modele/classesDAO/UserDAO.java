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


    protected  String runSQLQuery(Connection connection, String sql) throws Exception {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        String com;

        
        while (resultSet.next()) {
            com = resultSet.getString(0);
        }
        
        resultSet.close();
        statement.close();
        return com;
    }

    public String getName(Connection co, long id) {
        String str;

        try {
            str = runSQLQuery(co, "SELECT nomUser FROM Users WHERE \"Users.idUser\" = "+id+";");
        } catch (Exception e) {
            e.printStackTrace();
        }


        return str;
    }


    public ArrayList<Commune> getAccesCommunes(Connection co, long id) {
        ArrayList<Commune> arr = new ArrayList<Commune>();

        try {
            String str = runSQLQuery(co, "SELECT * FROM Users WHERE \"Users.idUser\""+filterSelect+";");
            String[] a = str.split(";");
            for (String s : a) {
                arr.add(new Commune(s,"Commune Sans Nom", new Departement(56, "Département Sans Nom", 0)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return arr;
    }
}
