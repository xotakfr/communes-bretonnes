import javafx.application.Application;
import javafx.stage.Stage;
import view.scenes.LoginScene;
import java.sql.Connection;

/**
 * Le point d'entrée de notre application, cette classe est la première à être exécutée
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class CommunesBretonnesApp extends Application {
    /** Le stage de l'application */
    private Stage primaryStage;

    /**
     * La méthode qui va créer le stage de l'application et permettre le début de l'exécution
     * de celle-ci
     * @param stage Le stage de l'application
     * @throws Exception Si une erreur quelconque s'ensuit dans le programme et n'est pas attrapée 
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Gestion des communes bretonnes");

        LoginScene.loadScene(stage);
        this.primaryStage.show();
    }

    /**
     * Le point d'entrée de notre programme
     * @param args Les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        launch(args);
    }
}
