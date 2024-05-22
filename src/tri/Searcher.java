package tri;

import java.util.ArrayList;

/**
 * Meilleure implémentation de ISearch au niveau vitesse
 * Ne fonctionne uniquement si obj est excactement le même objet que celui dans l'array
 * 
 * Peut être problématique si l'on fait une recherche uniquement par nom par exemple
 */
public class Searcher<T extends Comparable<T>> implements ISearch<T> {

    @Override
    public boolean exists(ArrayList<T> arr, T obj) {
        return arr.contains(obj);
    }

    @Override
    public int search(ArrayList<T> arr, T obj) {
        return arr.indexOf(obj);
    }
}
