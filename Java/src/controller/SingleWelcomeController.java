package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modele.classesDAO.UserDAO;
import modele.classesModele.Commune;
import view.scenes.LoginScene;
import view.scenes.StatsScene;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SingleWelcomeController {

    @FXML
    private Button communeButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button subButton;

    @FXML
    private Text nameText;

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
    void loadData(ActionEvent event) {
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        StatsScene.loadScene(stage, communeButton.getText());
    }

    public void handleLoad(Connection c, String username) {
        String nom = UserDAO.getName(c, username);
        System.out.println(nom);
        nameText.setText("Bienvenue " + nom);

        ArrayList<Commune> communes = UserDAO.getAccesCommunes(c, username);
        Commune commune = communes.get(0);
        String nomCommune = commune.getNomCommune();
        communeButton.setText(nomCommune);
        subButton.setText(nomCommune.substring(0, 1));
    }

}
