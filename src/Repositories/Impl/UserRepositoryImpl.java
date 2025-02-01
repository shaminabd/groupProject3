package Repositories.Impl;

import Config.DatabaseConnection;
import Model.*;
import Repositories.UserRepository;

import java.sql.*;

public class UserRepositoryImpl implements UserRepository {
    private Connection connection;

    public UserRepositoryImpl() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) {
        String query = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
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
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
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
                        String specialization = rs.getString("specialization");
                        return new Doctor(id, name, email, password, specialization);
                    case "patient":
                        String healthHistory = rs.getString("health_history");
                        return new Patient(id, name, email, password, healthHistory);
                    case "nurse":
                        return new Nurse(id, name, email, password);
                    case "pharmacist":
                        return new Pharmacist(id, name, email, password);
                    default:
                        return new User(id, name, email, password, role);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public int getNextUserId() {
        return 0;
    }

}
