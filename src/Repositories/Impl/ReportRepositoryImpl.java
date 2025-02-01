package Repositories.Impl;

import Config.DatabaseConnection;
import Model.Report;
import Repositories.ReportRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportRepositoryImpl implements ReportRepository {
    private Connection connection;

    public ReportRepositoryImpl() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createReport(Report report) {
        String query = "INSERT INTO reports (doctor_id, patient_id, details) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, report.getDoctorId());
            stmt.setInt(2, report.getPatientId());
            stmt.setString(3, report.getDetails());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Report> getReportsByPatient(int patientId) {
        List<Report> result = new ArrayList<>();
        String query = "SELECT * FROM reports WHERE patient_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new Report(rs.getInt("id"), rs.getInt("doctor_id"), rs.getInt("patient_id"), rs.getString("details")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
