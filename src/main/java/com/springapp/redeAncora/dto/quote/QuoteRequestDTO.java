package com.springapp.redeAncora.dto.quote;

import com.springapp.redeAncora.domain.Quote;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record QuoteRequestDTO(
        Long userId,
        Quote.Status status,
        LocalDateTime date
){

}
