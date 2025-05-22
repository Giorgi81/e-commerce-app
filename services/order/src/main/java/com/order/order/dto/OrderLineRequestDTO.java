package com.order.order.dto;

public record OrderLineRequestDTO(Long id, Long orderId, Integer productId, double quantity) {
}
