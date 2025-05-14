package com.springapp.redeAncora.repository;

import com.springapp.redeAncora.domain.ItemQuote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemQuoteRepository extends JpaRepository<ItemQuote, Long> {
    List<ItemQuote> findByQuoteId(Long quoteId);
}
