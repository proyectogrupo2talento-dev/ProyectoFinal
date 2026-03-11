package com.artesanias.model;

import java.sql.Timestamp;

public class Product {
    private int id;
    private String name;
    private double price;
    private Timestamp createdAt;

    // Constructor vacío
    public Product() {}

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getCategory() { return price; }
    public void setCategory(double price) { this.price = price; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Product{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", price='" + price + '\'' +
               '}';
    }
}