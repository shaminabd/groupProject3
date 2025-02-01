package Controllers;

import Model.Patient;
import Services.PatientService;
import java.util.List;

public class PatientController {
    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    public void registerPatient(Patient patient) {
        patientService.registerPatient(patient);
    }

    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }
    public void viewPatients() {
        List<Patient> patients = patientService.getAllPatients();
        for (Patient patient : patients) {
            System.out.println("ID: " + patient.getId() + ", Name: " + patient.getName() + ", Medical History: " + patient.getMedicalHistory());
        }
    }


    public void removePatient(int patientId) {
        patientService.deletePatient(patientId);
        System.out.println("Patient with ID " + patientId + " removed successfully.");
    }
}