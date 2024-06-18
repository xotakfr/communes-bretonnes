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
            arr = runSQLQuery(c, "SELECT * FROM Communes;");
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
            arr = runSQLQuery(co, "SELECT * FROM Communes WHERE \"Communes."+filter+"\""+filterSelect+";");
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


    public void update(long id, Commune commune) {
        Connection co = getConnection();
        String sql = "UPDATE Communes SET nomCommune = ?, idDepartement = ? WHERE idCommune = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setString(1, commune.getNomCommune());
            pstmt.setInt(2, commune.getDepartement().getIdDepartement());
            pstmt.setLong(3, id);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(Commune commune) {
        Connection co = getConnection();
        String sql = "INSERT INTO Communes (idCommune, nomCommune, idDepartement) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setInt(1, commune.getIdCommune());
            pstmt.setString(2, commune.getNomCommune());
            pstmt.setInt(3, commune.getDepartement().getIdDepartement());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Commune commune) {
        Connection co = getConnection();
        String sql = "DELETE FROM Communes WHERE idCommune = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setInt(1, commune.getIdCommune());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
