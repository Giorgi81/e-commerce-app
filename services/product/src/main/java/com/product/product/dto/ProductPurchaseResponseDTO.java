package com.product.product.dto;

import java.math.BigDecimal;

public record ProductPurchaseResponseDTO(
        Long productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
