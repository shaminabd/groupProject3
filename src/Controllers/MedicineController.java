package Controllers;

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

    }


    public void removeMedicine(int medicineId) {
        medicineService.deleteMedicine(medicineId);
        System.out.println("Medicine with ID " + medicineId + " removed successfully.");
    }

    public void giveMedicineToPatient(int patientId, int medicineId) {
    }
}