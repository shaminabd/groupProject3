package Model;

public class Patient extends User {
    private String medicalHistory;

    public Patient(int id, String name, String email, String password, String medicalHistory) {
        super(id, name, email, password, "patient");
        this.medicalHistory = medicalHistory;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }
}