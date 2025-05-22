package com.order.order.repository;

import com.order.order.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

    List<OrderLine> findOrderLineByOrderId(Long id);
}
