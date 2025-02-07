package Repositories.Impl;

import Config.IDB;
import Model.Doctor;
import Model.Report;
import Repositories.ReportRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportRepositoryImpl implements ReportRepository {
    private final IDB dbConnection;

    public ReportRepositoryImpl(IDB dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void createReport(Report report) {
        String query = "INSERT INTO reports (doctor_id, patient_id, details) VALUES (?, ?, ?)";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
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
        List<Report> reports = new ArrayList<>();
        String query = "SELECT * FROM reports WHERE patient_id = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reports.add(new Report(rs.getInt("id"), rs.getInt("doctor_id"), rs.getInt("patient_id"), rs.getString("details")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    @Override
    public List<Report> getReportsByDoctor(int doctorId) {
        List<Report> reports = new ArrayList<>();
        String query = "SELECT * FROM reports WHERE doctor_id = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reports.add(new Report(rs.getInt("id"), rs.getInt("doctor_id"), rs.getInt("patient_id"), rs.getString("details")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    // Delete a report by ID
    public void deleteReport(int reportId) {
        String query = "DELETE FROM reports WHERE id = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, reportId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
