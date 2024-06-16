package view.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeScene implements UIScenes{
    public static void loadScene(Stage stage) {
        System.out.println("Loading WelcomeScene");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WelcomeScene.class.getResource("../../welcome_screen.fxml"));
            BorderPane pane = loader.load();

            Scene scene = new Scene(pane);
            stage.setScene(scene);
            System.out.println("loaded");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
