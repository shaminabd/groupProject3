package Repositories.Impl;

import Config.IDB;
import Config.DatabaseConnection;
import Model.Medicine;
import Repositories.MedicineRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineRepositoryImpl implements MedicineRepository {
    private final IDB dbConnection;

    public MedicineRepositoryImpl(IDB dbConnection) {
        this.dbConnection = dbConnection;  // Uses IDB like your other repositories
    }

    @Override
    public void addMedicine(Medicine medicine) {
        String query = "INSERT INTO medicines (name, dosage) VALUES (?, ?)";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, medicine.getName());
            stmt.setString(2, medicine.getDosage());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Medicine> getAllMedicines() {
        List<Medicine> medicines = new ArrayList<>();
        String query = "SELECT * FROM medicines";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String dosage = rs.getString("dosage");
                medicines.add(new Medicine(id, name, dosage));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicines;
    }

    @Override
    public void deleteMedicine(int medicineId) {
        String query = "DELETE FROM medicines WHERE id = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, medicineId);
            stmt.executeUpdate();
            System.out.println("Medicine with ID " + medicineId + " deleted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Medicine getMedicineById(int medicineId) {  // If this method exists in your interface
        String query = "SELECT * FROM medicines WHERE id = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, medicineId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Medicine(rs.getInt("id"), rs.getString("name"), rs.getString("dosage"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
