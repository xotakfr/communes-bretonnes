package view.scenes;

import controller.StatsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Implémente UIScenes et charge la scène correspondante à la page de visualisation des données d'une commune
 * @see UIScene
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class StatsScene implements UIScenes {

    /**
     * Récupère la scène associée à la page de visualisation des données d'une commune
     * @param stage Le stage de l'application
     * @param commune La commune concernée
     */
    public static void loadScene(Stage stage, String commune) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoginScene.class.getResource("../../stats_1.fxml"));
            BorderPane pane = loader.load();

            Scene scene = new Scene(pane);
            stage.setScene(scene);
            StatsController controller = loader.getController();
            controller.handleLoad(commune);
        } 
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
