package modele.methodesTri;

import java.util.ArrayList;

/**
 * Implémentation simpliste de l'interface ISearch.
 * Cette implémentation utilise les méthodes contains() et indexOf() de ArrayList pour rechercher
 * un élément dans la liste. Elle fonctionne uniquement si l'objet recherché est exactement le même
 * objet que celui stocké dans la liste.
 * Attention ! Cette implémentation peut poser des problèmes si l'on effectue une recherche
 * uniquement par rapport à certaines propriétés de l'objet (par exemple, le nom), car elle compare
 * les objets par égalité, et non pas par leurs propriétés
 * @see Comparable
 * @see ISearch
 * @author Nathan Guheneuf-Le Brec, Inaki Gomez--Jego, Jean-Louis Emeraud, François Patinec-Haxel
 */
public class Searcher<T extends Comparable<T>> implements ISearch<T> {
    /**
     * Vérifie si l'objet donné existe dans la liste
     * @param arr La liste dans laquelle rechercher
     * @param obj L'objet à rechercher
     * @return true si l'objet existe dans la liste, sinon false
     */
    @Override
    public boolean exists(ArrayList<T> arr, T obj) {
        return arr.contains(obj);
    }

    /**
     * Recherche l'index de l'objet donné dans la liste
     * @param arr La liste dans laquelle rechercher
     * @param obj L'objet à rechercher
     * @return L'index de l'objet dans la liste, ou -1 si l'objet n'est pas trouvé
     */
    @Override
    public int search(ArrayList<T> arr, T obj) {
        return arr.indexOf(obj);
    }
}
