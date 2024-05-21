package tri;

import java.util.ArrayList;

import pays.*;

/**
 * Public Class BinarySearcher
 */
public class BinarySearcher {
    /**
     * search
     * @param tabPays Pays[]
     * @param nom String
     */
    public static boolean search(Pays[] tabPays, String nom) {
        int start = 0;
        int end = tabPays.length-1;
        int mil;

        boolean found = false;

        while (!found && start <= end) {
            mil = (start+end)/2;

            if (tabPays[mil].getNom().equals(nom)) {
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