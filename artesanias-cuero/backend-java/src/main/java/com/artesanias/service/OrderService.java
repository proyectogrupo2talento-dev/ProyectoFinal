package com.artesanias.service;
import com.artesanias.model.Orders;
import com.artesanias.repository.OrderRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.artesanias.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor

public class OrderService {
    private final OrderRepository orderRepository;

    public Orders save(Orders orders){
         if (orderRepository.existsById(orders.getId())) {
            throw new ResourceNotFoundException("la orden ya existe");
        }
        return orderRepository.save(orders);
    }

    public List<Orders> findAll() {
        return orderRepository.findAll();
    }

    public Orders findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
    }

}
