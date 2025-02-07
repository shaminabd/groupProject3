package Model;

public class Medicine {
    private int id;
    private String name;
    private String dosage;

    public Medicine(int id, String name, String dosage) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDosage() { return dosage; }

    public String getDescription() {
        return dosage;
    }
}