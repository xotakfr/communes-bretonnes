package view.scenes;

import javafx.stage.Stage;

/**
 * Interface pour les classes de chargement de scènes.
 * Elle définit la signature d'une méthode permettant de modifier la scène
 * du stage de l'application en récupérant un fichier .fxml
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public interface UIScenes {
    /**
     * Signature de la méthode utilisée par récupérer une scène et l'appliquée au stage
     * @param stage Le stage de l'application
     */
    static void loadScene(Stage stage) {}
}
