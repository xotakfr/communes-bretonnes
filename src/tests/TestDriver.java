package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestDriver {
    private static String url = "jdbc:mysql://127.0.0.1:3306/BDSAE";
    private static String user = "user";
    private static String password = "password";

    protected static Connection getConnection() { 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Return null or throw an exception based on your requirement
            return null;
        } catch (SQLException e) {
            System.err.println("Invalid login or password");
            // Return null or throw an exception based on your requirement
            return null;
        }
    }


    public static void main (String[] args) {
        getConnection();
    }
}