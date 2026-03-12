package com.artesanias.service;
import com.artesanias.model.OrderItem;
import com.artesanias.repository.OrderItemRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.artesanias.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor

public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItem save(OrderItem OrderItem){
         if (orderItemRepository.existsById(OrderItem.getId())) {
            throw new ResourceNotFoundException("la Order_Item ya existe");
        }
        return orderItemRepository.save(OrderItem);
    }

    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    public OrderItem findById(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order_Item no encontrado"));
    }

}



