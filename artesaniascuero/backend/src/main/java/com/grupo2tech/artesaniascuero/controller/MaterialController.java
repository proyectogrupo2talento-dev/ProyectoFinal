package com.grupo2tech.artesaniascuero.controller;
import org.springframework.web.bind.annotation.*;
import com.grupo2tech.artesaniascuero.model.Material;
import com.grupo2tech.artesaniascuero.service.MaterialService;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/materials")
@CrossOrigin(origins = "http://localhost:4200")

public class MaterialController {  
    private final MaterialService materialService;

    // Inyección de dependencias
    public MaterialController(MaterialService materialService){
        this.materialService = materialService;
    }

    // Crear material
    @PostMapping
    public Material createMaterial(@Valid @RequestBody Material material){
        return materialService.save(material);
    }

    // Obtener todos los materiales
    @GetMapping
    public List<Material> getAllMaterials(){
        return materialService.getAllMaterials();
    }

    // Obtener material por ID
    @GetMapping("/{id}")
    public Material getMaterialById(@PathVariable Long id){
        return materialService.getMaterialById(id);
    }

    // Actualizar material
    @PutMapping("/{id}")
    public Material updateMaterial(@PathVariable Long id, @Valid @RequestBody Material material){
        return materialService.updateMaterial(id, material);
    }

    // Eliminar material
    @DeleteMapping("/{id}")
    public void deleteMaterial(@PathVariable Long id){
        materialService.deleteMaterial(id);
    }
}   
