package Model;

import java.time.LocalDate;

public class Appointment {
    private int id;
    private int doctorId;
    private int patientId;
    private LocalDate date;

    public Appointment(int id, int doctorId, int patientId, LocalDate date) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = (date);
    }

    public int getId() { return id; }
    public int getDoctorId() { return doctorId; }
    public int getPatientId() { return patientId; }
    public LocalDate getDate() { return date; }
}