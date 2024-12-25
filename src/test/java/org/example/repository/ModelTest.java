package org.example.repository;

import org.example.database.DBConnect;
import org.example.database.DB;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModelTest {
    private Model model;
    private Connection connection;

    @BeforeAll
    public void setup() throws SQLException {
        connection = DBConnect.getConnection();
        new DB().setupDatabase();
        model = new Model();
    }

    @BeforeEach
    public void clearDatabase() throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM CarModel")) {
            stmt.executeUpdate();
        }
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM Dealership")) {
            stmt.executeUpdate();
        }
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM Car")) {
            stmt.executeUpdate();
        }
    }

    @Test
    public void testCreate() throws SQLException {
        org.example.entity.Model carModel = new org.example.entity.Model(1, "Toyota", "Camry", "Japan", "JP");
        model.create(carModel);

        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM CarModel WHERE id = ?")) {
            stmt.setInt(1, 1);
            try (ResultSet rs = stmt.executeQuery()) {
                assertTrue(rs.next());
                assertEquals("Toyota", rs.getString("brand"));
            }
        }
    }

    @Test
    public void testFindAll() throws SQLException {
        List<org.example.entity.Model> expectedModels = new ArrayList<>();
        expectedModels.add(new org.example.entity.Model(1, "Toyota", "Camry", "Japan", "JP"));
        model.create(expectedModels.get(0));

        List<org.example.entity.Model> carModels = model.findAll();

        assertEquals(expectedModels.size(), carModels.size());
        assertEquals(expectedModels.get(0).getBrand(), carModels.get(0).getBrand());
    }

    @Test
    public void testFindById() throws SQLException {
        org.example.entity.Model expectedModel = new org.example.entity.Model(1, "Toyota", "Camry", "Japan", "JP");
        model.create(expectedModel);

        Optional<org.example.entity.Model> foundModel = model.findById(1);

        assertTrue(foundModel.isPresent());
        assertEquals(expectedModel.getBrand(), foundModel.get().getBrand());
    }

    @Test
    public void testUpdate() throws SQLException {
        org.example.entity.Model carModel = new org.example.entity.Model(1, "Toyota", "Camry", "Japan", "JP");
        model.create(carModel);

        carModel.setBrand("Honda");
        model.update(carModel);

        Optional<org.example.entity.Model> foundModel = model.findById(1);
        assertTrue(foundModel.isPresent());
        assertEquals("Honda", foundModel.get().getBrand());
    }

    @Test
    public void testDelete() throws SQLException {
        org.example.entity.Model carModel = new org.example.entity.Model(1, "Toyota", "Camry", "Japan", "JP");
        model.create(carModel);

        model.delete(1);

        Optional<org.example.entity.Model> foundModel = model.findById(1);
        assertFalse(foundModel.isPresent());
    }

    @AfterAll
    public void tearDown() throws SQLException {
        resetDatabase();

        if (connection != null) {
            connection.close();
        }
    }

    private void resetDatabase() throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("SET REFERENTIAL_INTEGRITY FALSE; DROP ALL OBJECTS; SET REFERENTIAL_INTEGRITY TRUE;")) {
            stmt.executeUpdate();
        }
    }
}
