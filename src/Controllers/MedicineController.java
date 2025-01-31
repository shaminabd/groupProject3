package Controllers;

import Model.Medicine;
import Services.MedicineService;

public class MedicineController {
    private MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    public void addMedicine(Medicine medicine) {
        medicineService.addMedicine(medicine);
    }
}