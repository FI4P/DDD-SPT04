package com.springapp.redeAncora.controller.order;


import com.springapp.redeAncora.dto.order.OrderRequestDTO;
import com.springapp.redeAncora.dto.order.OrderResponseDTO;
import com.springapp.redeAncora.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> save(@RequestBody OrderRequestDTO body) {
        OrderResponseDTO order = orderService.save(body);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> findById(@PathVariable Long id) {
        OrderResponseDTO order = orderService.findById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> findAll() {
        List<OrderResponseDTO> orders = orderService.findAll();

        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> delete(@PathVariable Long id) {
        orderService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<OrderResponseDTO> update(@PathVariable Long id, @RequestBody OrderRequestDTO body) {
        OrderResponseDTO order = orderService.update(id, body);
        return ResponseEntity.ok(order);
    }
}
