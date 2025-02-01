package Model;

public class Doctor extends User {
    private String specialization;

    public Doctor(int id, String name, String email, String password, String specialization) {
        super(id, name, email, password, "doctor");
        this.specialization = specialization;
    }

    public String getSpecialization() { return specialization; }
}