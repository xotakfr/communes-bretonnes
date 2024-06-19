package modele.data;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Permet de récupérer les valeurs pour chaque colonnes de chaque table de la base de données
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class DefaultThing {
    /** La première collone */
    public StringProperty col1;
    /** La deuxième colonne */
    public StringProperty col2;
    /** La troisième colonne */
    public StringProperty col3;
    /** La quatrième colonne */
    public StringProperty col4;
    /** La cinquième colonne */
    public StringProperty col5;
    /** La sixième colonne */
    public StringProperty col6;
    /** La septième colonne */
    public StringProperty col7;
    /** La huitième colonne */
    public StringProperty col8;
    /** La neuvième colonne */
    public StringProperty col9;

    /**
     * Constructeur par les départements
     * @param dep L'instance de type Departement
     */
    public DefaultThing(Departement dep) {
        this.col1 = new SimpleStringProperty(String.valueOf(dep.getIdDep()));
        this.col2 = new SimpleStringProperty(dep.getNomDep());
        this.col3 = new SimpleStringProperty(String.valueOf(dep.getInvesCulturel2019()));
        this.col4 = new SimpleStringProperty("");
        this.col5 = new SimpleStringProperty("");
        this.col6 = new SimpleStringProperty("");
        this.col7 = new SimpleStringProperty("");
        this.col8 = new SimpleStringProperty("");
        this.col9 = new SimpleStringProperty("");
    }

    /**
     * Constructeur pour les communes
     * @param com L'instance de type Commune
     */
    public DefaultThing(Commune com) {
        this.col1 = new SimpleStringProperty(String.valueOf(com.getIdCommune()));
        this.col2 = new SimpleStringProperty(com.getNomCommune());
        this.col3 = new SimpleStringProperty(String.valueOf(com.getLeDepartement().getIdDep()));
        this.col4 = new SimpleStringProperty("");
        this.col5 = new SimpleStringProperty("");
        this.col6 = new SimpleStringProperty("");
        this.col7 = new SimpleStringProperty("");
        this.col8 = new SimpleStringProperty("");
        this.col9 = new SimpleStringProperty("");
    }

    /**
     * Constructeur pour les gares
     * @param gare L'instance de type Gare
     */
    public DefaultThing(Gare gare) {
        this.col1 = new SimpleStringProperty(String.valueOf(gare.getCodeGare()));
        this.col2 = new SimpleStringProperty(gare.getNomGare());
        this.col3 = new SimpleStringProperty(String.valueOf(gare.getFretValue()));
        this.col4 = new SimpleStringProperty(String.valueOf(gare.getVoyageurValue()));
        this.col5 = new SimpleStringProperty(String.valueOf(gare.getLaCommune().getIdCommune()));
        this.col6 = new SimpleStringProperty("");
        this.col7 = new SimpleStringProperty("");
        this.col8 = new SimpleStringProperty("");
        this.col9 = new SimpleStringProperty("");
    }

    /**
     * Constructeur pour les aéroports
     * @param aer L'instance de type Aeroport
     */
    public DefaultThing(Aeroport aer) {
        this.col1 = new SimpleStringProperty(aer.getNom());
        this.col2 = new SimpleStringProperty(aer.getAdresse());
        this.col3 = new SimpleStringProperty(String.valueOf(aer.getLeDepartement().getIdDep()));
        this.col4 = new SimpleStringProperty("");
        this.col5 = new SimpleStringProperty("");
        this.col6 = new SimpleStringProperty("");
        this.col7 = new SimpleStringProperty("");
        this.col8 = new SimpleStringProperty("");
        this.col9 = new SimpleStringProperty("");
    }

    /**
     * Constructeur pour les années
     * @param ann L'instance de type Annee
     */
    public DefaultThing(Annee ann) {
        this.col1 = new SimpleStringProperty(String.valueOf(ann.getAnnee()));
        this.col2 = new SimpleStringProperty(String.valueOf(ann.getTauxInflation()));
        this.col3 = new SimpleStringProperty("");
        this.col4 = new SimpleStringProperty("");
        this.col5 = new SimpleStringProperty("");
        this.col6 = new SimpleStringProperty("");
        this.col7 = new SimpleStringProperty("");
        this.col8 = new SimpleStringProperty("");
        this.col9 = new SimpleStringProperty("");
    }

    /**
     * Constructeur pour les données annuelles
     * @param ann L'instance de type DonneesAnnuelles
     */
    public DefaultThing(DonneesAnnuelles ann) {
        this.col1 = new SimpleStringProperty(String.valueOf(ann.getAnnee().getAnnee()));
        this.col2 = new SimpleStringProperty(String.valueOf(ann.getCommune().getIdCommune()));
        this.col3 = new SimpleStringProperty(String.valueOf(ann.getNbMaison()));
        this.col4 = new SimpleStringProperty(String.valueOf(ann.getNbAppart()));
        this.col5 = new SimpleStringProperty(String.valueOf(ann.getPrixMoyen()));
        this.col6 = new SimpleStringProperty(String.valueOf(ann.getPrixM2Moyen()));
        this.col7 = new SimpleStringProperty(String.valueOf(ann.getSurfaceMoyenne()));
        this.col8 = new SimpleStringProperty(String.valueOf(ann.getDepensesCulturellesTotales()));
        this.col9 = new SimpleStringProperty(String.valueOf(ann.getBudgetTotal()));
        //this.col0 = new SimpleStringProperty(String.valueOf(ann.getPopulation()));
    }

    /**
     * Renvoie la première colonne
     * @return La première colonne
     */
    public String getCol1() {
        return col1.get();
    }

    /**
     * Renvoie la deuxième colonne
     * @return La deuxième colonne
     */
    public String getCol2() {
        return col2.get();
    }

    /**
     * Renvoie la troisième colonne
     * @return La troisième colonne
     */
    public String getCol3() {
        return col3.get();
    }

    /**
     * Renvoie la quatrième colonne
     * @return La quatrième colonne
     */
    public String getCol4() {
        return col4.get();
    }

    /**
     * Renvoie la cinquième colonne
     * @return La cinquième colonne
     */
    public String getCol5() {
        return col5.get();
    }

    /**
     * Renvoie la sixième colonne
     * @return La sixième colonne
     */
    public String getCol6() {
        return col6.get();
    }

    /**
     * Renvoie la septième colonne
     * @return La septième colonne
     */
    public String getCol7() {
        return col7.get();
    }

    /**
     * Renvoie la huitième colonne
     * @return La huitième colonne
     */
    public String getCol8() {
        return col8.get();
    }

    /**
     * Renvoie la neuvième colonne
     * @return La neuvième colonne
     */
    public String getCol9() {
        return col9.get();
    }

    /**
     * Renvoie les données de chaque colonne
     * @return Les données de chaque colonne
     */
    public String getAsData() {
        String s="";
        if (!getCol1().equals("")) {
            s+=getCol1();
        }
        if (!getCol2().equals("")) {
            s=s+"§"+getCol2();
        }
        if (!getCol3().equals("")) {
            s=s+"§"+getCol3();
        }
        if (!getCol4().equals("")) {
            s=s+"§"+getCol4();
        }
        if (!getCol5().equals("")) {
            s=s+"§"+getCol5();
        }
        if (!getCol6().equals("")) {
            s=s+"§"+getCol6();
        }
        if (!getCol7().equals("")) {
            s=s+"§"+getCol7();
        }
        if (!getCol8().equals("")) {
            s=s+"§"+getCol8();
        }
        if (!getCol9().equals("")) {
            s=s+"§"+getCol9();
        }
        return s;
    }
}