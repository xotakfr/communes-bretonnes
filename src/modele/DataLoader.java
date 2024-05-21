package modele;

import java.util.ArrayList;
import java.util.Scanner;

public class DataLoader {
    private static final String PATH = "../data/";
    private static final String AIRPORT_PATH = "aeroport.csv"; //Name; Departement number; location
    private static final String MUNICIPALITY_PATH = "communesBretonnes.csv";//Departement number; insee; name
    private static final String DEPARTEMENTS = "departement.csv"; //departement; name
    private static final String CULTURAL = "depensesCulturellesParCommune.csv"; // Year ;name;insee;popu;total_cultu;total_b
    private static final String TRAIN ="gare.csv"; //Code; name;fret;voya;commune;dep;code_co
    private static final String CULTURAL_DEP = "investissementCulturelParDep.csv"; // Year ;name;insee;popu;total_cultu;total_b


    private static final String NEAR = "voisinageCommunesBretonnes.csv"//insee;nom;nbvoisins; insee voisins
}
