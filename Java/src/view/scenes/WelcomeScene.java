package view.scenes;

import controller.WelcomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class WelcomeScene implements UIScenes{
    public static void loadScene(Stage stage, String username) {
        BorderPane pane;
        try {
            FXMLLoader loader = new FXMLLoader();
            Connection c = (Connection) stage.getProperties().get("Connection");

            int number = WelcomeController.detectCommunes(c, username);
            if (number > 1) {
                loader.setLocation(WelcomeScene.class.getResource("../../welcome_screen.fxml"));
                System.out.println("Regular");
            } else {
                loader.setLocation(WelcomeScene.class.getResource("../../welcome_screen_single.fxml"));
                System.out.println("Single");
            }
            pane = loader.load();

            Scene scene = new Scene(pane);
            stage.setScene(scene);
            if (number > 1) {
                System.out.println("More than 1");
                WelcomeController controller = loader.getController();
                controller.handleLoad(c, username);
            } else {
                // TODO: WelcomeControllerSingle.handleLoad(scene, username)
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
