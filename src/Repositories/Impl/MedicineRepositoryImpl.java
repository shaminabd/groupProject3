package Repositories.Impl;

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
    private List<Medicine> medicines = new ArrayList<>();

    private Connection connection;

    public MedicineRepositoryImpl() {
        try {
            // Establish a connection to the database
            this.connection =   DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addMedicine(Medicine medicine) {
        String query = "INSERT INTO medicines (name, dosage) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, medicine.getName());
            stmt.setString(2, medicine.getDosage());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Medicine getMedicineById(int id) {
        String query = "SELECT * FROM medicines WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Medicine(rs.getInt("id"), rs.getString("name"), rs.getString("dosage"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Medicine> getAllMedicines() {
        List<Medicine> medicines = new ArrayList<>();
        String query = "SELECT * FROM medicines";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                medicines.add(new Medicine(rs.getInt("id"), rs.getString("name"), rs.getString("dosage")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicines;
    }

    // Remove medicine by ID
    @Override
    public void deleteMedicine(int medicineId) {
        String query = "DELETE FROM medicines WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, medicineId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
