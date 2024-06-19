package modele.classesDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.PreparedStatement;

import modele.classesModele.Commune;

/**
 * Data Access for Commune
 * Use all fonction in DAO for the Commune objects
 */
public class CommuneDAO extends DAO<Commune> {
    /** Filtre actuel - Voir comparableTo et SwitcherFilter */
    private static String currentFilter = "idCommune";
    /** Liste des filtres possibles */
    private static String[] filtersList = new String[]{"idCommune", "nomCommune", "voisins","population"};


    protected ArrayList<Commune> runSQLQuery(Connection connection, String sql) throws Exception {
        ArrayList<Commune> results = new ArrayList<Commune>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        
        while (resultSet.next()) {
            Commune com = new Commune(
                            resultSet.getInt(1),
                            resultSet.getString(2), 
                            new DepartementDAO().findByID(connection, resultSet.getInt(3))
                        );
            results.add(com);
        }
        
        resultSet.close();
        statement.close();
        return results;
    }


    public  ArrayList<Commune> findAll(Connection c) {
        ArrayList<Commune> arr = new ArrayList<Commune>();
        try {
            arr = runSQLQuery(c, "SELECT * FROM Commune;");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        /*
        for (Commune commune : arr) {
            ArrayList<Commune> voisines = new ArrayList<Commune>();
            int com = Integer.parseInt(da[0]);
            String[] near_c = commune.split("\\|");// Séparation de chaque ville depuis le CSV
            for (String nea : near_c) {
                Commune voisine = communes.get(searcher.search(communes, new Commune(Integer.parseInt(nea), "Searching", null)));
                voisines.add(voisine); // Ajout de la voisine dans la liste des voisines
            }
            current.setVoisins(voisines);
        */

        return arr;
    }


    public Commune findByID(Connection c, long id) {
        ArrayList<Commune> arr = new ArrayList<>();
        try {
            arr = runSQLQuery(c, "SELECT * FROM Commune WHERE idCommune = "+id+";");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        /*
        for (Commune commune : arr) {
            ArrayList<Commune> voisines = new ArrayList<Commune>();
            int com = Integer.parseInt(da[0]);
            String[] near_c = commune.split("\\|");// Séparation de chaque ville depuis le CSV
            for (String nea : near_c) {
                Commune voisine = communes.get(searcher.search(communes, new Commune(Integer.parseInt(nea), "Searching", null)));
                voisines.add(voisine); // Ajout de la voisine dans la liste des voisines
            }
            current.setVoisins(voisines);
        */

        return arr.get(0);
    }


    public  ArrayList<Commune> findByFilter(String filter, String filterSelect) {
        Connection co = getConnection();
        ArrayList<Commune> arr = new ArrayList<Commune>();

        try {
            arr = runSQLQuery(co, "SELECT * FROM Commune WHERE \"Communes."+filter+"\""+filterSelect+";");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        /*
        for (Commune commune : arr) {
            ArrayList<Commune> voisines = new ArrayList<Commune>();
            int com = Integer.parseInt(da[0]);
            String[] near_c = commune.split("\\|");// Séparation de chaque ville depuis le CSV
            for (String nea : near_c) {
                Commune voisine = communes.get(searcher.search(communes, new Commune(Integer.parseInt(nea), "Searching", null)));
                voisines.add(voisine); // Ajout de la voisine dans la liste des voisines
            }
            current.setVoisins(voisines);
        */

        return arr;
    }

    public Commune findByName(Connection c, String name) {
        ArrayList<Commune> arr = new ArrayList<>();
        try {
            arr = runSQLQuery(c, "SELECT * FROM Commune WHERE nomCommune LIKE \""+name+"\";");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return arr.get(0);
    }


    public void update(Connection co, long id, Commune commune) {
        String sql = "UPDATE Commune SET nomCommune = ?, leDepartement = ? WHERE idCommune = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setString(1, commune.getNomCommune());
            pstmt.setInt(2, commune.getLeDepartement().getIdDep());
            pstmt.setLong(3, id);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(Connection co, Commune commune) {
        String sql = "INSERT INTO Commune (idCommune, nomCommune, leDepartement) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setInt(1, commune.getIdCommune());
            pstmt.setString(2, commune.getNomCommune());
            pstmt.setInt(3, commune.getLeDepartement().getIdDep());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Connection co, Commune commune) {
        String sql = "DELETE FROM Commune WHERE idCommune = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setInt(1, commune.getIdCommune());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
