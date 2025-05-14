package com.springapp.redeAncora.dto.orderItem;

import java.math.BigDecimal;

public record OrderItemRequestDTO (
        Long orderId,
        Long productId,
        Integer quantity,
        BigDecimal price
) {
}
