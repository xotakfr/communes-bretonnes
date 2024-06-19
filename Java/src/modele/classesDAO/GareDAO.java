package modele.classesDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import modele.classesModele.Gare;;

/**
 * Data Access for Gare
 * Use all fonction in DAO for the Gare objects
 */
public class GareDAO extends DAO<Gare> {
    /** Filtre actuel - Voir comparableTo et SwitecherFilter */
    private static String currentFilter = "idCommune";
    /** Liste des filtres possibles */
    private static String[] filtersList = new String[]{"codeGare", "nomGare", "estFret", "estVoyageur", "laCommune"};


    protected  ArrayList<Gare> runSQLQuery(Connection connection, String sql) throws Exception {
        ArrayList<Gare> results = new ArrayList<Gare>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        
        while (resultSet.next()) {
            Gare com = new Gare(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getBoolean(3),
                            resultSet.getBoolean(4),
                            new CommuneDAO().findByID(connection, resultSet.getInt(5))
                        );
            results.add(com);
        }
        
        resultSet.close();
        statement.close();
        return results;
    }

    @Override
    public  ArrayList<Gare> findAll(Connection co) {
        ArrayList<Gare> arr = new ArrayList<Gare>();

        try {
            arr = runSQLQuery(co, "SELECT * FROM Gare;");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        /**
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


    public  Gare findByID(Connection co, long id) {
        ArrayList<Gare> arr = new ArrayList<Gare>();

        try {
            arr = runSQLQuery(co, "SELECT * FROM Gare WHERE \"Gares.idCommune\" = "+id+";");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        /**
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


    public  ArrayList<Gare> findByFilter(Connection co, String filter, String filterSelect) {
        ArrayList<Gare> arr = new ArrayList<Gare>();

        try {
            arr = runSQLQuery(co, "SELECT * FROM Gare WHERE \"Gares."+filter+"\""+filterSelect+";");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        /**
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
    public void update(Connection co, long id, Gare gare) {
        String sql = "UPDATE Gare SET nomGare = ?, estFret = ?, estVoyageur = ?, laCommune = ? WHERE codeGare = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setString(1, gare.getNomGare());
            pstmt.setBoolean(2, gare.getFretValue());
            pstmt.setBoolean(3, gare.getVoyageurValue());
            pstmt.setInt(4, gare.getLaCommune().getIdCommune());
            pstmt.setLong(5, id);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(Connection co, Gare gare) {
        String sql = "INSERT INTO Gare (codeGare, nomGare, estFret, estVoyageur, laCommune) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setInt(1, gare.getCodeGare());
            pstmt.setString(2, gare.getNomGare());
            pstmt.setBoolean(3, gare.getFretValue());
            pstmt.setBoolean(4, gare.getVoyageurValue());
            pstmt.setInt(5, gare.getLaCommune().getIdCommune());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Connection co, Gare gare) {
        String sql = "DELETE FROM Gare WHERE codeGare = ?";

        try (PreparedStatement pstmt = co.prepareStatement(sql)) {
            pstmt.setInt(1, gare.getCodeGare());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}