package modele.methodesTri;

import java.util.ArrayList;

/**
 * Implémentation de l'algorithme du tri rapide pour une liste d'éléments comparables.
 * @param <T> Le type des éléments à trier, qui doit implémenter l'interface Comparable
 * @see TriRapide
 * @see ISearch
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class TriRapide<T extends Comparable<T>> implements Itri {
    /** La liste à trier */
    private ArrayList<T> tab;

    /**
     * Permet de créer une nouvelle instance de TriRapide avec la liste donnée
     * en paramètre qui consiste en la liste à trier
     * @param tab La liste à trier
     */
    public TriRapide(ArrayList<T> tab) {
        if (tab != null) {
            this.tab = tab;
        } 
        else {
            System.err.println("Error null");
        }
    }

    /**
     * Partitionne le tableau en utilisant le dernier élément comme pivot
     * @param low L'indice du premier élément
     * @param high L'indice du dernier élément
     * @return L'indice du pivot après le partitionnement
     */
    private int partition(int low, int high) {
        // On récupère le pivot
        T pivot = this.tab.get(high);
        // On récupère l'index du plus petit élément
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            // Si l'élément actuel est inférieur ou égal au pivot
            if (this.tab.get(j).compareTo(pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    /**
     * Fonction récursive utilisée pour trier le tableau
     * @param low L'indice de départ
     * @param high L'indice de fin
     */
    private void quickSort(int low, int high) {
        if (low < high) {
            // on récupère et positionne le pivot
            int pi = partition(low, high);

            // Tri rapide effectué sur les partitions restantes après et avant la position du pivot
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    /**
     * Échange deux éléments d'un tableau
     * @param i L'indice du premier élément
     * @param j L'indice du deuxième élément
     */
    private void swap(int i, int j) {
        T temp = this.tab.get(i);
        this.tab.set(i, this.tab.get(j));
        this.tab.set(j, temp);
    }

    /**
     * Trie la liste en utilisant l'algorithme de tri rapide
     */
    public void trier() {
        int n = this.tab.size();
        quickSort(0, n - 1);
    }
}
