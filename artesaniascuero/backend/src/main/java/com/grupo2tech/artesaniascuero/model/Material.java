package com.grupo2tech.artesaniascuero.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;

@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "material", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private MaterialLot materialLot;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "La categoria es obligatoria")
    private String category;

    @Column(nullable = false)
    @JsonProperty("base_unit")
    @NotBlank(message = "La unidad base es obligatoria")
    private String base_unit;

    @Column(nullable = false)
    @JsonProperty("stock_min")
    @DecimalMin(value = "0.0", inclusive = true, message = "El stock minimo no puede ser negativo")
    private double stock_min;

    @Column(nullable = false)
    private boolean active = true; 

    // Getters and Setters
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("base_unit")
    public String getBaseUnit() {
        return base_unit;
    }

    @JsonProperty("base_unit")
    public void setBaseUnit(String base_unit) {
        this.base_unit = base_unit;
    }

    @JsonProperty("stock_min")
    public double getStockMin() {
        return stock_min;
    }

    @JsonProperty("stock_min")
    public void setStockMin(double stock_min) {
        this.stock_min = stock_min;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
