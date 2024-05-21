package tri;

import java.util.ArrayList;

public class SimpleSearcher<T> implements ISearch {
	public boolean exists(ArrayList<T> arr, T obj) {
		boolean ret = false;
	    for (T item : arr) {
		if (item.compareTo(obj)==0) {
		    ret = true;
		}
	    }
	    return ret;
	}
	
	public int search(ArrayList<T> arr, T obj) {
		int index = -1;
		int i=0;
	    while ((!index==-1) && i<arr.size()) {
		if (item.compareTo(obj)==0) {
		    index = i;
		}
		i++;
	    }
	    return i;
	}
}
