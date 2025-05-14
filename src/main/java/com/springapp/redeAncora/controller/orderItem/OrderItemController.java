package com.springapp.redeAncora.controller.orderItem;

import com.springapp.redeAncora.dto.orderItem.OrderItemRequestDTO;
import com.springapp.redeAncora.dto.orderItem.OrderItemResponseDTO;
import com.springapp.redeAncora.service.OrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/itemOrder")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping
    public ResponseEntity<OrderItemResponseDTO> save(@RequestBody OrderItemRequestDTO orderItemRequestDTO) {
        OrderItemResponseDTO orderItemResponseDTO = orderItemService.save(orderItemRequestDTO);

        return ResponseEntity.ok(orderItemResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id) {
        orderItemService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderItemResponseDTO>> findByOrder(@PathVariable Long orderId) {
        List<OrderItemResponseDTO> orderItemResponseDTOS = orderItemService.findByOrder(orderId);

        return ResponseEntity.ok(orderItemResponseDTOS);
    }



}
