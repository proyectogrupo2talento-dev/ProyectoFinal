package com.grupo2tech.artesaniascuero.repository;
import com.grupo2tech.artesaniascuero.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    Material findByName(String name);
}
