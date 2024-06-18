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

/**
 * Implémente UIScenes et charge la scène correspondant au menu de connexion.
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 * @see UIScenes 
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
            loader.setLocation(LoginScene.class.getResource("../../login_screen.fxml"));
            GridPane pane = loader.load();
            Scene scene = new Scene(pane);
            stage.setScene(scene);
        } 
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

