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
    public static boolean exists(ArrayList<T> arr, T obj) {
        int start = 0;
        int end = arr.length-1;
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

    /**
     * Return the ID of the given parameter
     * @param arr ArrayListe of Type
     * @param obj object (Type) to search
     */
    public static int search(ArrayList<T> arr, T obj) {
        int start = 0;
        int end = arr.length-1;
        int mil;

        int ret = -1;

        while (!found && start <= end) {
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