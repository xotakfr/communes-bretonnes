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
import modele.classesModele.Departement;
import utils.ResultSetTableView;
import view.scenes.WelcomeScene;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;

public class SettingsController {

    @FXML
    private MenuButton tableButton;

    @FXML
    private TableView tableView;

    @FXML
    void tableAction(ActionEvent event) {
        Stage stage = (Stage) (((MenuItem) event.getSource()).getParentPopup().getOwnerWindow());
        Connection c = (Connection) stage.getProperties().get("Connection");

        String table = ((MenuItem) event.getSource()).getText();
        try {
            System.out.println(table);
            Statement statement = c.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM " + table + ";");
            tableView = new ResultSetTableView(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            Class<?> classeDAO = Class.forName("modele.classesDAO."+table+"DAO");
            String className = classeDAO.getName();
            DAO<?> instanceDAO =  (DAO<?>) classeDAO.getDeclaredConstructor().newInstance();
            ArrayList<?> itemsArrayList = instanceDAO.findAll(c);
            ObservableList<?> items = FXCollections.observableArrayList(itemsArrayList);
            System.out.println(items);
            tableView = new TableView<>(items);
            switch (className) {
                case "modele.classesDAO.DepartementDAO":
                    System.out.println("j'ai reussi");
                    // departementTableAction(itemsArrayList);
            }
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
