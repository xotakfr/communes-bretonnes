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


public class LoginController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Text errorMessage;


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
