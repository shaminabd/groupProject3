package Repositories.Impl;

import Config.DatabaseConnection;
import Config.IDB;
import Model.Appointment;
import Repositories.AppointmentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepositoryImpl implements AppointmentRepository {
    private final IDB dbConnection;

    public AppointmentRepositoryImpl(IDB dbConnection) {
        this.dbConnection = dbConnection;  // Use the passed dbConnection, no need to instantiate a new one
    }

    @Override
    public void addAppointment(Appointment appointment) {
        String query = "INSERT INTO appointments (patient_id, doctor_id, appointment_date) VALUES (?, ?, ?)";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appointment.getPatientId());
            stmt.setInt(2, appointment.getDoctorId());
            stmt.setDate(3, java.sql.Date.valueOf(appointment.getDate()));  // Convert LocalDate to SQL Date
            stmt.executeUpdate();
            System.out.println("Appointment booked successfully.");
        } catch (SQLException e) {
            System.err.println("Error booking appointment: " + e.getMessage());
        }
    }

    @Override
    public List<Appointment> getAppointmentsByPatient(int patientId) {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointments WHERE patient_id = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LocalDate appointmentDate = rs.getDate("appointment_date").toLocalDate();
                appointments.add(new Appointment(rs.getInt("id"), rs.getInt("doctor_id"), rs.getInt("patient_id"), appointmentDate));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching appointments for patient: " + e.getMessage());
        }
        return appointments;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointments";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LocalDate appointmentDate = rs.getDate("appointment_date").toLocalDate();
                appointments.add(new Appointment(rs.getInt("id"), rs.getInt("doctor_id"), rs.getInt("patient_id"), appointmentDate));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all appointments: " + e.getMessage());
        }
        return appointments;
    }

    @Override
    public void deleteAppointment(int appointmentId) {
        String query = "DELETE FROM appointments WHERE id = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appointmentId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Appointment with ID " + appointmentId + " deleted successfully.");
            } else {
                System.out.println("No appointment found with ID " + appointmentId);
            }
        } catch (SQLException e) {
            System.err.println("Error deleting appointment: " + e.getMessage());
        }
    }
}
