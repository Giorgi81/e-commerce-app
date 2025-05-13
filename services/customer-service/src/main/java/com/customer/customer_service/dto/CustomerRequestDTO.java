package com.customer.customer_service.dto;

import com.customer.customer_service.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequestDTO(
        @NotNull(message = "First Name is required")
        String firstName,
        @NotNull(message = "Last Name is required")
        String lastName,
        @NotNull(message = "Email is required")
        @Email(message = "Email isnt valid")
        String email,
        @NotNull(message = "Address is required")
        Address address
) {
}
