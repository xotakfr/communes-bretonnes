package modele.data;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class DefaultThing {
    public StringProperty col1;
    public StringProperty col2;
    public StringProperty col3;
    public StringProperty col4;
    public StringProperty col5;
    public StringProperty col6;
    public StringProperty col7;
    public StringProperty col8;
    public StringProperty col9;

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

    public String getCol1() {
        return col1.get();
    }

    public String getCol2() {
        return col2.get();
    }

    public String getCol3() {
        return col3.get();
    }
    public String getCol4() {
        return col4.get();
    }
    public String getCol5() {
        return col5.get();
    }
    public String getCol6() {
        return col6.get();
    }
    public String getCol7() {
        return col7.get();
    }
    public String getCol8() {
        return col8.get();
    }
    public String getCol9() {
        return col9.get();
    }

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