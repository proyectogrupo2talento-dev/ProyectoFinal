package com.grupo2tech.artesaniascuero.repository;
import com.grupo2tech.artesaniascuero.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderNumber(String orderNumber);
}
