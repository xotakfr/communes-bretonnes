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

    private static ArrayList<Commune> runSQLQuery(Connection connection, String sql) throws Exception {
        ArrayList<Commune> results = new ArrayList<>();
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

    public ArrayList<Commune> findAll() {
        Connection = getConnection();

        ArrayList<Commune> arr = runSQLQuery("SELECT * FROM Communes;");

        for (Commune commune : arr) {
            ArrayList<Commune> voisines = new ArrayList<Commune>();
            int com = Integer.parseInt(da[0]);
            String[] near_c = commune.split("\\|");// Séparation de chaque ville depuis le CSV
            for (String nea : near_c) {
                Commune voisine = communes.get(searcher.search(communes, new Commune(Integer.parseInt(nea), "Searching", null)));
                voisines.add(voisine); // Ajout de la voisine dans la liste des voisines
            }
            current.setVoisins(voisines);

        return 
    }
}