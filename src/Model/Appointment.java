package Model;

public class Appointment {
    private int id;
    private int doctorId;
    private int patientId;
    private String date;

    public Appointment(int id, int doctorId, int patientId, String date) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
    }

    public int getId() { return id; }
    public int getDoctorId() { return doctorId; }
    public int getPatientId() { return patientId; }
    public String getDate() { return date; }
}