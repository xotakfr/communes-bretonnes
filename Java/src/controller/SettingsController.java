package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SettingsController {

    @FXML
    private MenuButton tableButton;

    @FXML
    void tableAction(ActionEvent event) {
        System.out.println("Bonjour, j'existe");
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        Connection c = (Connection) stage.getProperties().get("Connection");

        String table = ((MenuItem) event.getSource()).getText();
        System.out.println(table);
    }

    public void loadTableNames(Connection connection) {
        try {
            DatabaseMetaData dbmd = connection.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, "%", new String[]{"TABLE"});

            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                tableButton.getItems().add(new MenuItem(tableName));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
