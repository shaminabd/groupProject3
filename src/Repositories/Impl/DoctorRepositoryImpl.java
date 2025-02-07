package Repositories.Impl;

import Config.DatabaseConnection;
import Config.IDB;
import Model.Doctor;
import Repositories.DoctorRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepositoryImpl implements DoctorRepository {
    private final IDB dbConnection;

    public DoctorRepositoryImpl(IDB dbConnection) {
        this.dbConnection = new DatabaseConnection();
    }

    @Override
    public void addDoctor(Doctor doctor) {
        String query = "INSERT INTO doctors (name, email, password, role,specialization) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getEmail());
            stmt.setString(3, doctor.getPassword());
            stmt.setString(4, "doctor");
            stmt.setString(5, doctor.getSpecialization());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Doctor getDoctorById(int id) {
        String query = "SELECT * FROM doctors WHERE id = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Doctor(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"),rs.getString("specialization"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT d.id, u.name, u.email, u.password, d.specialization " +
                "FROM doctors d " +
                "JOIN users u ON d.user_id = u.id";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");  // Now exists in the ResultSet
                String email = rs.getString("email");
                String password = rs.getString("password");
                String specialization = rs.getString("specialization");
                doctors.add(new Doctor(id, name, email, password, specialization));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }


    @Override
    public void updateDoctor(Doctor doctor) {
        String query = "UPDATE doctors SET name = ?, email = ?, password = ?, specialization = ? WHERE id = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getEmail());
            stmt.setString(3, doctor.getPassword());
            stmt.setString(4, doctor.getSpecialization());
            stmt.setInt(5, doctor.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDoctor(int id) {
        String query = "DELETE FROM doctors WHERE id = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Doctor getDoctorByName(String name) {
        return null;
    }

    @Override
    public void deleteAllDoctors() {

    }
}
