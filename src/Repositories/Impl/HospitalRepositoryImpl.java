package Repositories.Impl;

import Model.Hospital;
import Repositories.HospitalRepository;
import java.util.ArrayList;
import java.util.List;

public class HospitalRepositoryImpl implements HospitalRepository {
    private List<Hospital> hospitals = new ArrayList<>();

    @Override
    public void addHospital(Hospital hospital) {
        hospitals.add(hospital);
    }

    @Override
    public Hospital getHospitalById(int id) {
        return hospitals.stream().filter(h -> h.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Hospital> getAllHospitals() {
        return hospitals;
    }
}
