package tri;

import java.util.ArrayList;

public class Searcher<T extends Comparable<T>> implements ISearch<T> {

    @Override
    public boolean exists(ArrayList<T> arr, T obj) {
        return arr.contains(obj);
    }

    @Override
    public int search(ArrayList<T> arr, T obj) {
        return arr.indexOf(obj);
    }
}
