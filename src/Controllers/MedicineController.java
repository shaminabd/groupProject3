package Controllers;

import Model.Medicine;
import Services.MedicineService;
import java.util.List;
import Config.DatabaseInitializer;

public class MedicineController {
    private final MedicineService medicineService;

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
            System.out.println("\nAvailable Medicines:");
            for (Medicine medicine : medicines) {
                System.out.println("ID: " + medicine.getId() + ", Name: " + medicine.getName() + ", Dosage: " + medicine.getDosage());
            }
        }
    }

    public void removeMedicine(int medicineId) {
        medicineService.deleteMedicine(medicineId);
        System.out.println("Medicine with ID " + medicineId + " removed successfully.");
    }

    public void giveMedicineToPatient(int patientId, int medicineId) {
        // Logic for giving medicine to patient
    }
}
