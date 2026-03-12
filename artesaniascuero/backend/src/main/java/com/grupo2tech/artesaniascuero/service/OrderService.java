package com.grupo2tech.artesaniascuero.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo2tech.artesaniascuero.exception.ResourceNotFoundException;
import com.grupo2tech.artesaniascuero.model.Material;
import com.grupo2tech.artesaniascuero.model.MaterialLot;
import com.grupo2tech.artesaniascuero.model.Order;
import com.grupo2tech.artesaniascuero.repository.MaterialLotRepository;
import com.grupo2tech.artesaniascuero.repository.MaterialRepository;
import com.grupo2tech.artesaniascuero.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final MaterialRepository materialRepository;
    private final MaterialLotRepository materialLotRepository;

    public OrderService(
            OrderRepository orderRepository,
            MaterialRepository materialRepository,
            MaterialLotRepository materialLotRepository) {
        this.orderRepository = orderRepository;
        this.materialRepository = materialRepository;
        this.materialLotRepository = materialLotRepository;
    }

    @Transactional
    public Order save(Order order) {
        if (order.getOrderNumber() != null && orderRepository.findByOrderNumber(order.getOrderNumber()) != null) {
            throw new IllegalStateException("La orden ya existe");
        }

        if (order.getMaterial() == null || order.getMaterial().getId() == null) {
            throw new IllegalStateException("Debes seleccionar un material para la orden");
        }

        if (order.getQuantity() <= 0) {
            throw new IllegalStateException("La cantidad de la orden debe ser mayor a cero");
        }

        Material material = materialRepository.findById(order.getMaterial().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Material no encontrado"));

        List<MaterialLot> lotsToUpdate = new ArrayList<>();

        Long materialId = material.getId();
        MaterialLot materialLot = materialLotRepository.findByMaterialId(materialId);
        if (materialLot == null) {
            throw new ResourceNotFoundException("No existe inventario para el material con id " + materialId);
        }

        double requiredQty = order.getQuantity();
        if (materialLot.getQtyOnHand() < requiredQty) {
            throw new IllegalStateException(
                    "Stock insuficiente para material " + material.getName()
                            + ". Requerido: " + requiredQty + ", Disponible: " + materialLot.getQtyOnHand());
        }

        materialLot.setQtyOnHand(materialLot.getQtyOnHand() - requiredQty);
        lotsToUpdate.add(materialLot);

        materialLotRepository.saveAll(lotsToUpdate);
        order.setMaterial(material);
        return orderRepository.save(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada"));
    }

}
