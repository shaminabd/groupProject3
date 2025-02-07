package Repositories;

import Model.Doctor;
import java.util.List;

public interface DoctorRepository {
    void addDoctor(Doctor doctor);
    Doctor getDoctorById(int id);
    List<Doctor> getAllDoctors();
    void updateDoctor(Doctor doctor);
    void deleteDoctor(int id);
    Doctor getDoctorByName(String name);
    void deleteAllDoctors();

}