public class TestDriver {
    private String url = "jdbc:mysql://127.0.0.1:3306/BDSAE";
    private String user = "user";
    private String password = "password";

    protected Connection getConnection() {
        try {
            class.forname("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection("url", user, password);
        } catch (ClassNotFoundException e) {
            System.err.println(e.stackTrace());
        }
    }

    public static void main (String[] args) {
        getConnection();
    }
}