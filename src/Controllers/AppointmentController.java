package Controllers;

import Model.Appointment;
import Services.AppointmentService;

import java.time.LocalDate;
import java.util.List;

public class AppointmentController {
    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public void bookAppointment(int doctorId, int patientId, LocalDate date) {
        Appointment appointment = new Appointment(0, doctorId, patientId, date);
        appointmentService.bookAppointment(appointment);
        System.out.println("Appointment booked with Doctor ID: " + doctorId + " for Patient ID: " + patientId + " and Date: " + date);
    }

    public void viewAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        for (Appointment appointment : appointments) {
            System.out.println("ID: " + appointment.getId() + ", Patient ID: " + appointment.getPatientId() + ", Doctor ID: " + appointment.getDoctorId() + ", Date: " + appointment.getDate());
        }
    }


    public void removeAppointment(int appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
        System.out.println("Appointment with ID " + appointmentId + " removed successfully.");
    }
}