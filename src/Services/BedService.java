package Services;

import Model.Bed;

import Repositories.BedRepository;
import java.util.List;

public class BedService {
    private BedRepository bedRepository;

    public BedService(BedRepository bedRepository) {
        this.bedRepository = bedRepository;
    }

    public void addBed(Bed bed) {
        bedRepository.addBed(bed);
    }
}
