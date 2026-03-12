package com.grupo2tech.artesaniascuero.repository;
import com.grupo2tech.artesaniascuero.model.MaterialLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialLotRepository extends JpaRepository<MaterialLot, Long> {
    MaterialLot findByMaterialId(Long materialId);
}
