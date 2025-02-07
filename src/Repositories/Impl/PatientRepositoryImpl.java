package Repositories.Impl;

import Config.DatabaseConnection;
import Config.IDB;
import Model.Patient;
import Repositories.PatientRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Config.DatabaseConnection.*;

public class PatientRepositoryImpl implements PatientRepository {
    private final IDB dbConnection;

    public PatientRepositoryImpl(IDB dbConnection) {
        this.dbConnection = new DatabaseConnection();
    }

    @Override
    public void addPatient(Patient patient) {
        String query = "INSERT INTO patients (user_id, health_history) VALUES (?, ?)";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patient.getId());
            stmt.setString(2, patient.getMedicalHistory());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Patient getPatientById(int id) {
        String query = "SELECT * FROM patients WHERE id = ?";
        try (Connection connection = dbConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Patient(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"),rs.getString("medical_history"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patients";
        try (Connection connection = dbConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                patients.add(new Patient(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"),rs.getString("medical_history")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public void deletePatient(int patientId) {
        String query = "DELETE FROM patients WHERE id = ?";
        try (Connection connection = dbConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getNextUserId() {
        return 0;
    }
}
