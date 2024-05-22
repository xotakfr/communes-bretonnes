package modele;

/**
 * public interface SwitcherFilter
 * Filter used with comparable
 */
public interface SwitcherFilter {

    /**
     * Get a list of all available filters
     */
    public static String[] getAllFilter();

    /**
     * Change the current filter
     * The filter is used in compareTo
     */
    public static void setFilter(String filter);
}