package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import modele.dao.CommuneDAO;
import modele.dao.DonneesAnnuellesDAO;
import modele.data.Commune;
import modele.data.DonneesAnnuelles;
import utils.FontOptimizer;
import utils.PythonLauncher;
import view.scenes.WelcomeScene;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.File;
import java.sql.Connection;

/**
 * Contrôleur de la page d'analyse des données pour une commune
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class StatsController {

    /** Quantité d'appartements */
    @FXML
    private Text appartText;

    /** Surface moyenne */
    @FXML
    private Text avgAreaText;

    /** Prix moyen */
    @FXML
    private Text avgText;

    /** Prix moyen au mètre carré */
    @FXML
    private Text avgm2Text;

    /** Budget de la commune */
    @FXML
    private Text budgetText;

    /** Numéro de la commune */
    @FXML
    private Text communeText;

    /** Dépenses culturelles de la commune */
    @FXML
    private Text culturalText;

    /** ImagePane où l'on stocke les graphes générés */
    @FXML
    private ImageView imagePane;

    /** Quantité de maisons */
    @FXML
    private Text maisonsText;

    /** Quantité de population */
    @FXML
    private Text popText;

    /** Zone d'input des arguments pour la troisième visualisation */
    @FXML
    private TextField vis3Arg;

    /** Zone d'input des arguments pour la quatrième visualisation */
    @FXML
    private TextField vis4arg;

    /** Zone d'input des arguments pour la cinquième visualisation */
    @FXML
    private TextField vis5Arg;

    /** Zone d'input des arguments pour la sixième visualisation */
    @FXML
    private TextField vis6Arg;

    /** Bouton permettant de choisir l'année des données qu'on visualise */
    @FXML
    private MenuButton yearButton;

    /**
     * Renvoie vers la scène précédente
     * @param event L'ActionEvent reçu lorsqu'on clique sur la flèche de retour à la scène précédente
     */
    @FXML
    void handleBack(ActionEvent event) {
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        String username = (String) stage.getProperties().get("Username");
        WelcomeScene.loadScene(stage, username);
    }

    /**
     * Gère le changement des données affichées lorsqu'on change d'année avec le bouton de sélection des années
     * @param event L'ActionEvent lorsqu'on sélectionne une année avec le bouton de sélection des années
     */
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

    /**
     * Gère le chargement de la scène
     * @param commune La commune sur laquelle on va faire des visualisations
     */
    public void handleLoad(String commune) {
        this.communeText.setText(commune);
        communeText.setStyle("-fx-font-size: "+ FontOptimizer.getOptimizedFontSize(commune, 80, 50)+"px;");
    }

    /**
     * Programme associé à l'envoi d'un message à python pour la première visualisation
     * @param event L'ActionEvent reçu lorsqu'on demande la première visualisation
     */
    @FXML
    void vis1(ActionEvent event) {
        String workingDirectory = System.getProperty("user.dir");
        System.out.println("Working Directory: " + workingDirectory);
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
        String imagePath = new File("javafx_visualization.png").getAbsolutePath();
        Image viz = new Image("file:"+imagePath);
        imagePane.setImage(viz);

        /*
        imagePane.setStyle(null);
        imagePane.getStyleClass().clear();
        imagePane.getStyleClass().addAll("stats_image");
        imagePane.setStyle("-fx-background-image: url(\"/javafx_visualization.png\");");
         */

    }

    /**
     * Programme associé à l'envoi d'un message à python pour la deuxième visualisation
     * @param event L'ActionEvent reçu lorsqu'on demande la deuxième visualisation
     */
    @FXML
    void vis2(ActionEvent event) {
        imagePane.setImage(new Image("/resources/assets/loading.png"));
        try {
            PythonLauncher.launch("visualizations.py", new String[]{"2"});
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        imagePane.setImage(null);
        String imagePath = new File("javafx_visualization.png").getAbsolutePath();
        Image viz = new Image("file:"+imagePath);
        imagePane.setImage(viz);
    }

    /**
     * Programme associé à l'envoi d'un message à python pour la troisième visualisation
     * @param event L'ActionEvent reçu lorsqu'on demande la troisième visualisation
     */
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
            String imagePath = new File("javafx_visualization.png").getAbsolutePath();
            Image viz = new Image("file:"+imagePath);
            imagePane.setImage(viz);
        } else {
            imagePane.setImage(null);
            imagePane.setImage(new Image("/resources/assets/wrong.png"));
        }
    }

    /**
     * Programme associé à l'envoi d'un message à python pour la quatrième visualisation
     * @param event L'ActionEvent reçu lorsqu'on demande la quatrième visualisation
     */
    @FXML
    void vis4(ActionEvent event) {
        imagePane.setImage(new Image("/resources/assets/loading.png"));

        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        Connection c = (Connection) stage.getProperties().get("Connection");
        Commune commune = new CommuneDAO().findByName(c, communeText.getText());


        int yArg = Integer.parseInt(vis4arg.getText());

        if (yArg >= 1) {
            try {
                PythonLauncher.launch("visualizations.py", new String[]{"4", Integer.toString(commune.getIdCommune()), vis4arg.getText()});
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            imagePane.setImage(null);
            String imagePath = new File("javafx_visualization.png").getAbsolutePath();
            Image viz = new Image("file:"+imagePath);
            imagePane.setImage(viz);
        } else {
            imagePane.setImage(null);
            imagePane.setImage(new Image("/resources/assets/wrong.png"));
        }
    }

    /**
     * Programme associé à l'envoi d'un message à python pour la cinquième visualisation
     * @param event L'ActionEvent reçu lorsqu'on demande la cinquième visualisation
     */
    @FXML
    void vis5(ActionEvent event) {
        imagePane.setImage(new Image("/resources/assets/loading.png"));

        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        Connection c = (Connection) stage.getProperties().get("Connection");

        int yArg = Integer.parseInt(vis5Arg.getText());

        if (yArg >= 1 ) {
            try {
                PythonLauncher.launch("visualizations.py", new String[]{"5", vis5Arg.getText()});
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            imagePane.setImage(null);
            String imagePath = new File("javafx_visualization.png").getAbsolutePath();
            Image viz = new Image("file:"+imagePath);
            imagePane.setImage(viz);
        } else {
            imagePane.setImage(null);
            imagePane.setImage(new Image("/resources/assets/wrong.png"));
        }

    }

    /**
     * Programme associé à l'envoi d'un message à python pour la sixième visualisation
     * @param event L'ActionEvent reçu lorsqu'on demande la sixième visualisation
     */
    @FXML
    void vis6(ActionEvent event) {
        imagePane.setImage(new Image("/resources/assets/loading.png"));

        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        Connection c = (Connection) stage.getProperties().get("Connection");
        Commune commune = new CommuneDAO().findByName(c, communeText.getText());

        int yArg = Integer.parseInt(vis6Arg.getText());

        if (yArg >= 1) {
            try {
                PythonLauncher.launch("visualizations.py", new String[]{"6", vis6Arg.getText(), Integer.toString(commune.getIdCommune())});
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            imagePane.setImage(null);
            String imagePath = new File("javafx_visualization.png").getAbsolutePath();
            Image viz = new Image("file:"+imagePath);
            imagePane.setImage(viz);
        } else {
            imagePane.setImage(null);
            imagePane.setImage(new Image("/resources/assets/wrong.png"));
        }

    }

}
