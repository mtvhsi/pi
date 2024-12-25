package org.example.repository;

import org.example.database.DBConnect;
import org.example.entity.Dealer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Car {

    public void create(Dealer.Car car) {
        String sql = "INSERT INTO Car (id, carModelId, dealershipName, state, configuration, color, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, car.getId());
            stmt.setInt(2, car.getCarModelId());
            stmt.setString(3, car.getDealershipName());
            stmt.setString(4, car.getState());
            stmt.setString(5, car.getConfiguration());
            stmt.setString(6, car.getColor());
            stmt.setDouble(7, car.getPrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Dealer.Car> findAll() {
        List<Dealer.Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM Car";
        try (Connection conn = DBConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Dealer.Car car = new Dealer.Car(
                        rs.getInt("id"),
                        rs.getInt("carModelId"),
                        rs.getString("dealershipName"),
                        rs.getString("state"),
                        rs.getString("configuration"),
                        rs.getString("color"),
                        rs.getDouble("price"));
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public Optional<Dealer.Car> findById(int id) {
        String sql = "SELECT * FROM Car WHERE id = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Dealer.Car car = new Dealer.Car(
                        rs.getInt("id"),
                        rs.getInt("carModelId"),
                        rs.getString("dealershipName"),
                        rs.getString("state"),
                        rs.getString("configuration"),
                        rs.getString("color"),
                        rs.getDouble("price"));
                return Optional.of(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void update(Dealer.Car car) {
        String sql = "UPDATE Car SET carModelId = ?, dealershipName = ?, state = ?, configuration = ?, color = ?, price = ? WHERE id = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, car.getCarModelId());
            stmt.setString(2, car.getDealershipName());
            stmt.setString(3, car.getState());
            stmt.setString(4, car.getConfiguration());
            stmt.setString(5, car.getColor());
            stmt.setDouble(6, car.getPrice());
            stmt.setInt(7, car.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Car WHERE id = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
