package modele.methodesTri;

import java.util.ArrayList;

/**
 * Interface pour les méthodes concernant la recherche dans une liste.
 * Cette interface définit la signature de deux méthodes utilisée 
 * pour effectuer la recherche d'un élément dans une liste
 * @param <T> Le type des éléments dans la liste
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public interface ISearch<T> {
	/**
      * Vérifie si un élément donné existe dans la liste.
      * @param arr La liste dans laquelle rechercher.
      * @param obj L'objet (de type T) à rechercher.
      * @return true si l'objet est trouvé dans la liste, false sinon.
      */
	public boolean exists(ArrayList<T> arr, T obj);

	/**
      * Recherche l'indice de l'élément donné dans la liste.
      * @param arr La liste dans laquelle rechercher.
      * @param obj L'objet (de type T) à rechercher.
      * @return L'indice de l'objet dans la liste, -1 s'il n'est pas trouvé.
      */
	public int search(ArrayList<T> arr, T obj);
}
