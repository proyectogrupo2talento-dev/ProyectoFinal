package com.grupo2tech.artesaniascuero.controller;

import com.grupo2tech.artesaniascuero.controller.dto.MaterialLotRequest;
import com.grupo2tech.artesaniascuero.model.MaterialLot;
import com.grupo2tech.artesaniascuero.service.MaterialLotService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/material-lots")
@CrossOrigin(origins = "http://localhost:4200")
public class MaterialLotController {
    
    private final MaterialLotService materialLotService;
    // Inyección de dependencias
    public MaterialLotController(MaterialLotService materialLotService){
        this.materialLotService = materialLotService;
    }

    @GetMapping
    public Iterable<MaterialLot> getAllMaterialLots() {
        return materialLotService.getAllMaterialLots();
    }

    @GetMapping("/{id}")
    public MaterialLot getMaterialLotById(@PathVariable Long id) {
        return materialLotService.getById(id);
    }

    @PostMapping
    public MaterialLot createMaterialLot(@Valid @RequestBody MaterialLotRequest request) {
        return materialLotService.save(request);
    }

    @PutMapping("/{id}")
    public MaterialLot updateMaterialLot(@PathVariable Long id, @Valid @RequestBody MaterialLotRequest request) {
        return materialLotService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteMaterialLot(@PathVariable Long id) {
        materialLotService.delete(id);
    }
}
