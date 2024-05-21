package tri;

import java.util.ArrayList;

import pays.*;

/**
 * public class TriParSelection
 * Implements Itri
 */
public class TriParSelectionAlpha implements Itri {
	/**
	 * private arraylist tab
	 */
	private ArrayList<Pays> tab;

	/**
	 * Create a new TriParSelection
	 * @param tab Pays[]
	 */
	public TriParSelectionAlpha(ArrayList<Pays> tab) {
		if (tab!=null) {
			this.tab = tab;
		} else {
			System.err.println("Error null");
		}
	}

	/**
	 * Return the position or the min in the tab
	 * @param debut int
	 * @return int
	 */
	private int minimumPosition(int debut) {
		int min = debut;
		for (int a=debut+1;a<tab.size();a++) {
			Pays p = this.tab.get(a);
			Pays p2 = this.tab.get(min);
			if (p.getNom().compareToIgnoreCase(p2.getNom())<0) {
				min = a;
			}
		}
		return min;
	}

	/**
	 * Swap two Pays in tab
	 * @param i int
	 * @param j int
	 */
	private void swap(int i, int j) {
		Pays temp = this.tab.get(i);
		this.tab.set(i, this.tab.get(j));
		this.tab.set(j, temp);
	}

	/**
	 * Sort the tab
	 */
	public void trier() {
		for (int a=0; a<this.tab.size(); a++) {
			int min = this.minimumPosition(a);
			this.swap(a, min);
		}
	}
}
