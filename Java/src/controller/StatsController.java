/**
 * Sample Skeleton for 'stats_1.fxml' Controller Class
 */

package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import modele.classesDAO.CommuneDAO;
import modele.classesDAO.DonneesAnnuellesDAO;
import modele.classesModele.Commune;
import modele.classesModele.DonneesAnnuelles;
import view.scenes.WelcomeScene;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.sql.Connection;

public class StatsController {

    @FXML // fx:id="appartText"
    private Text appartText; // Value injected by FXMLLoader

    @FXML // fx:id="avgAreaText"
    private Text avgAreaText; // Value injected by FXMLLoader

    @FXML // fx:id="avgText"
    private Text avgText; // Value injected by FXMLLoader

    @FXML // fx:id="avgm2Text"
    private Text avgm2Text; // Value injected by FXMLLoader

    @FXML // fx:id="budgetText"
    private Text budgetText; // Value injected by FXMLLoader

    @FXML // fx:id="culturalText"
    private Text culturalText; // Value injected by FXMLLoader

    @FXML // fx:id="maisonsText"
    private Text maisonsText; // Value injected by FXMLLoader

    @FXML // fx:id="popText"
    private Text popText; // Value injected by FXMLLoader

    @FXML
    private Text communeText;

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
        System.out.println(event.getSource().getClass());
        Stage stage = (Stage) (((MenuItem) event.getSource()).getParentPopup().getOwnerWindow());
        Connection c = (Connection) stage.getProperties().get("Connection");
        Commune commune = new CommuneDAO().findByName(c, communeText.getText());

        int annee = Integer.parseInt(((MenuItem) event.getSource()).getText());

        DonneesAnnuelles data = new DonneesAnnuellesDAO().findByCommuneAndYear(c, commune.getIdCommune(), annee);
        this.maisonsText.setText(Integer.toString(data.getNbMaison()));
        this.appartText.setText(Integer.toString(data.getNbAppart()));
        this.avgText.setText(Float.toString(data.getPrixMoyen()));
        this.avgm2Text.setText(Float.toString(data.getPrixM2Moyen()));
        this.culturalText.setText(Float.toString(data.getDepensesCulturellesTotales()));
        this.budgetText.setText(Float.toString(data.getBudgetTotal()));
        this.popText.setText(Float.toString(data.getPopulation()));
    }

    public void handleLoad(String commune) {
        this.communeText.setText(commune);
    }

}
