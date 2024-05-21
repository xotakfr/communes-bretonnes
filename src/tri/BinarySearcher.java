package tri;

import java.util.ArrayList;

/**
 * Public Class BinarySearcher
 * 
 * Type T must implement Comparable<T>
 */
public class BinarySearcher<T extends Comparable<T>> implements ISearch<T> {
    @Override
    /**
     * Return True If the given parameter exists
     * @param arr ArrayListe of Type
     * @param obj object (Type) to search
     */
    public boolean exists(ArrayList<T> arr, T obj) {
        int start = 0;
        int end = arr.size()-1;
        int mil;

        boolean found = false;

        while (!found && start <= end) {
            mil = (start+end)/2;

            if (arr.get(mil).compareTo(obj)==0) {
                found = true;
            } else if (obj.compareTo(arr.get(mil))<0) {
                end = mil -1;
            } else if (obj.compareTo(arr.get(mil))>0) {
                start = mil + 1;
            }

        }
        return found;
    }

    @Override
    /**
     * Return the ID of the given parameter
     * @param arr ArrayListe of Type
     * @param obj object (Type) to search
     */
    public int search(ArrayList<T> arr, T obj) {
        int start = 0;
        int end = arr.size()-1;
        int mil = 0;

        int ret = -1;

        while (ret==-1 && start <= end) {
            mil = (start+end)/2;

            if (arr.get(mil).compareTo(obj)==0) {
                ret = mil;
            } else if (obj.compareTo(arr.get(mil))<0) {
                end = mil -1;
            } else if (obj.compareTo(arr.get(mil))>0) {
                start = mil + 1;
            }

        }
        return mil;
    }
}