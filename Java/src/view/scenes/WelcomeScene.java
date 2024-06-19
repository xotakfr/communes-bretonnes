package view.scenes;

import controller.SingleWelcomeController;
import controller.WelcomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

/**
 * Implémente UIScenes et charge la scène correspondant au menu principal.
 * La scène utilisée pour le menu principal dépend des permissions de l'utilisateur.
 * @see UIScenes
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class WelcomeScene implements UIScenes {
    /**
     * Récupère la scène associée au menu principal de l'application et l'applique
     * au stage de l'application.
     * En fonction des permissions de l'utilisateur, une scène différente est sélectionnée pour
     * le menu principal.
     * Les permissions sont détectées à l'aide de données stockées dans la BDD.
     * @param stage Le stage de l'application
     * @param username Le nom de l'utilisateur
     */
    public static void loadScene(Stage stage, String username) {
        BorderPane pane;
        try {
            FXMLLoader loader = new FXMLLoader();
            Connection c = (Connection) stage.getProperties().get("Connection");

            int number = WelcomeController.detectCommunes(c, username);
            if (number > 1) {
                loader.setLocation(WelcomeScene.class.getResource("/resources/welcome_screen.fxml"));
            } else {
                loader.setLocation(WelcomeScene.class.getResource("/resources/welcome_screen_single.fxml"));
            }
            pane = loader.load();

            Scene scene = new Scene(pane);
            stage.setScene(scene);
            if (number > 1) {
                WelcomeController controller = loader.getController();
                controller.handleLoad(c, username);
            } else {
                SingleWelcomeController controller = loader.getController();
                controller.handleLoad(c, username);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
