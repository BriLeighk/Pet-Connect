package edu.wisconsin.databaseclass.pet_connect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestMySQLConnection {
    public static void main(String[] args) {
        
        String jdbcUrl = "jdbc:mysql://localhost:3306/pet_adoption";
        String username = "brileighx";
        String password = "BriBriK7_200203";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            if (connection != null) {
                System.out.println("Successfully connected to MySQL database.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to MySQL database.");
            e.printStackTrace();
        }
    }
}
