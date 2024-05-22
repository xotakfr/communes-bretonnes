package tri;

import java.util.ArrayList;

/**
 * public class TriParSelection
 * Implements Itri
 * 
 * element de type T doit implémenter comparableTo
 */
public class TriRapide<T extends Comparable<T>> implements Itri {
    /**
     * private arraylist tab
     */
    private ArrayList<T> tab;

    /**
     * Create a new TriParSelection
     * @param tab T[]
     */
    public TriRapide(ArrayList<T> tab) {
        if (tab!= null) {
            this.tab = tab;
        } else {
            System.err.println("Error null");
        }
    }

    /**
     * Partition the array using last element as pivot
     * @param low index of the first element
     * @param high index of the last element
     * @return index of the pivot after partitioning
     */
    private int partition(int low, int high) {
        T pivot = this.tab.get(high); // Pivot
        int i = (low - 1); // Index of smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (this.tab.get(j).compareTo(pivot) <= 0) {
                i++; // increment index of smaller element
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    /**
     * Recursive function to sort the array
     * @param low starting index
     * @param high ending index
     */
    private void quickSort(int low, int high) {
        if (low < high) {
            // pi is partitioning index, arr[p] is now at right place
            int pi = partition(low, high);

            // Separately sort elements before partition and after partition
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    /**
     * Swap two T in tab
     * @param i int
     * @param j int
     */
    private void swap(int i, int j) {
        T temp = this.tab.get(i);
        this.tab.set(i, this.tab.get(j));
        this.tab.set(j, temp);
    }

    /**
     * Sort the tab
     */
    public void trier() {
        int n = this.tab.size();
        quickSort(0, n - 1);
    }
}
