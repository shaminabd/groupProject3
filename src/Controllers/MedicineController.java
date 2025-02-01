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
        List<Medicine> medicines = medicineService.getAllMedicines();
        for (Medicine medicine : medicines) {
            System.out.println("ID: " + medicine.getId() + ", Name: " + medicine.getName() + ", Dosage: " + medicine.getDosage());
        }
    }

    // Remove medicine by ID
    public void removeMedicine(int medicineId) {
        medicineService.deleteMedicine(medicineId);
        System.out.println("Medicine with ID " + medicineId + " removed successfully.");
    }
}