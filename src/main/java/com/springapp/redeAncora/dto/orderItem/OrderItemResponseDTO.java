package com.springapp.redeAncora.dto.orderItem;

import java.math.BigDecimal;

public record OrderItemResponseDTO(
        Long id,
        String productName,
        Integer quantity,
        BigDecimal price
){
}

