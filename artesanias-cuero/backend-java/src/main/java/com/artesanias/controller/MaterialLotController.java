package com.artesanias.controller;  // ← ¿Está en la carpeta controller?

import com.artesanias.dao.MaterialLotDAO;
import com.artesanias.model.MaterialLot;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MaterialLotController {
    
    private final MaterialLotDAO materialLotDAO;

    public MaterialLotController(MaterialLotDAO materialLotDAO) {
        this.materialLotDAO = materialLotDAO;
    }

    @GetMapping("/material-lots")  // ← ¿Exactamente así?
    public List<MaterialLot> getAllLots() {
        return materialLotDAO.getAll();
    }
}