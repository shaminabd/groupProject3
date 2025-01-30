package Model;

public class Bed {
    private int id;
    private boolean isOccupied;

    public Bed(int id, boolean isOccupied) {
        this.id = id;
        this.isOccupied = isOccupied;
    }

    public int getId() { return id; }
    public boolean isOccupied() { return isOccupied; }
}