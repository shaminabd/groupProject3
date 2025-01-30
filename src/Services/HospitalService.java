package Services;

import Model.Hospital;

import Repositories.HospitalRepository;
import java.util.List;

public class HospitalService {
    private HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public void addHospital(Hospital hospital) {
        hospitalRepository.addHospital(hospital);
    }
}
