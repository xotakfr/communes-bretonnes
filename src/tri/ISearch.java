package tri;

import java.util.ArrayList;

/**
 * public class ISearch
 */
public interface ISearch<T> {
	/**
	 * sort
	 */
	public boolean exists(ArrayList<T> arr, T obj);
	public int search(ArrayList<T> arr, T obj);
}
