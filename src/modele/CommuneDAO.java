package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access for Commune
 * Use all fonction in DAO for the Commune objects
 */
public class CommuneDAO extends DAO<Commune> {

    private static List<Object[]> runSQLQuery(Connection connection, String sql) throws Exception {
        List<Object[]> results = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Commune com = new Commune(resultSet.getObject(0),resultSet.getObject(1), resultSet.getObject(2));
            results.add(com);
        }

        resultSet.close();
        statement.close();
        return results;
    }    
}