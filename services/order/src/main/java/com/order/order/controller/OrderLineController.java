package com.order.order.controller;

import com.order.order.dto.OrderLineResponseDTO;
import com.order.order.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order-line")
public class OrderLineController {

    private final OrderLineService orderLineService;

    @GetMapping("/order/{id}")
    public ResponseEntity<List<OrderLineResponseDTO>> findOrderLineByOrderId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderLineService.findOrderLineByOrderId(id));
    }
}
