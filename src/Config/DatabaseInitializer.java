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

            // Users table
            statement.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "email VARCHAR(100) UNIQUE, " +
                    "password VARCHAR(100), " +
                    "role VARCHAR(50) CHECK (role IN ('admin', 'doctor', 'patient')));");

            // Doctors table with only 3 specializations
            statement.execute("CREATE TABLE IF NOT EXISTS doctors (" +
                    "id SERIAL PRIMARY KEY, " +
                    "user_id INT UNIQUE REFERENCES users(id) ON DELETE CASCADE, " +
                    "specialization VARCHAR(50) CHECK (specialization IN ('Surgeon', 'Psychologist', 'Therapist')));");

            // Patients table
            statement.execute("CREATE TABLE IF NOT EXISTS patients (" +
                    "id SERIAL PRIMARY KEY, " +
                    "user_id INT UNIQUE REFERENCES users(id) ON DELETE CASCADE, " +
                    "health_history TEXT);");

            // Medicines table
            statement.execute("CREATE TABLE IF NOT EXISTS medicines (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(100) UNIQUE, " +
                    "dosage VARCHAR(50));");

            // Beds table
            statement.execute("CREATE TABLE IF NOT EXISTS beds (" +
                    "id SERIAL PRIMARY KEY, " +
                    "is_occupied BOOLEAN DEFAULT FALSE);");

            // Appointments table
            statement.execute("CREATE TABLE IF NOT EXISTS appointments (" +
                    "id SERIAL PRIMARY KEY, " +
                    "doctor_id INT REFERENCES doctors(id) ON DELETE CASCADE, " +
                    "patient_id INT REFERENCES patients(id) ON DELETE CASCADE, " +
                    "appointment_date TIMESTAMP);");

            // Reports table
            statement.execute("CREATE TABLE IF NOT EXISTS reports (" +
                    "id SERIAL PRIMARY KEY, " +
                    "doctor_id INT REFERENCES doctors(id) ON DELETE CASCADE, " +
                    "patient_id INT REFERENCES patients(id) ON DELETE CASCADE, " +
                    "details TEXT);");

            System.out.println("Database initialized successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error initializing the database.");
        }
    }

    public static void addDefaultUsers() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Default Admin
            statement.execute("INSERT INTO users (name, email, password, role) VALUES " +
                    "('Admin User', 'admin@hospital.com', 'admin123', 'admin') " +
                    "ON CONFLICT (email) DO NOTHING;");

            // Default Doctors with only the 3 allowed specializations
            statement.execute("INSERT INTO users (name, email, password, role) VALUES " +
                    "('Dr. Smith', 'surgeon@hospital.com', 'pass123', 'doctor') " +
                    "ON CONFLICT (email) DO NOTHING;");
            statement.execute("INSERT INTO doctors (user_id, specialization) " +
                    "SELECT id, 'Surgeon' FROM users WHERE email = 'surgeon@hospital.com' " +
                    "ON CONFLICT (user_id) DO NOTHING;");

            statement.execute("INSERT INTO users (name, email, password, role) VALUES " +
                    "('Dr. John', 'therapist@hospital.com', 'pass124', 'doctor') " +
                    "ON CONFLICT (email) DO NOTHING;");
            statement.execute("INSERT INTO doctors (user_id, specialization) " +
                    "SELECT id, 'Therapist' FROM users WHERE email = 'therapist@hospital.com' " +
                    "ON CONFLICT (user_id) DO NOTHING;");

            statement.execute("INSERT INTO users (name, email, password, role) VALUES " +
                    "('Dr. Wilson', 'psychologist@hospital.com', 'pass125', 'doctor') " +
                    "ON CONFLICT (email) DO NOTHING;");
            statement.execute("INSERT INTO doctors (user_id, specialization) " +
                    "SELECT id, 'Psychologist' FROM users WHERE email = 'psychologist@hospital.com' " +
                    "ON CONFLICT (user_id) DO NOTHING;");

            // Default Medicines
            statement.execute("INSERT INTO medicines (name, dosage) VALUES " +
                    "('Paracetamol', '500mg'), " +
                    "('Ibuprofen', '200mg'), " +
                    "('Amoxicillin', '250mg') " +
                    "ON CONFLICT (name) DO NOTHING;");

            // Default Beds
            statement.execute("INSERT INTO beds (is_occupied) VALUES (FALSE), (FALSE), (TRUE), (FALSE), (TRUE) " +
                    "ON CONFLICT (id) DO NOTHING;");

            System.out.println("Default users, medicines, and beds added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding default users, medicines, and beds.");
        }
    }
}