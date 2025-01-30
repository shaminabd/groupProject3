package Repositories;

import Model.Appointment;
import java.util.List;

public interface AppointmentRepository {
    void bookAppointment(Appointment appointment);
    List<Appointment> getAppointmentsByPatient(int patientId);
}