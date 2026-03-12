package com.grupo2tech.artesaniascuero.service;

import com.grupo2tech.artesaniascuero.controller.dto.MaterialLotRequest;
import com.grupo2tech.artesaniascuero.exception.ResourceNotFoundException;
import com.grupo2tech.artesaniascuero.model.Material;
import org.springframework.stereotype.Service;
import com.grupo2tech.artesaniascuero.model.MaterialLot;
import com.grupo2tech.artesaniascuero.repository.MaterialLotRepository;
import com.grupo2tech.artesaniascuero.repository.MaterialRepository;

import java.util.List;

@Service
public class MaterialLotService {
    
    private final MaterialLotRepository materialLotRepository;
    private final MaterialRepository materialRepository;

    public MaterialLotService(MaterialLotRepository materialLotRepository, MaterialRepository materialRepository) {
        this.materialLotRepository = materialLotRepository;
        this.materialRepository = materialRepository;
    }

    public List<MaterialLot> getAllMaterialLots() {
        return materialLotRepository.findAll();
    }

    public MaterialLot getById(Long id) {
        return materialLotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lote no encontrado"));
    }

    public MaterialLot save(MaterialLotRequest request) {
        Material material = materialRepository.findById(request.getMaterialId())
                .orElseThrow(() -> new ResourceNotFoundException("Material no encontrado"));

        MaterialLot existingLot = materialLotRepository.findByMaterialId(material.getId());
        if (existingLot != null) {
            throw new IllegalStateException("Ya existe un lote para este material");
        }

        MaterialLot materialLot = new MaterialLot();
        materialLot.setMaterial(material);
        materialLot.setColor(request.getColor());
        materialLot.setQtyOnHand(request.getQtyOnHand());
        materialLot.setCost(request.getCost());

        return materialLotRepository.save(materialLot);
    }

    public MaterialLot update(Long id, MaterialLotRequest request) {
        MaterialLot existingLot = materialLotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lote no encontrado"));

        Material material = materialRepository.findById(request.getMaterialId())
                .orElseThrow(() -> new ResourceNotFoundException("Material no encontrado"));

        MaterialLot lotByMaterial = materialLotRepository.findByMaterialId(material.getId());
        if (lotByMaterial != null && !lotByMaterial.getId().equals(id)) {
            throw new IllegalStateException("Ya existe un lote para este material");
        }

        existingLot.setMaterial(material);
        existingLot.setColor(request.getColor());
        existingLot.setQtyOnHand(request.getQtyOnHand());
        existingLot.setCost(request.getCost());

        return materialLotRepository.save(existingLot);
    }

    public void delete(Long id) {
        if (!materialLotRepository.existsById(id)) {
            throw new ResourceNotFoundException("Lote no encontrado");
        }
        materialLotRepository.deleteById(id);
    }
}
