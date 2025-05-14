package com.springapp.redeAncora.dto.itemQuote;

import java.math.BigDecimal;

public record ItemQuoteRequestDTO (
        Long quoteId,
        Long productId,
        Integer quantity,
        BigDecimal price
) {

}
