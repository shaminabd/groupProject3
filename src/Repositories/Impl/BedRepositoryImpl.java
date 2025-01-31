package Repositories.Impl;

import Model.Bed;
import Repositories.BedRepository;
import java.util.ArrayList;
import java.util.List;

public class BedRepositoryImpl implements BedRepository {
    private List<Bed> beds = new ArrayList<>();

    @Override
    public void addBed(Bed bed) {
        beds.add(bed);
    }

    @Override
    public Bed getBedById(int id) {
        return beds.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Bed> getAllBeds() {
        return beds;
    }
}
