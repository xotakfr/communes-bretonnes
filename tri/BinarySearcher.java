package tri;

import java.util.ArrayList;

/**
 * Public Class BinarySearcher
 * 
 * Type T must implement Comparable<T>
 */
public class BinarySearcher<T> {
    /**
     * Return True If the given parameter exists
     * @param arr ArrayListe of Type
     * @param obj object (Type) to search
     */
    public static boolean search(ArrayList<T> arr, T obj) {
        int start = 0;
        int end = arr.length-1;
        int mil;

        boolean found = false;

        while (!found && start <= end) {
            mil = (start+end)/2;

            if (tabPays[mil].getNom().equals(obj)) {
                found = true;
            } else if (nom.compareToIgnoreCase(tabPays[mil].getNom())<0) {
                end = mil -1;
            } else if (nom.compareToIgnoreCase(tabPays[mil].getNom())>0) {
                start = mil + 1;
            }

        }
        return found;
    }
}