package Repositories.Impl;

import Model.Patient;
import Repositories.PatientRepository;
import java.util.ArrayList;
import java.util.List;

public class PatientRepositoryImpl implements PatientRepository {
    private List<Patient> patients = new ArrayList<>();

    @Override
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    @Override
    public Patient getPatientById(int id) {
        return patients.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patients;
    }
}
