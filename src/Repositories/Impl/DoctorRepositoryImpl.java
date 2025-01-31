package Repositories.Impl;

import Model.Doctor;
import Repositories.DoctorRepository;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepositoryImpl implements DoctorRepository {
    private List<Doctor> doctors = new ArrayList<>();

    @Override
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    @Override
    public Doctor getDoctorById(int id) {
        return doctors.stream().filter(d -> d.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctors;
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        deleteDoctor(doctor.getId());
        doctors.add(doctor);
    }

    @Override
    public void deleteDoctor(int id) {
        doctors.removeIf(d -> d.getId() == id);
    }
}
