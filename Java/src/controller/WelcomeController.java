package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import modele.classesDAO.UserDAO;
import modele.classesModele.Commune;
import view.scenes.LoginScene;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class WelcomeController {
    @FXML
    private Button logoutButton;

    @FXML
    private ListView<String> communesList;

    @FXML
    void handleLogout(ActionEvent event) {
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        try {
            Connection c = (Connection) stage.getProperties().get("Connection");
            c.close();
            LoginScene.loadScene(stage);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    @FXML
    public void handleLoad(Connection c, String username) {
        ArrayList<Commune> communes = UserDAO.getAccesCommunes(c, username);
        ObservableList<String> entries = FXCollections.observableArrayList();
        for(Commune commune: communes) {
            entries.add(commune.getNomCommune());
        }
        communesList.setItems(entries);
    }

    public static int detectCommunes(Connection c, String username) {
        int res;
        if (username.equals("root")) {
            res = 2;
        } else {
            res = UserDAO.getAccesCommunes(c, username).size();
        }
        return res;
    }
}
