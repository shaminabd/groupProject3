package Services;
import Model.Patient;
import Repositories.PatientRepository;
import java.util.List;

public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void registerPatient(Patient patient) {
        patientRepository.addPatient(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.getAllPatients();
    }
}
