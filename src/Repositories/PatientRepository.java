package Repositories;

import Model.Patient;
import java.util.List;

public interface PatientRepository {
    void addPatient(Patient patient);
    Patient getPatientById(int id);
    List<Patient> getAllPatients();

    void deletePatient(int patientId);
}