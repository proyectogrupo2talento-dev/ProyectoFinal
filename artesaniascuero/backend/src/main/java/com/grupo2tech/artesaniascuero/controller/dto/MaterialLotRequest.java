package com.grupo2tech.artesaniascuero.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MaterialLotRequest {

    @NotNull(message = "El material es obligatorio")
    private Long materialId;

    @NotNull(message = "El color es obligatorio")
    @Min(value = 0, message = "El color no puede ser negativo")
    private Integer color;

    @DecimalMin(value = "0.0", inclusive = true, message = "La cantidad no puede ser negativa")
    private double qtyOnHand;

    @DecimalMin(value = "0.0", inclusive = true, message = "El costo no puede ser negativo")
    private double cost;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public double getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(double qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
