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

    public void viewDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        for (Doctor doctor : doctors) {
            System.out.println("ID: " + doctor.getId() + ", Name: " + doctor.getName() + ", Specialization: " + doctor.getSpecialization());
        }
    }


    public void removeDoctor(int doctorId) {
        doctorService.deleteDoctor(doctorId);
        System.out.println("Doctor with ID " + doctorId + " removed successfully.");
    }
}