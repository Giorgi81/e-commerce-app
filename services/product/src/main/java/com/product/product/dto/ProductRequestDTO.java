package com.product.product.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequestDTO(

        @NotNull(message = "Product name mustn't be null")
        String name,
        @NotNull(message = "Product description mustn't be null")
        String description,
        @Positive(message = "Product quantity has to be positive")
        long availableQuantity,
        @Positive(message = "Product price has to be positive")
        BigDecimal price,
        @NotNull(message = "Category id must not be null")
        Integer categoryId
) {
}
