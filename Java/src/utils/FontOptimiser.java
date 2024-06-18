package utils;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FontOptimizer {

    public static double getOptimizedFontSize(String text, double width, double height) {
        // Commence par une petite taille de police d'écriture (minimum)
        double fontSize = 2;

        Text textObject = new Text(text);
        
        // On boucle, ju'squ'à trouver la plus grande taille possible
        while (true) {
            // changement de taille et mise à jour
            textObject.setFont(Font.font(fontSize));
            
            javafx.geometry.Bounds bounds = textNode.getLayoutBounds();

            if (bounds.getWidth() <= width && bounds.getHeight() <= height) {
                fontSize += 0.5;
            } else {
                fontSize -= 0.5;
                break;
            }
        }
        return fontSize;
    }

    public static void main(String[] args) {
        String text = "Ceci est un texte";
        double width = 50;
        double height = 150;
        
        double optimizedFontSize = getOptimizedFontSize(text, width, height);
        System.out.println("Taille proposée : " + optimizedFontSize);
    }
}
