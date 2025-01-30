package Services;

import Model.Doctor;

import Repositories.DoctorRepository;
import java.util.List;

public class DoctorService {
    private DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void registerDoctor(Doctor doctor) {
        doctorRepository.addDoctor(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.getAllDoctors();
    }
}
