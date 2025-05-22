package com.product.product.dto;
import java.math.BigDecimal;


public record ProductResponseDTO(

        Long id,
        String name,
        String description,
        long availableQuantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription

) {
}
