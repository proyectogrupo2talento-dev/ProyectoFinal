package com.artesanias.dao;

import com.artesanias.model.MaterialLot;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MaterialLotDAO {
    
    private final DataSource dataSource;

    public MaterialLotDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Obtener todos los lotes
    public List<MaterialLot> getAll() {
        List<MaterialLot> lots = new ArrayList<>();
        String sql = "SELECT id, material_id, color_id, lot_code, qty_initial, qty_on_hand, unit_cost, created_at FROM material_lot";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                MaterialLot lot = new MaterialLot();
                lot.setId(rs.getInt("id"));
                lot.setMaterialId(rs.getInt("material_id"));
                
                // color_id puede ser NULL
                int colorId = rs.getInt("color_id");
                lot.setColorId(rs.wasNull() ? null : colorId);
                
                lot.setLotCode(rs.getString("lot_code"));
                lot.setQtyInitial(rs.getDouble("qty_initial"));
                lot.setQtyOnHand(rs.getDouble("qty_on_hand"));
                lot.setUnitCost(rs.getDouble("unit_cost"));
                lot.setCreatedAt(rs.getTimestamp("created_at"));
                lots.add(lot);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener lotes: " + e.getMessage());
        }

        return lots;
    }

    // Obtener un lote por ID
    public MaterialLot getById(int id) {
        String sql = "SELECT * FROM material_lot WHERE id = ?";
        MaterialLot lot = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                lot = new MaterialLot();
                lot.setId(rs.getInt("id"));
                lot.setMaterialId(rs.getInt("material_id"));
                
                int colorId = rs.getInt("color_id");
                lot.setColorId(rs.wasNull() ? null : colorId);
                
                lot.setLotCode(rs.getString("lot_code"));
                lot.setQtyInitial(rs.getDouble("qty_initial"));
                lot.setQtyOnHand(rs.getDouble("qty_on_hand"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener lote: " + e.getMessage());
        }

        return lot;
    }

    // Crear nuevo lote
    public MaterialLot create(MaterialLot lot) {
        String sql = "INSERT INTO material_lot (material_id, color_id, lot_code, qty_initial, qty_on_hand, unit_cost) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, lot.getMaterialId());
            
            if (lot.getColorId() != null) {
                pstmt.setInt(2, lot.getColorId());
            } else {
                pstmt.setNull(2, Types.INTEGER);
            }
            
            pstmt.setString(3, lot.getLotCode());
            pstmt.setDouble(4, lot.getQtyInitial());
            pstmt.setDouble(5, lot.getQtyOnHand());
            pstmt.setDouble(6, lot.getUnitCost());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                lot.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            System.err.println("Error al crear lote: " + e.getMessage());
        }

        return lot;
    }
}