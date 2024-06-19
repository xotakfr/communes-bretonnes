package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.scenes.WelcomeScene;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Contrôleur de la page de connexion
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class LoginController {

    /** Le Field où le mot de passe de l'utilisateur sera écrit */
    @FXML
    private PasswordField passwordField;

    /** Le Field où l'username de l'utilisateur sera écrit */
    @FXML
    private TextField usernameField;

    /** Le message qui sera affiché dans le cas d'une erreur */
    @FXML
    private Text errorMessage;


    /**
     * Gère la connexion de l'utilisateur à la base de données puis charge la scène suivante
     * @param event L'ActionEvent reçu quand l'utilisateur clique sur le bouton de connexion
     */
    public void handle(ActionEvent event) {
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost/bdSAE", usernameField.getText(), passwordField.getText());
            stage.getProperties().put("Connection", c);
            stage.getProperties().put("Username", usernameField.getText());
            WelcomeScene.loadScene(stage,usernameField.getText());
        } catch (SQLException e) {
            errorMessage.setText("Echec de la connection, vérifiez vos identifiants");
            System.out.println(e.getMessage());
        }


    }
}
