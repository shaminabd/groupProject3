package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private static final String URL = "jdbc:postgresql://localhost:4242/hospital_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "12344321";

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

            // Doctors table with all specializations
            statement.execute("CREATE TABLE IF NOT EXISTS doctors (" +
                    "id SERIAL PRIMARY KEY, " +
                    "user_id INT UNIQUE REFERENCES users(id) ON DELETE CASCADE, " +
                    "specialization VARCHAR(50));");

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

            // Default Doctors with Specializations
            statement.execute("INSERT INTO users (name, email, password, role) VALUES " +
                    "('Dr. Smith', 'doctor@hospital.com', 'pass123', 'doctor') " +
                    "ON CONFLICT (email) DO NOTHING;");
            statement.execute("INSERT INTO doctors (user_id, specialization) " +
                    "SELECT id, 'Cardiology' FROM users WHERE email = 'doctor@hospital.com' " +
                    "ON CONFLICT (user_id) DO NOTHING;");

            statement.execute("INSERT INTO users (name, email, password, role) VALUES " +
                    "('Dr. House', 'surgeon@hospital.com', 'pass124', 'doctor') " +
                    "ON CONFLICT (email) DO NOTHING;");
            statement.execute("INSERT INTO doctors (user_id, specialization) " +
                    "SELECT id, 'Surgeon' FROM users WHERE email = 'surgeon@hospital.com' " +
                    "ON CONFLICT (user_id) DO NOTHING;");

            statement.execute("INSERT INTO users (name, email, password, role) VALUES " +
                    "('Dr. Adams', 'neurologist@hospital.com', 'pass125', 'doctor') " +
                    "ON CONFLICT (email) DO NOTHING;");
            statement.execute("INSERT INTO doctors (user_id, specialization) " +
                    "SELECT id, 'Neurology' FROM users WHERE email = 'neurologist@hospital.com' " +
                    "ON CONFLICT (user_id) DO NOTHING;");

            statement.execute("INSERT INTO users (name, email, password, role) VALUES " +
                    "('Dr. Wilson', 'oncologist@hospital.com', 'pass126', 'doctor') " +
                    "ON CONFLICT (email) DO NOTHING;");
            statement.execute("INSERT INTO doctors (user_id, specialization) " +
                    "SELECT id, 'Oncology' FROM users WHERE email = 'oncologist@hospital.com' " +
                    "ON CONFLICT (user_id) DO NOTHING;");

            statement.execute("INSERT INTO users (name, email, password, role) VALUES " +
                    "('Dr. Elizabeth', 'psychiatrist@hospital.com', 'pass127', 'doctor') " +
                    "ON CONFLICT (email) DO NOTHING;");
            statement.execute("INSERT INTO doctors (user_id, specialization) " +
                    "SELECT id, 'Psychiatrist' FROM users WHERE email = 'psychiatrist@hospital.com' " +
                    "ON CONFLICT (user_id) DO NOTHING;");

            statement.execute("INSERT INTO users (name, email, password, role) VALUES " +
                    "('Dr. Emma', 'radiologist@hospital.com', 'pass128', 'doctor') " +
                    "ON CONFLICT (email) DO NOTHING;");
            statement.execute("INSERT INTO doctors (user_id, specialization) " +
                    "SELECT id, 'Radiologist' FROM users WHERE email = 'radiologist@hospital.com' " +
                    "ON CONFLICT (user_id) DO NOTHING;");

            statement.execute("INSERT INTO users (name, email, password, role) VALUES " +
                    "('Dr. Ali', 'hematology@hospital.com', 'pass129', 'doctor') " +
                    "ON CONFLICT (email) DO NOTHING;");
            statement.execute("INSERT INTO doctors (user_id, specialization) " +
                    "SELECT id, 'Hematology' FROM users WHERE email = 'hematology@hospital.com' " +
                    "ON CONFLICT (user_id) DO NOTHING;");

            statement.execute("INSERT INTO users (name, email, password, role) VALUES " +
                    "('Dr. Sanat', 'pulmonologist@hospital.com', 'pass130', 'doctor') " +
                    "ON CONFLICT (email) DO NOTHING;");
            statement.execute("INSERT INTO doctors (user_id, specialization) " +
                    "SELECT id, 'Pulmonology' FROM users WHERE email = 'pulmonologist@hospital.com' " +
                    "ON CONFLICT (user_id) DO NOTHING;");

            statement.execute("INSERT INTO users (name, email, password, role) VALUES " +
                    "('Dr. Zukhra', 'dermatologist@hospital.com', 'pass131', 'doctor') " +
                    "ON CONFLICT (email) DO NOTHING;");
            statement.execute("INSERT INTO doctors (user_id, specialization) " +
                    "SELECT id, 'Dermatology' FROM users WHERE email = 'dermatologist@hospital.com' " +
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
