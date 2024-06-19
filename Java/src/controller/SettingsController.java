package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modele.dao.*;
import modele.data.*;
import view.scenes.WelcomeScene;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import modele.data.DefaultThing;

/**
 * Contrôleur de la page de paramètrage
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class SettingsController {

    /** Classe DAO actuellement sélectionnée  */
    private String currentClasse = "";
    /** Connexion à la base de données */
    private Connection c = null;

    /** Bouton de choix entre les tables */
    @FXML
    private MenuButton tableButton;

    /** Zone de visualisation des données des tables */
    @FXML
    private TableView tableView;

    /** Première colonne pour les données */
    @FXML
    private TableColumn<DefaultThing, String> col1;
    /** Deuxième colonne pour les données */
    @FXML
    private TableColumn<DefaultThing, String> col2;
    /** Troisième colonne pour les données */
    @FXML
    private TableColumn<DefaultThing, String> col3;
    /** Quatrième colonne pour les données */
    @FXML
    private TableColumn<DefaultThing, String> col4;
    /** Cinquième colonne pour les données */
    @FXML
    private TableColumn<DefaultThing, String> col5;
    /** Sixième colonne pour les données */
    @FXML
    private TableColumn<DefaultThing, String> col6;
    /** Septième colonne pour les données */
    @FXML
    private TableColumn<DefaultThing, String> col7;
    /** Huitième colonne pour les données */
    @FXML
    private TableColumn<DefaultThing, String> col8;
    /** Neuvième colonne pour les données */
    @FXML
    private TableColumn<DefaultThing, String> col9;

    /** Valeur textuelle à afficher dans une case d'une colonne pour une table */
    @FXML
    private TextField valueText;

    /**
     * Récupère les données de la base de données afin de les visualisées
     * @param event L'ActionEvent reçu lorsqu'on sélectionne une table à visualiser
     */
    @FXML
    void tableAction(ActionEvent event) {
        Stage stage = (Stage) (((MenuItem) event.getSource()).getParentPopup().getOwnerWindow());
        c = (Connection) stage.getProperties().get("Connection");

        col1.setCellValueFactory(new PropertyValueFactory<>("col1"));
        col2.setCellValueFactory(new PropertyValueFactory<>("col2"));
        col3.setCellValueFactory(new PropertyValueFactory<>("col3"));
        col4.setCellValueFactory(new PropertyValueFactory<>("col4"));
        col5.setCellValueFactory(new PropertyValueFactory<>("col5"));
        col6.setCellValueFactory(new PropertyValueFactory<>("col6"));
        col7.setCellValueFactory(new PropertyValueFactory<>("col7"));
        col8.setCellValueFactory(new PropertyValueFactory<>("col8"));
        col9.setCellValueFactory(new PropertyValueFactory<>("col9"));

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection!=null) {
                DefaultThing d = (DefaultThing) newSelection;
                System.out.println(d.getAsData());
                valueText.setText(d.getAsData());
            }
        });

        String table = ((MenuItem) event.getSource()).getText();
        try {
            System.out.println(table);
            Statement statement = c.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM " + table + ";");
            //tableView = new ResultSetTableView(rs);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            Class<?> classeDAO = Class.forName("modele.dao."+table+"DAO");
            String className = classeDAO.getName();
            currentClasse = className;
            //DAO<?> instanceDAO =  (DAO<?>) classeDAO.getDeclaredConstructor().newInstance();

            loadTableView();


        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Récupère le nom des tables de la base de données
     * @param connection La connexion à la base de données
     */
    public void loadTableNames(Connection connection) {
        try {
            /*
            DatabaseMetaData dbmd = connection.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, "%", new String[]{"TABLE"});

            
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                MenuItem item = new MenuItem(tableName);
                item.setOnAction(this::tableAction);
                tableButton.getItems().add(item);
            }
            */
           String[] names = new String[]{"Aeroport", "Annee", "Commune", "Departement", "Gare", "DonnesAnnuelles"};
           for (String a : names) {
                String tableName = a;
                MenuItem item = new MenuItem(tableName);
                item.setOnAction(this::tableAction);
                tableButton.getItems().add(item);
           }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadTableView() {
        ArrayList<DefaultThing> def = new ArrayList<DefaultThing>();
        tableView.getItems().clear();
        valueText.setText("");

            switch (currentClasse) {
                case "modele.dao.DepartementDAO":
                    ArrayList<Departement> itemsArrayListDepartement = new DepartementDAO().findAll(c);
                    for (Departement q : itemsArrayListDepartement) {
                        def.add(new DefaultThing(q));
                    }
                    break;

                case "modele.dao.CommuneDAO":
                    ArrayList<Commune> itemsArrayListCommune = new CommuneDAO().findAll(c);
                    for (Commune q : itemsArrayListCommune) {
                        def.add(new DefaultThing(q));
                    }
                    break;
                case "modele.dao.GareDAO":
                    ArrayList<Gare> itemsArrayListGare = new GareDAO().findAll(c);
                    for (Gare q : itemsArrayListGare) {
                        def.add(new DefaultThing(q));
                    }
                    break;
                case "modele.dao.AeroportDAO":
                    ArrayList<Aeroport> itemsArrayListAer = new AeroportDAO().findAll(c);
                    for (Aeroport q : itemsArrayListAer) {
                        def.add(new DefaultThing(q));
                    }
                    break;
                case "modele.dao.AnneeDAO":
                    ArrayList<Annee> itemsArrayListAnnee = new AnneeDAO().findAll(c);
                    for (Annee q : itemsArrayListAnnee) {
                        def.add(new DefaultThing(q));
                    }
                    break;
                case "modele.dao.DonneesAnnuelles":
                    ArrayList<DonneesAnnuelles> itemsArrayListDonneesAnnuelles = new DonneesAnnuellesDAO().findAll(c);
                    for (DonneesAnnuelles q : itemsArrayListDonneesAnnuelles) {
                        def.add(new DefaultThing(q));
                    }
                    break;
            }
            ObservableList<DefaultThing> items = FXCollections.observableArrayList(def);
            tableView.setItems(items);
    }

    /**
     * Permet de revenir sur la vue précédente
     * @param event L'ActionEvent reçu lorsqu'on clique sur la flèche de retour à la vue précédente
     */
    @FXML
    void handleBackButton(ActionEvent event) {
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        String username = (String) stage.getProperties().get("Username");
        WelcomeScene.loadScene(stage, username);
    }

    /**
     * Permet d'éditer la valeur d'une instance existante
     */
    @FXML
    void handleEditButton() {
        String[] txt = valueText.getText().split("§");
        switch (currentClasse) {
            case "modele.dao.DepartementDAO":
                new DepartementDAO().update(c,Integer.parseInt(txt[0]), new Departement(Integer.parseInt(txt[0]), txt[1], Float.parseFloat(txt[2])));
                break;
            case "modele.dao.CommuneDAO":
                new CommuneDAO().update(c,Integer.parseInt(txt[0]), new Commune(Integer.parseInt(txt[0]), txt[1], new DepartementDAO().findByID(c, Long.parseLong(txt[2]))));
                break;
            case "modele.dao.GareDAO":
                new GareDAO().update(c,Integer.parseInt(txt[0]), new Gare(Integer.parseInt(txt[0]), txt[1], Boolean.parseBoolean(txt[2]), Boolean.parseBoolean(txt[3]), new CommuneDAO().findByID(c, Long.parseLong(txt[4]))));
                break;
            case "modele.dao.AeroportDAO":
                new AeroportDAO().update(c,txt[0], new Aeroport(txt[0], txt[1], new DepartementDAO().findByID(c, Long.parseLong(txt[2]))));
                break;
            case "modele.dao.AnneeDAO":
                new AnneeDAO().update(c,Integer.parseInt(txt[0]), new Annee(Integer.parseInt(txt[0]), Float.parseFloat(txt[1])));
                break;
            case "modele.dao.DonneesAnnuelleDAO":
                new AnneeDAO().update(c,Integer.parseInt(txt[0]), new Annee(Integer.parseInt(txt[0]), Float.parseFloat(txt[1])));
                break;
        }
        loadTableView();
    }

    /**
     * Permet d'effacer une instance existante
     */
    @FXML
    void handleDeleteButton() {
        String[] txt = valueText.getText().split("§");
        switch (currentClasse) {
        case "modele.dao.DepartementDAO":
            new DepartementDAO().delete(c, new DepartementDAO().findByID(c,Long.parseLong(txt[0])));
            break;
        case "modele.dao.CommuneDAO":
            new CommuneDAO().delete(c,new CommuneDAO().findByID(c,Long.parseLong(txt[0])));
            break;
        case "modele.dao.GareDAO":
            new GareDAO().delete(c,new GareDAO().findByID(c, Long.parseLong(txt[0])));
            break;
        case "modele.dao.AeroportDAO":
            new AeroportDAO().delete(c, new AeroportDAO().getFromName(c, txt[0]));
            break;
        case "modele.dao.AnneeDAO":
            new AnneeDAO().delete(c, new AnneeDAO().getFromId(c, Long.parseLong(txt[0])));
            break;
        }
        loadTableView();
    }

    /**
     * Permet d'ajouter une nouvelle instance
     */
    @FXML
    void handleAddButton() {
        String[] txt = valueText.getText().split("§");
        switch (currentClasse) {
        case "modele.dao.DepartementDAO":
            new DepartementDAO().create(c,new Departement(Integer.parseInt(txt[0]), txt[1], Float.parseFloat(txt[0])));
            break;
        case "modele.dao.CommuneDAO":
            new CommuneDAO().create(c,new Commune(Integer.parseInt(txt[0]), txt[1], new DepartementDAO().findByID(c,Long.parseLong(txt[2]))));
            break;
        case "modele.dao.GareDAO":
            new GareDAO().create(c,new Gare(Integer.parseInt(txt[0]), txt[1], Boolean.parseBoolean(txt[2]), Boolean.parseBoolean(txt[3]), new CommuneDAO().findByID(c,Long.parseLong(txt[4]))));
            break;
        case "modele.dao.AnneeDAO":
            new AnneeDAO().create(c, new Annee(Integer.parseInt(txt[0]), Float.parseFloat(txt[1])));
            break;
        }
        loadTableView();
    }

}
