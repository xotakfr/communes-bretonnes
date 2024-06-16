package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.scenes.WelcomeScene;


public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    public void handle(ActionEvent event) {
        System.out.println("Event triggered");
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        WelcomeScene.loadScene(stage);

    }
}
