package com.springapp.redeAncora.dto.itemQuote;

import java.math.BigDecimal;

public record ItemQuoteResponseDTO (
        Long id,
        String productName,
        Integer quantity,
        BigDecimal price
){

}
