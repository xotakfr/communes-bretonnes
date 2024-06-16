import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CommunesBretonnesApp extends Application {
    private Stage primaryStage;
    private GridPane grid;

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Gestion des communes bretonnes");

        loadLogin();
    }

    private void loadLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("login_screen.fxml"));
            grid = loader.load();

            Scene scene = new Scene(grid);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
