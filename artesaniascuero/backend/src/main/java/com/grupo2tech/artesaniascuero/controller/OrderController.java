package com.grupo2tech.artesaniascuero.controller;
import org.springframework.web.bind.annotation.*;
import com.grupo2tech.artesaniascuero.model.Order;
import com.grupo2tech.artesaniascuero.service.OrderService;
import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:4200") 
public class OrderController {
    
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@Valid @RequestBody Order order) {
        return orderService.save(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @Valid @RequestBody Order order) {
        return orderService.update(id, order);
    }

}
