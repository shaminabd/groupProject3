package Repositories;

import Model.Medicine;
import java.util.List;

public interface MedicineRepository {
    void addMedicine(Medicine medicine);
    Medicine getMedicineById(int id);
    List<Medicine> getAllMedicines();

    void deleteMedicine(int medicineId);
}