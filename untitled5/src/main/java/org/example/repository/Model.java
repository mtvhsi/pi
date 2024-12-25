package org.example.repository;

import org.example.database.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Model {

    public void create(org.example.entity.Model carModel) {
        String sql = "INSERT INTO CarModel (id, brand, model, countryOrigin, countryCode) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carModel.getId());
            stmt.setString(2, carModel.getBrand());
            stmt.setString(3, carModel.getModel());
            stmt.setString(4, carModel.getCountryOrigin());
            stmt.setString(5, carModel.getCountryCode());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<org.example.entity.Model> findAll() {
        List<org.example.entity.Model> carModels = new ArrayList<>();
        String sql = "SELECT * FROM CarModel";
        try (Connection conn = DBConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                org.example.entity.Model carModel = new org.example.entity.Model(
                        rs.getInt("id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("countryOrigin"),
                        rs.getString("countryCode"));
                carModels.add(carModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carModels;
    }

    public Optional<org.example.entity.Model> findById(int id) {
        String sql = "SELECT * FROM CarModel WHERE id = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                org.example.entity.Model carModel = new org.example.entity.Model(
                        rs.getInt("id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("countryOrigin"),
                        rs.getString("countryCode"));
                return Optional.of(carModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void update(org.example.entity.Model carModel) {
        String sql = "UPDATE CarModel SET brand = ?, model = ?, countryOrigin = ?, countryCode = ? WHERE id = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, carModel.getBrand());
            stmt.setString(2, carModel.getModel());
            stmt.setString(3, carModel.getCountryOrigin());
            stmt.setString(4, carModel.getCountryCode());
            stmt.setInt(5, carModel.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM CarModel WHERE id = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
