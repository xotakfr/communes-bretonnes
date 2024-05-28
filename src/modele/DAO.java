package modele;

public abstract class DAO<T> {
    private String url = "jdbc:mysql://127.0.0.1:3306/BDSAE";
    
    protected Connection getConnection() {
        try {
            class.forname("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.err.println(e.stackTrace());
        }
    }

    public abstract List<T> findAll();
    public abstract T findById(long id);
    /**
     * Voir documentation de SwitcherFilter
     * 
     * Est censé proposer un accès de recherche en limitant les instructions SQL "dangeureuses"
     * @param filter Filtre de recherche (liste de filtres accessibles dans Object.filtersList)
     * @param filterSelect Filtre de séléction (Accepte plusieurs instructions via ;)
     */
    public abstract T findByFilter(String filter, String filterSelect);
    public abstract void update(T element); 
    public abstract void create(T element);
    public abstract void delete(T element);
    
}