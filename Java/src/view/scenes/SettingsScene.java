package view.scenes;

import controller.SettingsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class SettingsScene implements UIScenes{
    public static void loadScene(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoginScene.class.getResource("../../settings.fxml"));
            BorderPane pane = loader.load();
            Scene scene = new Scene(pane);
            stage.setScene(scene);

            Connection c = (Connection) stage.getProperties().get("Connection");

            SettingsController controller = loader.getController();
            controller.loadTableNames(c);

        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
