package Repositories.Impl;

import Model.Appointment;
import Repositories.AppointmentRepository;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepositoryImpl implements AppointmentRepository {
    private List<Appointment> appointments = new ArrayList<>();

    @Override
    public void bookAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    @Override
    public List<Appointment> getAppointmentsByPatient(int patientId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId() == patientId) {
                result.add(appointment);
            }
        }
        return result;
    }
}
