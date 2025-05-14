package com.springapp.redeAncora.dto.order;

import com.springapp.redeAncora.domain.Order;
import com.springapp.redeAncora.domain.Quote;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record OrderRequestDTO (
        Long userId,
        Order.Status status,
        LocalDate date
){
}
