package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.scenes.LoginScene;
import view.scenes.WelcomeScene;

public class WelcomeController {
    @FXML
    private Button logoutButton;

    @FXML
    void handle(ActionEvent event) {
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        LoginScene.loadScene(stage);
    }
}
