package com.artesanias.repository;
import com.artesanias.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.time.LocalDate;
import java.util.List;


public interface OrderRepository extends JpaRepository<Orders, Long> {
        // Buscar empresas por Cliente
    List<Orders> findByCustomerId(Long customerId);
    
         // ver ordenes por estado
    List<Orders> findByStatus(String status);
    
         //filtrar por fechas
    List<Orders> findByPromisedDateBetween(LocalDate start, LocalDate end);

        // Buscar Orden por cliente unico
   //Optional<Orders> findByIdAndCustomerId(String name, Long customerId);

    //boolean existsByOrdersAndCustomerId(String name, Long customerId);

}
