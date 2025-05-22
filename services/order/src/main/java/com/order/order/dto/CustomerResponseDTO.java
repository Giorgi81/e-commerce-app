package com.order.order.dto;

public record CustomerResponseDTO(
        String id,

        String firstName,

        String lastName,

        String email
) {
}
