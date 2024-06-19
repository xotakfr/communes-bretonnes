package view.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Implémente UIScenes et charge la scène correspondant au menu de connexion.
 * @see UIScenes 
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class LoginScene implements UIScenes {

    /**
     * Récupère la scène associée au menu de connexion de l'application et
     * l'applique au stage de l'application
     * @param stage Le stage de l'application
     */
    public static void loadScene(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoginScene.class.getResource("/resources/login_screen.fxml"));
            GridPane pane = loader.load();
            Scene scene = new Scene(pane);
            stage.setScene(scene);
        } 
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

