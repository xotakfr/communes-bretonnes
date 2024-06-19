import modele.data.*;
import modele.methodesTri.*;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Cette classe permet de mettre en place des scénarios pour chaque classe du modèle. Nous recommandons de 
 * lancer l'exécution du programme dans un terminal en mode plein écran et de diminuer la taille du texte
 * en raison de la quantité d'informations renvoyées par la méthode testTri()
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class Scenario {

    /**
     * Le point d'entrée de notre programme
     * @param args Arguments de la ligne de commande
     */
    public static void main(String[] args) {
        System.out.println("***** TEST DU TRI CROISSANT DES DONNEES DE CHAQUE CLASSE DU MODELE POUR CHACUN DE LEURS FILTRES *****");
        testTri();
        System.out.println("***** TRI CROISSANT DES DONNEES SUR UN CAS ISOLE ET ILLUSTRE PAR UN DIAGRAMME DE SEQUENCE (VOIR ./UML) *****");
        triPourDiagrammeDeSequence();
    }

    /**
     * Cette méthode permet de tester le tri croissant des données par la méthode du tri rapide
     * sur chaque filtre de chaque classe du modèle à partir de trois instances puis imprime le 
     * résultat obtenu dans la console afin qu'il puisse être comparé à ce qu'il est attendu
     */
    private static void testTri() {
        // initialisation des données
        Object[] lesInstancesDeClasses = initialization();
        String[] lesFiltres;
        // test le tri sur les données
        for (int i = 0; i < lesInstancesDeClasses.length; i++) {
            // récupère les filtres associés au type des données
            lesFiltres = getCurrentFilters(i);
            for (String filtre : lesFiltres) {
                // tri et imprime le résultat pour les données
                useCurrentFilter(i, filtre, (Object[]) lesInstancesDeClasses[i]);
            }
        }
    }

    /**
     * Cette méthode consiste en le tri croissant des données par la méthode du tri par sélection
     * pour un filtre en particulier de la classe Departement à partir de trois instances dont le 
     * résultat est imprimé dans la console. Plus spécifiquement, cette méthode sert à l'illustration 
     * des envois de messages entre les méthodes utilisées lors d'un tri croissant des données par la
     * méthode du tri par sélection. 
     * Cette illustration est accompagnée d'un diagramme de séquence se trouvant dans ./UML
     */
    public static void triPourDiagrammeDeSequence() {
        // modification du filtre de la classe Departement
        Departement.setFilter("nomDep");
        // initialisation des données
        Departement D1 = new Departement(56, "MORBIHAN", 1000);
        Departement D2 = new Departement(22, "COTES-D'ARMOR", 5000);
        Departement D3 = new Departement(29, "FINISTERE", 10);
        Departement[] lesDepartements = {D1, D2, D3};
        // convertir notre liste vers une ArrayList
        ArrayList<Departement> notreArrayList = new ArrayList<Departement>(Arrays.asList(lesDepartements));
        System.out.println("Tri sur des données de la classe Departement selon nomDep");
        // obtenir une instance de TriParSelection à partir de l'ArrayList
        TriParSelection<Departement> notreTrieur = new TriParSelection<>(notreArrayList);
        // utiliser la méthode trier() de l'instance obtenue pour trier l'ArrayList selon un ordre croissant 
        notreTrieur.trier();
        for (Departement leDepartement : notreArrayList) {
            System.out.println(leDepartement);
        }
    }

    /**
     * Renvoie les instances des classes qu'on va tester
     * @return Les instances des classes qu'on va tester
     */
    public static Object[] initialization() {
        Annee[] lesAnnees = {
            new Annee(2018, 1f),
            new Annee(1984, 10f),
            new Annee(2078, -1f)
        };
        Departement[] lesDepartements = {
            new Departement(56, "MORBIHAN", 1000),
            new Departement(22, "COTES-D'ARMOR", 5000),
            new Departement(29, "FINISTERE", 10)
        };
        Aeroport[] lesAeroports = {
            new Aeroport("A", "3", lesDepartements[0]),
            new Aeroport("B", "2", lesDepartements[1]),
            new Aeroport("C", "1", lesDepartements[2])
        };
        Commune[] lesCommunes = {
            new Commune(3, "A", lesDepartements[0]),
            new Commune(2, "B", lesDepartements[1]),
            new Commune(1, "C", lesDepartements[2])
        };
        Gare[] lesGares = {
            new Gare(1, "C", true, false, lesCommunes[0]),
            new Gare(2, "B", true, false, lesCommunes[1]),
            new Gare(3, "A", false, true, lesCommunes[2])
        };
        DonneesAnnuelles[] lesDonneesAnnuelles = {
            new DonneesAnnuelles(lesAnnees[2], lesCommunes[0], 1, 3, 500, 10, 100, 1000, 3, 10000),
            new DonneesAnnuelles(lesAnnees[1], lesCommunes[1], 2, 2, 5000, 100, 10, 10000, 2, 1000),
            new DonneesAnnuelles(lesAnnees[0], lesCommunes[2], 3, 1, 50, 1, 1, 100, 1, 100)

        };
        Object[] lesInstances = {lesAnnees, lesDepartements, lesAeroports, lesCommunes, lesGares, lesDonneesAnnuelles};
        return lesInstances;
    }

    /**
     * Renvoie les filtres associés à la classe elle-même associée à i
     * @param i Le numéro de la classe
     * @return Les filtres associés à la classe elle-même associée à i
     */
    private static String[] getCurrentFilters(int i) {
        String[] ret = null;
        switch(i) {
            case 0:
                ret = Annee.getAllFilter();
                break;
            case 1:
                ret = Departement.getAllFilter();
                break;
            case 2:
                ret = Aeroport.getAllFilter();
                break;
            case 3:
                ret = Commune.getAllFilter();
                break;
            case 4:
                ret = Gare.getAllFilter();
                break;
            case 5:
                ret = DonneesAnnuelles.getAllFilter();
                break;
            default:
                break;
        }
        return ret;
    }

    /**
     * Tri et imprime dans le terminal le résultat du tri sur les données fournies en paramètre. 
     * Le tri est effectué en fonction de la valeur de i qui détermine le type abstrat de TriRapide
     * et la valeur de filtre qui détermine le filtre utilisé par l'instance de TriRapide obtenue
     * @param i La valeur qui détermine le type abstrait de TriRapide
     * @param filtre Le filtre qui détermine comment sont triées les instances
     * @param listeInstances Les instances qu'on trie selon le filtre
     */
    public static void useCurrentFilter(int i, String filtre, Object[] listeInstances) {
        System.out.println();
        switch(i) {
            case 0:
                Annee.setFilter(filtre);
                ArrayList<Annee> liste0 = new ArrayList<Annee>(Arrays.asList((Annee[]) listeInstances.clone()));
                System.out.println("Tri sur des données de la classe Annee selon " + filtre);
                TriRapide<Annee> trieur0 = new TriRapide<Annee>(liste0);    
                trieur0.trier();
                for (Annee instance : liste0) {
                    System.out.println(instance);
                }
                System.out.println();
                break;
            case 1:
                Departement.setFilter(filtre);
                ArrayList<Departement> liste1 = new ArrayList<Departement>(Arrays.asList((Departement[]) listeInstances.clone()));
                System.out.println("Tri sur des données de la classe Departement selon " + filtre);
                TriRapide<Departement> trieur1 = new TriRapide<Departement>(liste1);
                trieur1.trier();
                for (Departement instance : liste1) {
                    System.out.println(instance);
                }
                System.out.println();
                break;
            case 2:
                Aeroport.setFilter(filtre);
                ArrayList<Aeroport> liste2 = new ArrayList<Aeroport>(Arrays.asList((Aeroport[]) listeInstances.clone()));
                System.out.println("Tri sur des données de la classe Aeroport selon " + filtre);
                TriRapide<Aeroport> trieur2 = new TriRapide<Aeroport>(liste2);
                trieur2.trier();
                for (Aeroport instance : liste2) {
                    System.out.println(instance);
                }
                System.out.println();
                break;
            case 3:
                Commune.setFilter(filtre);
                ArrayList<Commune> liste3 = new ArrayList<Commune>(Arrays.asList((Commune[]) listeInstances.clone()));
                System.out.println("Tri sur des données de la classe Commune selon " + filtre);
                TriRapide<Commune> trieur3 = new TriRapide<Commune>(liste3);
                trieur3.trier();
                for (Commune instance : liste3) {
                    System.out.println(instance);
                }
                System.out.println();
                break;
            case 4:
                Gare.setFilter(filtre);
                ArrayList<Gare> liste4 = new ArrayList<Gare>(Arrays.asList((Gare[]) listeInstances.clone()));
                System.out.println("Tri sur des données de la classe Gare selon " + filtre);
                TriRapide<Gare> trieur4 = new TriRapide<Gare>(liste4);
                trieur4.trier();
                for (Gare instance : liste4) {
                    System.out.println(instance);
                }
                System.out.println();
                break;
            case 5:
                DonneesAnnuelles.setFilter(filtre);
                ArrayList<DonneesAnnuelles> liste5 = new ArrayList<DonneesAnnuelles>(Arrays.asList((DonneesAnnuelles[]) listeInstances.clone()));
                System.out.println("Tri sur des données de la classe DonneesAnnuelles selon " + filtre);
                TriRapide<DonneesAnnuelles> trieur5 = new TriRapide<DonneesAnnuelles>(liste5);
                trieur5.trier();
                for (DonneesAnnuelles instance : liste5) {
                    System.out.println(instance);
                }
                System.out.println();
                break;
            default:
                break;
        }
    }
}