package Controllers;

import Model.Appointment;
import Services.AppointmentService;

public class AppointmentController {
    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public void bookAppointment(Appointment appointment) {
        appointmentService.bookAppointment(appointment);
    }
}