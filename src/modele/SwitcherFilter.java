package modele;

/**
 * Public interface SwitcherFilter
 * Filter used with comparable
 */
public interface SwitcherFilter {

    /**
     * Get a list of all available filters
     */
    String[] getAllFilters();

    /**
     * Change the current filter
     * The filter is used in compareTo
     */
    void setFilter(String filter);
}
