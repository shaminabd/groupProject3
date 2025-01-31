package Controllers;

import Model.Doctor;
import Services.DoctorService;
import java.util.List;

public class DoctorController {
    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public void registerDoctor(Doctor doctor) {
        doctorService.registerDoctor(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }
}