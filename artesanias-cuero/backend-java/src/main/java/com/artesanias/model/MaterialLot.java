package com.artesanias.model;

import java.sql.Timestamp;

public class MaterialLot {
    private int id;
    private int materialId;
    private Integer colorId;  // Nullable según DBML
    private String lotCode;
    private double qtyInitial;
    private double qtyOnHand;
    private double unitCost;
    private Timestamp createdAt;

    // Constructor vacío
    public MaterialLot() {}

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMaterialId() { return materialId; }
    public void setMaterialId(int materialId) { this.materialId = materialId; }

    public Integer getColorId() { return colorId; }
    public void setColorId(Integer colorId) { this.colorId = colorId; }

    public String getLotCode() { return lotCode; }
    public void setLotCode(String lotCode) { this.lotCode = lotCode; }

    public double getQtyInitial() { return qtyInitial; }
    public void setQtyInitial(double qtyInitial) { this.qtyInitial = qtyInitial; }

    public double getQtyOnHand() { return qtyOnHand; }
    public void setQtyOnHand(double qtyOnHand) { this.qtyOnHand = qtyOnHand; }

    public double getUnitCost() { return unitCost; }
    public void setUnitCost(double unitCost) { this.unitCost = unitCost; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "MaterialLot{" +
               "id=" + id +
               ", materialId=" + materialId +
               ", lotCode='" + lotCode + '\'' +
               ", qtyOnHand=" + qtyOnHand +
               '}';
    }
}