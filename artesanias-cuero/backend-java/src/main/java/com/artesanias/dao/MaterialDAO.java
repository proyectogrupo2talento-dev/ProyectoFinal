package com.artesanias.dao;

import com.artesanias.model.Material;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MaterialDAO {
    
    private final DataSource dataSource;

    // Spring inyecta el DataSource automáticamente
    public MaterialDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Material> getAll() {
        List<Material> materiales = new ArrayList<>();
        String sql = "SELECT id, name, category, base_unit, stock_min, active, created_at FROM material";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Material m = new Material();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setCategory(rs.getString("category"));
                m.setBaseUnit(rs.getString("base_unit"));
                m.setStockMin(rs.getDouble("stock_min"));
                m.setActive(rs.getBoolean("active"));
                m.setCreatedAt(rs.getTimestamp("created_at"));
                materiales.add(m);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener materiales: " + e.getMessage());
        }

        return materiales;
    }

    public Material getById(int id) {
        String sql = "SELECT * FROM material WHERE id = ?";
        Material material = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                material = new Material();
                material.setId(rs.getInt("id"));
                material.setName(rs.getString("name"));
                material.setCategory(rs.getString("category"));
                material.setBaseUnit(rs.getString("base_unit"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener material: " + e.getMessage());
        }

        return material;
    }
}