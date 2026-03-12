package com.grupo2tech.artesaniascuero.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @JsonProperty("order_number")
    @NotBlank(message = "El numero de orden es obligatorio")
    private String orderNumber;

    @Column(nullable = false)
    @JsonProperty("customer_id")
    private long customerId;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "material_id", nullable = false)
    @NotNull(message = "El material es obligatorio")
    private Material material;

    @Column(nullable = false)
    @DecimalMin(value = "0.01", message = "La cantidad debe ser mayor a cero")
    private double quantity;

    @Column(nullable = false)
    private String status; 

    @Column(nullable = false)
    @JsonProperty("promised_date")
    private String promisedDate;

    @Column(nullable = false)
    private String notes;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("order_number")
    public String getOrderNumber() {
        return orderNumber;
    }

    @JsonProperty("order_number")
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @JsonProperty("customer_id")
    public long getCustomerId() {
        return customerId;
    }

    @JsonProperty("customer_id")
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("promised_date")
    public String getPromisedDate() {
        return promisedDate;
    }

    @JsonProperty("promised_date")
    public void setPromisedDate(String promisedDate) {
        this.promisedDate = promisedDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
