package com.grupo2tech.artesaniascuero.service;
import org.springframework.stereotype.Service;
import com.grupo2tech.artesaniascuero.model.Material;
import com.grupo2tech.artesaniascuero.repository.MaterialRepository;
import java.util.List;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository){
        this.materialRepository = materialRepository;
    }

    public Material save(Material material){
        // lógica de negocio
        if(materialRepository.findByName(material.getName()) != null){
            throw new IllegalStateException("El material ya existe");
        }

        return materialRepository.save(material);
    }

    public List<Material> getAllMaterials(){
        return materialRepository.findAll();
    }

    @SuppressWarnings("null")
    public Material getMaterialById(Long id){
        if (!materialRepository.existsById(id)){
            throw new RuntimeException("El material no existe");
        }
        return materialRepository.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    public Material updateMaterial(Long id, Material material){
        if (!materialRepository.existsById(id)){
            throw new RuntimeException("El material no existe");
        }
        material.setId(id);
        return materialRepository.save(material);
    }

    @SuppressWarnings("null")
    public void deleteMaterial(Long id){
        if (!materialRepository.existsById(id)){
            throw new RuntimeException("El material no existe");
        }
        materialRepository.deleteById(id);
    }

}
