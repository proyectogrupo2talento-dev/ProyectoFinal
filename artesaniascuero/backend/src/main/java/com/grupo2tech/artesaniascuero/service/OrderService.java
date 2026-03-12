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

        validateOrderData(order);

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

    @Transactional
    public Order update(Long id, Order order) {
        Order existingOrder = findById(id);

        Order existingWithSameNumber = orderRepository.findByOrderNumber(order.getOrderNumber());
        if (existingWithSameNumber != null && !existingWithSameNumber.getId().equals(id)) {
            throw new IllegalStateException("La orden ya existe");
        }

        validateOrderData(order);

        Material newMaterial = materialRepository.findById(order.getMaterial().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Material no encontrado"));

        MaterialLot oldMaterialLot = materialLotRepository.findByMaterialId(existingOrder.getMaterial().getId());
        if (oldMaterialLot == null) {
            throw new ResourceNotFoundException(
                    "No existe inventario para el material con id " + existingOrder.getMaterial().getId());
        }

        List<MaterialLot> lotsToUpdate = new ArrayList<>();

        oldMaterialLot.setQtyOnHand(oldMaterialLot.getQtyOnHand() + existingOrder.getQuantity());

        if (existingOrder.getMaterial().getId().equals(newMaterial.getId())) {
            if (oldMaterialLot.getQtyOnHand() < order.getQuantity()) {
                throw new IllegalStateException(
                        "Stock insuficiente para material " + newMaterial.getName()
                                + ". Requerido: " + order.getQuantity() + ", Disponible: " + oldMaterialLot.getQtyOnHand());
            }

            oldMaterialLot.setQtyOnHand(oldMaterialLot.getQtyOnHand() - order.getQuantity());
            lotsToUpdate.add(oldMaterialLot);
        } else {
            MaterialLot newMaterialLot = materialLotRepository.findByMaterialId(newMaterial.getId());
            if (newMaterialLot == null) {
                throw new ResourceNotFoundException("No existe inventario para el material con id " + newMaterial.getId());
            }

            if (newMaterialLot.getQtyOnHand() < order.getQuantity()) {
                throw new IllegalStateException(
                        "Stock insuficiente para material " + newMaterial.getName()
                                + ". Requerido: " + order.getQuantity() + ", Disponible: " + newMaterialLot.getQtyOnHand());
            }

            newMaterialLot.setQtyOnHand(newMaterialLot.getQtyOnHand() - order.getQuantity());
            lotsToUpdate.add(oldMaterialLot);
            lotsToUpdate.add(newMaterialLot);
        }

        materialLotRepository.saveAll(lotsToUpdate);

        existingOrder.setOrderNumber(order.getOrderNumber());
        existingOrder.setCustomerId(order.getCustomerId());
        existingOrder.setMaterial(newMaterial);
        existingOrder.setQuantity(order.getQuantity());
        existingOrder.setStatus(order.getStatus());
        existingOrder.setPromisedDate(order.getPromisedDate());
        existingOrder.setNotes(order.getNotes());

        return orderRepository.save(existingOrder);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada"));
    }

    @Transactional
    public void delete(Long id) {
        Order order = findById(id);

        MaterialLot materialLot = materialLotRepository.findByMaterialId(order.getMaterial().getId());
        if (materialLot == null) {
            throw new ResourceNotFoundException(
                    "No existe inventario para el material con id " + order.getMaterial().getId());
        }

        materialLot.setQtyOnHand(materialLot.getQtyOnHand() + order.getQuantity());
        materialLotRepository.save(materialLot);

        orderRepository.delete(order);
    }

    private void validateOrderData(Order order) {
        if (order.getMaterial() == null || order.getMaterial().getId() == null) {
            throw new IllegalStateException("Debes seleccionar un material para la orden");
        }

        if (order.getQuantity() <= 0) {
            throw new IllegalStateException("La cantidad de la orden debe ser mayor a cero");
        }
    }

}
