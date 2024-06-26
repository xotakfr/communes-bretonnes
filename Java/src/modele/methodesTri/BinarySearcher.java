package modele.methodesTri;

import java.util.ArrayList;

/**
 * Cette classe permet de rechercher des éléments dans une liste triée en utilisant l'algorithme de recherche binaire.
 * L'instance de type T doit implémenter l'interface Comparable afin que le programme soit fonctionnel
 * @see Comparable
 * @see ISearch
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class BinarySearcher<T extends Comparable<T>> implements ISearch<T> {
    /**
     * Vérifie si un élément donné existe dans la liste triée
     * @param arr La liste triée dans laquelle rechercher
     * @param obj L'objet (de type T) à rechercher
     * @return true si l'objet est trouvé dans la liste, false sinon
     */
    @Override
    public boolean exists(ArrayList<T> arr, T obj) {
        int start = 0;
        int end = arr.size() - 1;
        int mil;
        boolean found = false;

        while (!found && start <= end) {
            mil = (start + end) / 2;

            if (arr.get(mil).compareTo(obj) == 0) {
                found = true;
            } 
            else if (obj.compareTo(arr.get(mil)) < 0) {
                end = mil - 1;
            } 
            else if (obj.compareTo(arr.get(mil)) > 0) {
                start = mil + 1;
            }
        }
        return found;
    }

    /**
     * Recherche l'indice de l'élément donné dans la liste triée
     * @param arr La liste triée dans laquelle rechercher
     * @param obj L'objet (de type T) à rechercher
     * @return L'indice de l'objet dans la liste, -1 s'il n'est pas trouvé
     */
    @Override
    public int search(ArrayList<T> arr, T obj) {
        int start = 0;
        int end = arr.size() - 1;
        int mil = 0;
        int ret = -1;

        while (ret == -1 && start <= end) {
            mil = (start + end) / 2;

            if (arr.get(mil).compareTo(obj) == 0) {
                ret = mil;
            } 
            else if (obj.compareTo(arr.get(mil)) < 0) {
                end = mil - 1;
            } 
            else if (obj.compareTo(arr.get(mil)) > 0) {
                start = mil + 1;
            }
        }
        return mil;
    }
}