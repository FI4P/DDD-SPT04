package com.springapp.redeAncora.repository;

import com.springapp.redeAncora.domain.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<ItemOrder, Long> {
    List<ItemOrder> findByOrderId(Long orderId);
}
