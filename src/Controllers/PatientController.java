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
}