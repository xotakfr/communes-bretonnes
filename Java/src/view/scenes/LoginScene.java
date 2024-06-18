package view.scenes;

import controller.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class LoginScene implements UIScenes {

    public static void loadScene(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoginScene.class.getResource("../../login_screen.fxml"));
            GridPane pane = loader.load();

            Scene scene = new Scene(pane);
            stage.setScene(scene);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

