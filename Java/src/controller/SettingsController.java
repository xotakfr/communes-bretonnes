package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modele.classesModele.Departement;
import utils.ResultSetTableView;

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
        /*
        try {
            Class<?> classeDAO = Class.forName("modele.classesDAO."+table+"DAO");
            String className = classeDAO.getName();
            DAO<?> instanceDAO =  (DAO<?>) classeDAO.newInstance();
            ArrayList<?> itemsArrayList = instanceDAO.findAll(c);
            ObservableList<?> items = FXCollections.observableArrayList(itemsArrayList);
            System.out.println(items);
            tableView = new TableView<>(items);
            switch (className) {
                case "modele.classesDAO.DepartementDAO":
                    departementTableAction(itemsArrayList);
            }
        } catch (ClassNotFoundException e) {

        } catch (InstantiationException e) {

        } catch (IllegalAccessException e) {

        }

         */
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
}
