package Services;

import Model.Appointment;

import Repositories.AppointmentRepository;
import java.util.List;

public class AppointmentService {
    private AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void bookAppointment(Appointment appointment) {
        appointmentRepository.bookAppointment(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.getAllAppointments();
    }

    // Delete an appointment by ID
    public void deleteAppointment(int appointmentId) {
        appointmentRepository.deleteAppointment(appointmentId);
    }
}
