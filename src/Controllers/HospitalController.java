package Controllers;

import Model.Hospital;
import Services.HospitalService;

public class HospitalController {
    private HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    public void addHospital(Hospital hospital) {
        hospitalService.addHospital(hospital);
    }

    public void addDoctorToHospital(int addHospitalId, int addDoctorId) {
    }

    public void removeDoctorFromHospital(int removeHospitalId, int removeDoctorId) {

    }

    public void removeHospital(int hospitalId) {
    }
}