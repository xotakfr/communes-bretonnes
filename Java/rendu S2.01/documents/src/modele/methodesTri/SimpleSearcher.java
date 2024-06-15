package modele.methodesTri;

import java.util.ArrayList;

/**
 * Implémentation plus avancée de l'interface ISearch.
 * Cette implémentation parcourt linéairement la liste pour rechercher un élément.
 * Elle compare chaque élément de la liste avec l'objet recherché en utilisant la méthode compareTo()
 * propre à l'élément.
 * @see Comparable
 * @see ISearch
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class SimpleSearcher<T extends Comparable<T>> implements ISearch<T> {
	/**
     * Vérifie si l'objet donné existe dans la liste
     * @param arr La liste dans laquelle rechercher
     * @param obj L'objet à rechercher
     * @return true si l'objet existe dans la liste, sinon false
     */
	@Override
	public boolean exists(ArrayList<T> arr, T obj) {
		boolean ret = false;
	    for (T item : arr) {
			if (item.compareTo(obj) == 0) {
				ret = true;
			}
	    }
	    return ret;
	}

	/**
     * Recherche l'index de l'objet donné dans la liste
     * @param arr La liste dans laquelle rechercher
     * @param obj L'objet à rechercher
     * @return L'index de l'objet dans la liste, ou -1 si l'objet n'est pas trouvé
     */
	@Override
	public int search(ArrayList<T> arr, T obj) {
		int index = -1;
		int i = 0;
	    while (!(index == -1) && i < arr.size()) {
			if (arr.get(i).compareTo(obj) == 0) {
				index = i;
			}
			i++;
	    }
	    return i;
	}
}
