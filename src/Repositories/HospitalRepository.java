package Repositories;

import Model.Hospital;
import java.util.List;

public interface HospitalRepository {
    void addHospital(Hospital hospital);
    Hospital getHospitalById(int id);
    List<Hospital> getAllHospitals();
}