package Repositories.Impl;

import Config.DatabaseConnection;
import Model.Bed;
import Repositories.BedRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BedRepositoryImpl implements BedRepository {
    private Connection connection;

    public BedRepositoryImpl() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addBed(Bed bed) {
        String query = "INSERT INTO beds (occupied) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setBoolean(1, bed.isOccupied());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bed getBedById(int id) {
        String query = "SELECT * FROM beds WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Bed(rs.getInt("id"), rs.getBoolean("occupied"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Bed> getAllBeds() {
        List<Bed> beds = new ArrayList<>();
        String query = "SELECT * FROM beds";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                beds.add(new Bed(rs.getInt("id"), rs.getBoolean("occupied")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beds;
    }
}
