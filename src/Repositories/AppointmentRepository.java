package Repositories;

import Model.Appointment;
import java.util.List;

public interface AppointmentRepository {
    List<Appointment> getAppointmentsByPatient(int patientId);

    void deleteAppointment(int appointmentId);

    List<Appointment> getAllAppointments();

    void addAppointment(Appointment appointment);
}