import modele.classes.*;
import modele.methodesTri.*;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Cette classe permet de mettre en place des scénarios d'utilisation normal pour chaque classe du modèle
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class OldScenario {

    /**
     * Le point d'entrée de notre programme
     * @param args Arguments de la ligne de commande
     */
    public static void main(String[] args) {
        System.out.println("***** TEST SUR LE TRI DES DONNEES DE CHAQUE CLASSE DU MODELE POUR CHACUN DE LEURS FILTRES *****");
        testTri();
        System.out.println("***** TEST SUR LE FILTRAGE DES DONNEES D'UNE CLASSE EN FONCTION DE VALEURS NUMERIQUES *****");
        testModele();
    }

    /**
     * Méthode de test pour le modèle
     */
    private static void testModele() {
        DataLoader.loadAll();

        for (Commune co : DataLoader.getCommunes()) {
            System.out.println(co);
        }
        for (Departement de : DataLoader.getDepartements()) {
            System.out.println(de);
        }

        ArrayList<Commune> d = DataLoader.getCommunes("idCommune", ">29000;<30000");
        for (Commune co : d) {
            System.out.println(co);
        }
        
        System.out.println("\n\n\nTests Filtres");
        System.out.println("Filtre selon le nom (Exemple, l'utilisateur va chercher toute les communes qui commencent par \"QU\")");
        ArrayList<Commune> d1 = DataLoader.getCommunes("nomCommune", ">QT;<QV");// Recherche de toute les communes dont le nom commence par QU
        for (Commune co : d1) {
            System.out.println(co);
        }

        System.out.println("Filtre selon la population (Exemple, l'utilisateur va chercher toute les communes qui ont entre 50000 et 100000 de population)");
        ArrayList<Commune> d2 = DataLoader.getCommunes("population", ">50000;<100000");// entre 5000 et 100000
        for (Commune co : d2) {
            System.out.println(co);
        }
    }

    /**
     * Cette méthode permet de tester le tri des données sur chaque filtre de chaque
     * classe du modèle à partir de trois instances puis imprime le résultat obtenu dans la console
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
            new Commune(3, "C", lesDepartements[0]),
            new Commune(2, "B", lesDepartements[1]),
            new Commune(1, "A", lesDepartements[2])
        };
        Gare[] lesGares = {
            new Gare(1, "C", true, false, lesCommunes[2]),
            new Gare(2, "B", true, false, lesCommunes[1]),
            new Gare(3, "A", false, true, lesCommunes[0])
        };
        DonneesAnnuelles[] lesDonneesAnnuelles = {
            new DonneesAnnuelles(lesAnnees[2], lesCommunes[0], 1, 3, 500, 10, 1000, 3, 10000),
            new DonneesAnnuelles(lesAnnees[1], lesCommunes[1], 2, 2, 5000, 100, 10000, 2, 1000),

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
     * Le tri est effectué en fonction de la valeur de i qui détermine le type T de TriRapide<T>
     * et la valeur de filtre qui détermine le filtre utilisé par l'instance de TriRapide<T>
     * @param i La valeur qui détermine le type T de TriRapide<T>
     * @param filtre Le filtre qui détermine comment sont triées les instances
     * @param listeInstances Les instances qu'on trie selon le filtre
     */
    public static void useCurrentFilter(int i, String filtre, Object[] listeInstances) {
        switch(i) {
            case 0:
                ArrayList<Annee> liste0 = new ArrayList<Annee>(Arrays.asList((Annee[]) listeInstances.clone()));
                System.out.println("Tri sur des données de la classe Annee selon " + filtre);
                TriRapide<Annee> trieur0 = new TriRapide<Annee>(liste0);    
                trieur0.trier();
                for (Annee instance : liste0) {
                    System.out.print(instance.toString());
                }
                System.out.println();
                break;
            case 1:
                ArrayList<Departement> liste1 = new ArrayList<Departement>(Arrays.asList((Departement[]) listeInstances.clone()));
                System.out.println("Tri sur des données de la classe Departement selon " + filtre);
                TriRapide<Departement> trieur1 = new TriRapide<Departement>(liste1);
                trieur1.trier();
                for (Departement instance : liste1) {
                    System.out.print(instance.toString());
                }
                System.out.println();
                break;
            case 2:
                ArrayList<Aeroport> liste2 = new ArrayList<Aeroport>(Arrays.asList((Aeroport[]) listeInstances.clone()));
                System.out.println("Tri sur des données de la classe Aeroport selon " + filtre);
                TriRapide<Aeroport> trieur2 = new TriRapide<Aeroport>(liste2);
                trieur2.trier();
                for (Aeroport instance : liste2) {
                    System.out.print(instance.toString());
                }
                System.out.println();
                break;
            case 3:
                ArrayList<Commune> liste3 = new ArrayList<Commune>(Arrays.asList((Commune[]) listeInstances.clone()));
                System.out.println("Tri sur des données de la classe Commune selon " + filtre);
                TriRapide<Commune> trieur3 = new TriRapide<Commune>(liste3);
                trieur3.trier();
                for (Commune instance : liste3) {
                    System.out.print(instance.toString());
                }
                System.out.println();
                break;
            case 4:
                ArrayList<Gare> liste4 = new ArrayList<Gare>(Arrays.asList((Gare[]) listeInstances.clone()));
                System.out.println("Tri sur des données de la classe Gare selon " + filtre);
                TriRapide<Gare> trieur4 = new TriRapide<Gare>(liste4);
                trieur4.trier();
                for (Gare instance : liste4) {
                    System.out.print(instance.toString());
                }
                System.out.println();
                break;
            case 5:
                ArrayList<DonneesAnnuelles> liste5 = new ArrayList<DonneesAnnuelles>(Arrays.asList((DonneesAnnuelles[]) listeInstances.clone()));
                System.out.println("Tri sur des données de la classe DonneesAnnuelles selon " + filtre);
                TriRapide<DonneesAnnuelles> trieur5 = new TriRapide<DonneesAnnuelles>(liste5);
                trieur5.trier();
                for (DonneesAnnuelles instance : liste5) {
                    System.out.print(instance.toString());
                }
                System.out.println();
                break;
            default:
                break;
        }
    }
}