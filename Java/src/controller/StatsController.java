/**
 * Sample Skeleton for 'stats_1.fxml' Controller Class
 */

package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import modele.classesDAO.CommuneDAO;
import modele.classesDAO.DonneesAnnuellesDAO;
import modele.classesModele.Commune;
import modele.classesModele.DonneesAnnuelles;
import utils.FontOptimizer;
import utils.PythonLauncher;
import view.scenes.WelcomeScene;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.sql.Connection;

public class StatsController {


    @FXML
    private Text appartText;

    @FXML
    private Text avgAreaText;

    @FXML
    private Text avgText;

    @FXML
    private Text avgm2Text;

    @FXML
    private Text budgetText;

    @FXML
    private Text communeText;

    @FXML
    private Text culturalText;

    @FXML
    private ImageView imagePane;

    @FXML
    private Text maisonsText;

    @FXML
    private Text popText;

    @FXML
    private TextField vis3Arg;

    @FXML
    private TextField vis4arg;

    @FXML
    private TextField vis5Arg;

    @FXML
    private TextField vis6Arg;

    @FXML
    private MenuButton yearButton;


    @FXML
    void handleBack(ActionEvent event) {
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        String username = (String) stage.getProperties().get("Username");
        WelcomeScene.loadScene(stage, username);
    }

    @FXML
    void handleYearChange(ActionEvent event) {
        Stage stage = (Stage) (((MenuItem) event.getSource()).getParentPopup().getOwnerWindow());
        Connection c = (Connection) stage.getProperties().get("Connection");
        Commune commune = new CommuneDAO().findByName(c, communeText.getText());

        int annee = Integer.parseInt(((MenuItem) event.getSource()).getText());

        DonneesAnnuelles data = new DonneesAnnuellesDAO().findByCommuneAndYear(c, commune.getIdCommune(), annee);
        this.maisonsText.setText(Integer.toString(data.getNbMaison()));
        this.appartText.setText(Integer.toString(data.getNbAppart()));
        this.avgText.setText(Float.toString(data.getPrixMoyen()));
        this.avgm2Text.setText(Float.toString(data.getPrixM2Moyen()));
        this.avgAreaText.setText(Float.toString(data.getSurfaceMoyenne()));
        if (data.getDepensesCulturellesTotales() == -1) {
            this.culturalText.setText("Données manquantes");
        } else {
            this.culturalText.setText(Float.toString(data.getDepensesCulturellesTotales()));
        }
        if (data.getBudgetTotal() == -1) {
            this.budgetText.setText("Données manquantes");
        } else {
            this.budgetText.setText(Float.toString(data.getBudgetTotal()));
        }
        if (data.getPopulation() == -1) {
            this.popText.setText("Données manquantes");
        } else {
            this.popText.setText(Float.toString(data.getPopulation()));
        }
    }

    public void handleLoad(String commune) {
        this.communeText.setText(commune);
        communeText.setStyle("-fx-font-size: "+ FontOptimizer.getOptimizedFontSize(commune, 80, 50)+"px;");
    }

    @FXML
    void vis1(ActionEvent event) {
        imagePane.setImage(new Image("/resources/assets/loading.png"));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        // imagePane.setStyle("-fx-background-image: url(\"/resources/assets/loading.png\");");
        try {
            PythonLauncher.launch("visualizations.py", new String[]{"1"});
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        imagePane.setImage(null);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        imagePane.setImage(new Image("/javafx_visualization.png"));

        /*
        imagePane.setStyle(null);
        imagePane.getStyleClass().clear();
        imagePane.getStyleClass().addAll("stats_image");
        imagePane.setStyle("-fx-background-image: url(\"/javafx_visualization.png\");");
         */

    }

    @FXML
    void vis2(ActionEvent event) {
        imagePane.setImage(new Image("/resources/assets/loading.png"));
        try {
            PythonLauncher.launch("visualizations.py", new String[]{"2"});
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        imagePane.setImage(null);
        imagePane.setImage(new Image("/javafx_visualization.png"));
    }

    @FXML
    void vis3(ActionEvent event) {
        imagePane.setImage(new Image("/resources/assets/loading.png"));

        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        Connection c = (Connection) stage.getProperties().get("Connection");
        Commune commune = new CommuneDAO().findByName(c, communeText.getText());

        int yArg = Integer.parseInt(vis3Arg.getText());

        if (yArg >= 1 ) {
            try {
                PythonLauncher.launch("visualizations.py", new String[]{"3", Integer.toString(commune.getIdCommune()), vis3Arg.getText()});
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            imagePane.setImage(null);
            imagePane.setImage(new Image("/javafx_visualization.png"));
        } else {
            imagePane.setImage(null);
            imagePane.setImage(new Image("/resources/assets/wrong.png"));
        }
    }

    @FXML
    void vis4(ActionEvent event) {
        imagePane.setImage(new Image("/resources/assets/loading.png"));

        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        Connection c = (Connection) stage.getProperties().get("Connection");
        Commune commune = new CommuneDAO().findByName(c, communeText.getText());


        int yArg = Integer.parseInt(vis3Arg.getText());

        if (yArg >= 1) {
            try {
                PythonLauncher.launch("visualizations.py", new String[]{"4", Integer.toString(commune.getIdCommune()), vis4arg.getText()});
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            imagePane.setImage(null);
            imagePane.setImage(new Image("/javafx_visualization.png"));
        } else {
            imagePane.setImage(null);
            imagePane.setImage(new Image("/resources/assets/wrong.png"));
        }
    }

    @FXML
    void vis5(ActionEvent event) {
        imagePane.setImage(new Image("/resources/assets/loading.png"));

        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        Connection c = (Connection) stage.getProperties().get("Connection");

        int yArg = Integer.parseInt(vis3Arg.getText());

        if (yArg >= 1 ) {
            try {
                PythonLauncher.launch("visualizations.py", new String[]{"5", vis5Arg.getText()});
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            imagePane.setImage(null);
            imagePane.setImage(new Image("/javafx_visualization.png"));
        } else {
            imagePane.setImage(null);
            imagePane.setImage(new Image("/resources/assets/wrong.png"));
        }

    }

    @FXML
    void vis6(ActionEvent event) {
        imagePane.setImage(new Image("/resources/assets/loading.png"));

        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        Connection c = (Connection) stage.getProperties().get("Connection");
        Commune commune = new CommuneDAO().findByName(c, communeText.getText());

        int yArg = Integer.parseInt(vis3Arg.getText());

        if (yArg >= 1) {
            try {
                PythonLauncher.launch("visualizations.py", new String[]{"6", vis6Arg.getText(), Integer.toString(commune.getIdCommune())});
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            imagePane.setImage(null);
            imagePane.setImage(new Image("/javafx_visualization.png"));
        } else {
            imagePane.setImage(null);
            imagePane.setImage(new Image("/resources/assets/wrong.png"));
        }

    }

}
