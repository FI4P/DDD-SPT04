package com.springapp.redeAncora.service;


import com.springapp.redeAncora.domain.Order;
import com.springapp.redeAncora.domain.User;
import com.springapp.redeAncora.dto.order.OrderRequestDTO;
import com.springapp.redeAncora.dto.order.OrderResponseDTO;
import com.springapp.redeAncora.repository.OrderRepository;
import com.springapp.redeAncora.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    private OrderResponseDTO responseDTO(Order order) {
        return new OrderResponseDTO(order.getId(), order.getUser().getName(), order.getStatus(), order.getCreatedAt());
    }

    public OrderResponseDTO save(OrderRequestDTO body) {
        User user = userRepository.findById(body.userId()).orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order(null, user, body.date(), body.status());

        Order saved = orderRepository.save(order);
        return responseDTO(saved);
    }

    public OrderResponseDTO update(Long id, OrderRequestDTO body) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));

        order.setStatus(body.status() != null ? body.status() : order.getStatus());

        Order saved = orderRepository.save(order);
        return responseDTO(saved);
    }

    public void delete(Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));

        orderRepository.delete(order);
    }

    public OrderResponseDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        return responseDTO(order);
    }

    public List<OrderResponseDTO> findAll() {
        return orderRepository.findAll().stream().map(this::responseDTO).toList();
    }
}
