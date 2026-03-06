package com.artesanias.model;

import java.sql.Timestamp;

public class Material {
    private int id;
    private String name;
    private String category;
    private String baseUnit;
    private double stockMin;
    private boolean active;
    private Timestamp createdAt;

    // Constructor vacío
    public Material() {}

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getBaseUnit() { return baseUnit; }
    public void setBaseUnit(String baseUnit) { this.baseUnit = baseUnit; }

    public double getStockMin() { return stockMin; }
    public void setStockMin(double stockMin) { this.stockMin = stockMin; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Material{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", category='" + category + '\'' +
               ", baseUnit='" + baseUnit + '\'' +
               '}';
    }
}