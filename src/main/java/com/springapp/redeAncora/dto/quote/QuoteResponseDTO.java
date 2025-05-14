package com.springapp.redeAncora.dto.quote;

import com.springapp.redeAncora.domain.Quote;
import com.springapp.redeAncora.domain.User;

import java.time.LocalDateTime;

public record QuoteResponseDTO (
        Long id,
        Long userId,
        Quote.Status status,
        LocalDateTime date
){

}
