package Repositories.Impl;

import Model.Medicine;
import Repositories.MedicineRepository;
import java.util.ArrayList;
import java.util.List;

public class MedicineRepositoryImpl implements MedicineRepository {
    private List<Medicine> medicines = new ArrayList<>();

    @Override
    public void addMedicine(Medicine medicine) {
        medicines.add(medicine);
    }

    @Override
    public Medicine getMedicineById(int id) {
        return medicines.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Medicine> getAllMedicines() {
        return medicines;
    }
}
