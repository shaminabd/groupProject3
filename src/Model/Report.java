package Model;

public class Report {
    private int id;
    private int doctorId;
    private int patientId;
    private String details;

    public Report(int id, int doctorId, int patientId, String details) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.details = details;
    }

    public int getId() { return id; }
    public int getDoctorId() { return doctorId; }
    public int getPatientId() { return patientId; }
    public String getDetails() { return details; }
}