package com.order.order.dto;

import java.math.BigDecimal;

public record PurchaseResponseDTO(

        Integer productId,

        String name,

        String description,

        BigDecimal price,

        double quantity
) {
}
