package Repositories;

import Model.Bed;
import java.util.List;

public interface BedRepository {
    void addBed(Bed bed);
    Bed getBedById(int id);
    List<Bed> getAllBeds();
}