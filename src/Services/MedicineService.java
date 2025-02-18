package Services;

import Model.Doctor;
import Model.Medicine;
import Repositories.MedicineRepository;
import java.util.List;

public class MedicineService {
    private MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public void addMedicine(Medicine medicine) {
        medicineRepository.addMedicine(medicine);
    }

    public List<Medicine> getAllMedicines() {
        return medicineRepository.getAllMedicines();
    }


    public void deleteMedicine(int medicineId) {
        medicineRepository.deleteMedicine(medicineId);
    }


}