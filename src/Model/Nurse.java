package Model;

public class Nurse extends User {
    public Nurse(int id, String name, String email, String password) {
        super(id, name, email, password, "nurse");
    }
}