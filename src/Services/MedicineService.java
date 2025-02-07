package Services;

import Model.Medicine;
import Repositories.MedicineRepository;
import java.util.List;

public class MedicineService {
    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public List<Medicine> getAllMedicines() {
        return medicineRepository.getAllMedicines();
    }

    public void addMedicine(Medicine medicine) {
        // Logic for adding medicine
    }

    public void deleteMedicine(int medicineId) {
        // Logic for deleting medicine
    }
}
