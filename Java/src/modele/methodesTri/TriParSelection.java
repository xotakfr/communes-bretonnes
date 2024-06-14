package modele.methodesTri;

import java.util.ArrayList;

/**
 * Implémentation de l'algorithme du tri par sélection pour une liste d'éléments comparables.
 * @param <T> Le type des éléments à trier, qui doit implémenter l'interface Comparable
 * @see Comparable
 * @see Itri
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class TriParSelection<T extends Comparable<T>> implements Itri {
	/** La liste à trier */
	private ArrayList<T> tab;

	/**
     * Permet de créer une nouvelle instance de TriParSelection avec la liste donnée
     * en paramètre qui consiste en la liste à trier
     * @param tab La liste à trier
     */
	public TriParSelection(ArrayList<T> tab) {
		if (tab != null) {
			this.tab = tab;
		} 
		else {
			System.err.println("Error null");
		}
	}

	/**
     * Retourne la position du plus petit élément dans la liste à partir de l'indice donné
     * @param debut L'indice à partir duquel rechercher
     * @return La position du plus petit élément
     */
	private int minimumPosition(int debut) {
		int min = debut;
		for (int a = debut + 1; a < this.tab.size(); a++) {
			T p = this.tab.get(a);
			T p2 = this.tab.get(min);
			if (p.compareTo(p2) < 0) {
				min = a;
			}
		}
		return min;
	}

	/**
     * Échange deux éléments dans la liste
     * @param i L'indice du premier élément
     * @param j L'indice du deuxième élément
     */
	private void swap(int i, int j) {
		T temp = this.tab.get(i);
		this.tab.set(i, this.tab.get(j));
		this.tab.set(j, temp);
	}

	/**
     * Trie la liste en utilisant l'algorithme de tri par sélection
     */
	public void trier() {
		for (int a = 0; a < this.tab.size() - 1; a++) {
			int min = this.minimumPosition(a);
			this.swap(a, min);
		}
	}
}