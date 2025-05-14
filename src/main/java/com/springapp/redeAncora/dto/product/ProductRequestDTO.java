package com.springapp.redeAncora.dto.product;

import java.math.BigDecimal;

public record ProductRequestDTO(
        String name,
        String brand,
        String vehicleCode,
        BigDecimal price
){}
