package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private static final String URL = "jdbc:postgresql://localhost:5432/hospital_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "user";

    public static void initializeDatabase() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {


            statement.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "email VARCHAR(100) UNIQUE, " +
                    "password VARCHAR(100), " +
                    "role VARCHAR(50)");


            statement.execute("CREATE TABLE IF NOT EXISTS doctors (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "email VARCHAR(100) UNIQUE, " +
                    "password VARCHAR(100), " +
                    "specialization VARCHAR(100)");


            statement.execute("CREATE TABLE IF NOT EXISTS patients (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "email VARCHAR(100) UNIQUE, " +
                    "password VARCHAR(100), " +
                    "medical_history TEXT");


            statement.execute("CREATE TABLE IF NOT EXISTS appointments (" +
                    "id SERIAL PRIMARY KEY, " +
                    "doctor_id INT REFERENCES doctors(id), " +
                    "patient_id INT REFERENCES patients(id), " +
                    "date TIMESTAMP");


            statement.execute("CREATE TABLE IF NOT EXISTS reports (" +
                    "id SERIAL PRIMARY KEY, " +
                    "doctor_id INT REFERENCES doctors(id), " +
                    "patient_id INT REFERENCES patients(id), " +
                    "details TEXT");


            statement.execute("CREATE TABLE IF NOT EXISTS medicines (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "dosage VARCHAR(50)");


            statement.execute("CREATE TABLE IF NOT EXISTS beds (" +
                    "id SERIAL PRIMARY KEY, " +
                    "is_occupied BOOLEAN");


            statement.execute("CREATE TABLE IF NOT EXISTS hospitals (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "location VARCHAR(200)");

            System.out.println("Database initialized successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error initializing the database.");
        }
    }
}
