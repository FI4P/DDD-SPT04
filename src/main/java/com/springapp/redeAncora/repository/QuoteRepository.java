package com.springapp.redeAncora.repository;

import com.springapp.redeAncora.domain.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
}
