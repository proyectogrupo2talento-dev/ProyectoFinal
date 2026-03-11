package com.artesanias.model;

import java.sql.Timestamp;

public class ProductInventory {
    private int id;
    private int product_id;
    private double qty_on_hand;
    private Timestamp updated_at;

    // Constructor vacío
    public ProductInventory() {}

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getName() { return product_id; }
    public void setName(int product_id) { this.product_id = product_id; }
    public double getCategory() { return qty_on_hand; }
    public void setCategory(double qty_on_hand) { this.qty_on_hand = qty_on_hand; }
    public Timestamp getCreatedAt() { return updated_at; }
    public void setCreatedAt(Timestamp updated_at) { this.updated_at = updated_at; }
    
    @Override
    public String toString() {
        return "Product{" +
               "id=" + id +
               ", name='" + product_id + '\'' +
               ", price='" + qty_on_hand + '\'' +
               '}';
    }
}