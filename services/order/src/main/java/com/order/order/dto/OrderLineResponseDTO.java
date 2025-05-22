package com.order.order.dto;

public record OrderLineResponseDTO(Long id, Long orderId, Integer productId, double quantity) {
}
