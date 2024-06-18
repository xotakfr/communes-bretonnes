package view.scenes;

import controller.StatsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StatsScene {
    public static void loadScene(Stage stage, String commune) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoginScene.class.getResource("../../stats_1.fxml"));
            BorderPane pane = loader.load();

            Scene scene = new Scene(pane);
            stage.setScene(scene);
            StatsController controller = loader.getController();
            controller.handleLoad(commune);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
