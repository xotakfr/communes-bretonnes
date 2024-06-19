package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modele.classesDAO.*;
import modele.classesModele.*;
import utils.ResultSetTableView;
import view.scenes.WelcomeScene;
import javafx.scene.Scene;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;

import modele.classesModele.DefaultThing;

public class SettingsController {

    @FXML
    private MenuButton tableButton;

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn<DefaultThing, String> col1;
    @FXML
    private TableColumn<DefaultThing, String> col2;
    @FXML
    private TableColumn<DefaultThing, String> col3;
    @FXML
    private TableColumn<DefaultThing, String> col4;
    @FXML
    private TableColumn<DefaultThing, String> col5;
    @FXML
    private TableColumn<DefaultThing, String> col6;
    @FXML
    private TableColumn<DefaultThing, String> col7;
    @FXML
    private TableColumn<DefaultThing, String> col8;
    @FXML
    private TableColumn<DefaultThing, String> col9;

    @FXML
    void tableAction(ActionEvent event) {
        Stage stage = (Stage) (((MenuItem) event.getSource()).getParentPopup().getOwnerWindow());
        Connection c = (Connection) stage.getProperties().get("Connection");

        col1.setCellValueFactory(new PropertyValueFactory<>("col1"));
        col2.setCellValueFactory(new PropertyValueFactory<>("col2"));
        col3.setCellValueFactory(new PropertyValueFactory<>("col3"));
        col4.setCellValueFactory(new PropertyValueFactory<>("col4"));
        col5.setCellValueFactory(new PropertyValueFactory<>("col5"));
        col6.setCellValueFactory(new PropertyValueFactory<>("col6"));
        col7.setCellValueFactory(new PropertyValueFactory<>("col7"));
        col8.setCellValueFactory(new PropertyValueFactory<>("col8"));
        col9.setCellValueFactory(new PropertyValueFactory<>("col9"));

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
            Class<?> classeDAO = Class.forName("modele.classesDAO."+table+"DAO");
            String className = classeDAO.getName();
            DAO<?> instanceDAO =  (DAO<?>) classeDAO.getDeclaredConstructor().newInstance();

            ArrayList<DefaultThing> def = new ArrayList<DefaultThing>();
            
            switch (className) {
                case "modele.classesDAO.DepartementDAO":
                    ArrayList<Departement> itemsArrayListDepartement = new DepartementDAO().findAll(c);
                    for (Departement q : itemsArrayListDepartement) {
                        def.add(new DefaultThing(q));
                    }
                case "modele.classesDAO.CommuneDAO":
                    ArrayList<Commune> itemsArrayListCommune = new CommuneDAO().findAll(c);
                    for (Commune q : itemsArrayListCommune) {
                        def.add(new DefaultThing(q));
                    }

            }
            ObservableList<DefaultThing> items = FXCollections.observableArrayList(def);
            System.out.println(items);
            tableView.setItems(items);

        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (InstantiationException e) {
            System.out.println(e.getMessage());
        }
        catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
        catch (InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
        catch (NoSuchMethodException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadTableNames(Connection connection) {
        try {
            DatabaseMetaData dbmd = connection.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, "%", new String[]{"TABLE"});

            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                MenuItem item = new MenuItem(tableName);
                item.setOnAction(this::tableAction);
                tableButton.getItems().add(item);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void handleBackButton(ActionEvent event) {
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        String username = (String) stage.getProperties().get("Username");
        WelcomeScene.loadScene(stage, username);
    }
}
