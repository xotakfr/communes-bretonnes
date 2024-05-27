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
    
}