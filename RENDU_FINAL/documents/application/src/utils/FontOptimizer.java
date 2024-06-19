package utils;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Permet d'optimiser la taille du texte affiché dans notre application
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class FontOptimizer {

    /**
     * Trouve la taille optimale pour du texte
     * @param text Le texte
     * @param width La largeur du texte
     * @param height La longueur du texte
     * @return
     */
    public static double getOptimizedFontSize(String text, double width, double height) {
        // Commence par une petite taille de police d'écriture (minimum)
        double fontSize = 2;

        Text textObject = new Text(text);
        
        // On boucle, ju'squ'à trouver la plus grande taille possible
        while (true) {
            // changement de taille et mise à jour
            textObject.setFont(Font.font(fontSize));
            
            javafx.geometry.Bounds bounds = textObject.getLayoutBounds();

            if (bounds.getWidth() <= width && bounds.getHeight() <= height) {
                fontSize += 0.5;
            } else {
                fontSize -= 0.5;
                break;
            }
        }
        return fontSize;
    }
}
