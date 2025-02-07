package Repositories.Impl;

import Config.DatabaseConnection;
import Config.IDB;
import Model.*;
import Repositories.UserRepository;

import java.sql.*;

public class UserRepositoryImpl implements UserRepository {
    private final IDB dbConnection;

    public UserRepositoryImpl(IDB dbConnection) {
        this.dbConnection = new DatabaseConnection();
    }

    @Override
    public void addUser(User user) {
        String query = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
        try (Connection connection = dbConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String role = rs.getString("role");

                switch (role.toLowerCase()) {
                    case "admin":
                        return new Admin(id, name, email, password, role);
                    case "doctor":
                        String specialization = getDoctorSpecialization(id, connection);
                        return new Doctor(id, name, email, password, specialization);
                    case "patient":
                        String healthHistory = getPatientHealthHistory(id, connection);
                        return new Patient(id, name, email, password, healthHistory);
                    default:
                        return new User(id, name, email, password, role);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getPatientHealthHistory(int patientId, Connection connection) {
        String query = "SELECT health_history FROM patients WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("health_history");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "No history"; // Default if no health history found
    }

    private String getDoctorSpecialization(int doctorId, Connection connection) {
        String query = "SELECT specialization FROM doctors WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("specialization");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Unknown"; // Default if specialization not found
    }


    @Override
    public int getNextUserId() {
        return 0;
    }

}
