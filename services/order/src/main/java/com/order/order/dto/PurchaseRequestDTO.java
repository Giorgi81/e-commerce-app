package com.order.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequestDTO(
        @NotNull(message = "Product should be present")
        Integer productId,

        @Positive(message = "Quantity is mandatory")
        double quantity
) {
}
