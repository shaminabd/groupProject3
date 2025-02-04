package Repositories.Impl;

import Config.DatabaseConnection;
import Config.IDB;
import Model.Hospital;
import Repositories.HospitalRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalRepositoryImpl implements HospitalRepository {
    private IDB dbConnection;

    public HospitalRepositoryImpl() {
        this.dbConnection = new DatabaseConnection();
    }

    @Override
    public void addHospital(Hospital hospital) {
        String query = "INSERT INTO hospitals (name, location) VALUES (?, ?)";
        try (Connection connection = dbConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, hospital.getName());
            stmt.setString(2, hospital.getLocation());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Hospital getHospitalById(int id) {
        String query = "SELECT * FROM hospitals WHERE id = ?";
        try (Connection connection = dbConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Hospital(rs.getInt("id"), rs.getString("name"), rs.getString("location"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Hospital> getAllHospitals() {
        List<Hospital> hospitals = new ArrayList<>();
        String query = "SELECT * FROM hospitals";
        try (Connection connection = dbConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                hospitals.add(new Hospital(rs.getInt("id"), rs.getString("name"), rs.getString("location")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hospitals;
    }
}
