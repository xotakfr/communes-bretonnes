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

/**
 * Contrôleur de la page principale pour les utilisateurs ayant accès à une seule commune
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class SingleWelcomeController {

    /** Bouton amenant vers la page d'analyse pour la commune */
    @FXML
    private Button communeButton;

    /** Bouton de déconnexion */
    @FXML
    private Button logoutButton;

    /** Tout autre bouton */
    @FXML
    private Button subButton;

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
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    /**
     * Permet de charger la scène actuelle
     * @param event L'ActionEvent reçu lorsqu'on arrive dans la scène
     */
    @FXML
    void loadData(ActionEvent event) {
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        StatsScene.loadScene(stage, communeButton.getText());
    }

    /**
     * Permet de créer le nom de l'utilisateur en utilisant les données reçues de la base de données
     * @param c La connexion à la base de données
     * @param username L'username de l'utilisateur
     */
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
