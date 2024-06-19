package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modele.dao.UserDAO;
import modele.data.Commune;
import view.scenes.LoginScene;
import view.scenes.SettingsScene;
import view.scenes.StatsScene;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Contrôleur de la page principale pour les utilisateurs ayant accès à plusieurs communes
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class WelcomeController {

    /** Bouton de déconnexion */
    @FXML
    private Button logoutButton;

    /** Liste des communes que l'utilisateur a accès à */
    @FXML
    private ListView<String> communesList;

    /** Texte d'accueil */
    @FXML
    private Text nameText;

    /**
     * Renvoie vers la scène précédente et ferme la connexion à la base de données
     * @param event L'ActionEvent reçu lorsqu'on clique sur la flèche de déconnexion
     */
    @FXML
    void handleLogout(ActionEvent event) {
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        try {
            Connection c = (Connection) stage.getProperties().get("Connection");
            c.close();
            LoginScene.loadScene(stage);
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gère le chargement de la scène
     * @param c La connexion à la base de données
     * @param username L'username de l'utilisateur
     */
    @FXML
    public void handleLoad(Connection c, String username) {
        ArrayList<Commune> communes = UserDAO.getAccesCommunes(c, username);
        ObservableList<String> entries = FXCollections.observableArrayList();
        for(Commune commune: communes) {
            entries.add(commune.getNomCommune());
        }
        communesList.setItems(entries);
        String nom = UserDAO.getName(c, username);
        nameText.setText("Bienvenue " + nom);
    }

    /**
     * Permet de trouver l'ensemble des communes que l'utilisateur peut accèder
     * @param c La connexion à la base de données
     * @param username L'username de l'utilisateur
     * @return La liste des communes auxquelles l'utilisateur a accès à
     */
    public static int detectCommunes(Connection c, String username) {
        int res;
        if (username.equals("root")) {
            res = 2;
        } else {
            res = UserDAO.getAccesCommunes(c, username).size();
        }
        return res;
    }

    /**
     * Gère la redirection vers la scène d'analyse des données de la commune sélectionnée
     * @param event L'ActionEvent reçu lorsqu'on clique le nom d'une des communes disponible
     */
    @FXML
    void handleSelection(MouseEvent event) {
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        StatsScene.loadScene(stage, this.communesList.getSelectionModel().getSelectedItem());
    }

    /**
     * Gère la redirection vers la scène de paramètrage de l'application
     * @param event L'ActionEvent reçu lorsqu'on clique sur le bouton de paramètrage
     */
    @FXML
    void handleSettings(ActionEvent event) {
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        SettingsScene.loadScene(stage);
    }
}
