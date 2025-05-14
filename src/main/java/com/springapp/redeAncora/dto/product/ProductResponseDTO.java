package com.springapp.redeAncora.dto.product;

import java.math.BigDecimal;

public record ProductResponseDTO(
        Long id,
        String name,
        String brand,
        String vehicleCode,
        BigDecimal price
){}
