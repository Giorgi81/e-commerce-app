package com.customer.customer_service.dto;

import com.customer.customer_service.entity.Address;
import lombok.Builder;

public record CustomerResponseDTO(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
