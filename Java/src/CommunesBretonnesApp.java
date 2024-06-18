import javafx.application.Application;

import javafx.stage.Stage;
import view.scenes.LoginScene;

import java.sql.Connection;

public class CommunesBretonnesApp extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Gestion des communes bretonnes");

        LoginScene.loadScene(stage);
        this.primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
