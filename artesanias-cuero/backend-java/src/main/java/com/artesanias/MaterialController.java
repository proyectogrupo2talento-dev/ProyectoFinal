package com.artesanias;

import com.artesanias.dao.MaterialDAO;
import com.artesanias.model.Material;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MaterialController {
    
    private final MaterialDAO materialDAO;

    public MaterialController(MaterialDAO materialDAO) {
        this.materialDAO = materialDAO;
    }

    @GetMapping("/materials")
    public List<Material> getMaterials() {
        return materialDAO.getAll();
    }

    @GetMapping("/materials/{id}")
    public Material getMaterialById(@PathVariable int id) {
        return materialDAO.getById(id);
    }
}