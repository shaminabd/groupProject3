package Controllers;

import Model.Appointment;
import Services.AppointmentService;

import java.util.List;

public class AppointmentController {
    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public void bookAppointment(Appointment appointment) {
        appointmentService.bookAppointment(appointment);
    }

    public void viewAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        for (Appointment appointment : appointments) {
            System.out.println("ID: " + appointment.getId() + ", Patient ID: " + appointment.getPatientId() + ", Doctor ID: " + appointment.getDoctorId() + ", Date: " + appointment.getDate());
        }
    }

    // Remove appointment by ID
    public void removeAppointment(int appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
        System.out.println("Appointment with ID " + appointmentId + " removed successfully.");
    }
}