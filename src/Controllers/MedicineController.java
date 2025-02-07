package Controllers;

import Model.Doctor;
import Model.Medicine;
import Services.MedicineService;

import java.util.List;

public class MedicineController {
    private MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    public void addMedicine(Medicine medicine) {
        medicineService.addMedicine(medicine);
    }

    public void viewMedicines() {
        List<Medicine> medicines = medicineService.getAllMedicines();
        if (medicines.isEmpty()) {
            System.out.println("No medicines available.");
        } else {
            System.out.println("List of available medicines:");
            for (Medicine medicine : medicines) {
                System.out.println("ID: " + medicine.getId() + ", Name: " + medicine.getName() + ", Description: " + medicine.getDescription());
            }
        }
    }



    public void removeMedicine(int medicineId) {
        medicineService.deleteMedicine(medicineId);
        System.out.println("Medicine with ID " + medicineId + " removed successfully.");
    }

    public void giveMedicineToPatient(int patientId, int medicineId) {
    }

//    public void giveMedicineToPatient(int doctorId, int patientId, int medicineId) {
//        Doctor doctor = medicineService.getDoctorById(doctorId); // Assuming you have a method to get the doctor by ID
//        if (doctor != null) {
//            if ("Psychologist".equalsIgnoreCase(doctor.getSpecialization())) {
//                System.out.println("Doctor " + doctor.getName() + " cannot prescribe medicine. Specialization: Psychologist.");
//            } else {
//                // Proceed with giving the medicine to the patient
//                medicineService.giveMedicineToPatient(patientId, medicineId);
//                System.out.println("Medicine with ID " + medicineId + " given to patient with ID " + patientId + ".");
//            }
//        } else {
//            System.out.println("Doctor with ID " + doctorId + " not found.");
//        }
//    }
}