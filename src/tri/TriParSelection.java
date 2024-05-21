package tri;

import java.util.ArrayList;

/**
 * public class TriParSelection
 * Implements Itri
 * 
 * element de type T doit implémenter comparableTo
 */
public class TriParSelection implements Itri {
	/**
	 * private arraylist tab
	 */
	private ArrayList<T> tab;

	/**
	 * Create a new TriParSelection
	 * @param tab T[]
	 */
	public TriParSelection(ArrayList<T> tab) {
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
			T p = this.tab.get(a);
			T p2 = this.tab.get(min);
			if (p.compareTo(this.tab.get(min))<0) {
				min = a;
			}
		}
		return min;
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
		for (int a=0; a<this.tab.size(); a++) {
			int min = this.minimumPosition(a);
			this.swap(a, min);
		}
	}
}