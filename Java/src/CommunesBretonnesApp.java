import javafx.application.Application;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.scenes.LoginScene;

public class CommunesBretonnesApp extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Gestion des communes bretonnes");

        LoginScene.loadScene(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
