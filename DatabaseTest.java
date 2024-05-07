import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTest {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "Learning#.01";

    public static void main(String[] args) {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found.");
            e.printStackTrace();
            return;
        }

        // Attempt to connect to the MySQL server directly, without specifying a database
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (connection != null) {
                System.out.println("Connected to MySQL server successfully!");
                // Check and create the database if it doesn't exist
                createDatabaseIfNotExists(connection);
            }
        } catch (SQLException e) {
            System.out.println("Connection to MySQL server failed!");
            e.printStackTrace();
        }
    }

    private static void createDatabaseIfNotExists(Connection connection) throws SQLException {
        String dbName = "BankingDB";
        Statement stmt = connection.createStatement();
        // SQL command to create the database if it does not exist
        String sql = "CREATE DATABASE IF NOT EXISTS " + dbName;
        stmt.executeUpdate(sql);
        System.out.println("Database " + dbName + " created or already exists.");
        // Close the statement
        stmt.close();
        // Connect to the new database and do further operations if needed
        try (Connection dbConnection = DriverManager.getConnection(URL + dbName, USER, PASSWORD)) {
            System.out.println("Connected to the database " + dbName + " successfully!");
            // You can add more operations here to initialize tables, etc.
        }
    }
}