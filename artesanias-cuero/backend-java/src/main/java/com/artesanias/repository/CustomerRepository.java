package com.artesanias.repository;
import com.artesanias.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    // Buscar por nombre
    Optional<Customer> findByName(String name);

    // Validar si existe por nombre
    boolean existsByName(String name);

}
