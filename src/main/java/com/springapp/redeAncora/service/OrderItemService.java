package com.springapp.redeAncora.service;


import com.springapp.redeAncora.domain.ItemOrder;
import com.springapp.redeAncora.domain.Order;
import com.springapp.redeAncora.domain.Product;
import com.springapp.redeAncora.dto.orderItem.OrderItemRequestDTO;
import com.springapp.redeAncora.dto.orderItem.OrderItemResponseDTO;
import com.springapp.redeAncora.repository.OrderItemRepository;
import com.springapp.redeAncora.repository.OrderRepository;
import com.springapp.redeAncora.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderItemService(OrderItemRepository orderItemRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public OrderItemResponseDTO responseDTO(ItemOrder item) {
        return new OrderItemResponseDTO(item.getId(), item.getProduct().getName(), item.getQuantity(), item.getPrice());
    }

    public OrderItemResponseDTO save(OrderItemRequestDTO body) {
        Order order = orderRepository.findById(body.orderId()).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        Product product = productRepository.findById(body.productId()).orElseThrow(() -> new EntityNotFoundException("Product not found"));

        ItemOrder itemOrder = new ItemOrder(null, order, product, body.quantity(), body.price());

        ItemOrder saved = orderItemRepository.save(itemOrder);

        return responseDTO(saved);
    }


    public void delete(Long id) {
        ItemOrder itemOrder = orderItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item order not found"));

        orderItemRepository.delete(itemOrder);

    }

    public OrderItemResponseDTO findById(Long id) {
        ItemOrder itemOrder = orderItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item order not found"));

        return responseDTO(itemOrder);

    }

    public List<OrderItemResponseDTO> findByOrder(Long orderId) {
        return orderItemRepository.findByOrderId(orderId).stream().map(this::responseDTO).toList();
    }
}
