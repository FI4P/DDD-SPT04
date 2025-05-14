package com.springapp.redeAncora.repository;

import com.springapp.redeAncora.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
