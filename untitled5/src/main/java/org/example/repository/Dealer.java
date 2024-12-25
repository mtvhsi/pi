package org.example.repository;

import org.example.database.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Dealer {

    public void create(org.example.entity.Dealer dealership) {
        String sql = "INSERT INTO Dealership (name) VALUES (?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dealership.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<org.example.entity.Dealer> findAll() {
        List<org.example.entity.Dealer> dealerships = new ArrayList<>();
        String sql = "SELECT * FROM Dealership";
        try (Connection conn = DBConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                org.example.entity.Dealer dealership = new org.example.entity.Dealer(rs.getString("name"));
                dealerships.add(dealership);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dealerships;
    }

    public Optional<org.example.entity.Dealer> findByName(String name) {
        String sql = "SELECT * FROM Dealership WHERE name = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                org.example.entity.Dealer dealership = new org.example.entity.Dealer(rs.getString("name"));
                return Optional.of(dealership);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void update(org.example.entity.Dealer dealership) {
        String sql = "UPDATE Dealership SET name = ? WHERE name = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dealership.getName());
            stmt.setString(2, dealership.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String name) {
        String sql = "DELETE FROM Dealership WHERE name = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
