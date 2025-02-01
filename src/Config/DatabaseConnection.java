package Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/hospital_db"; // Update with your DB details
    private static final String USER = "postgres"; // Replace with your DB username
    private static final String PASSWORD = "user"; // Replace with your DB password

    public static Connection getConnection() throws SQLException {
        try {
            // Load the PostgreSQL driver class
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to connect to the database.", e);
        }
    }
}
