package com.services.payment.payment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record Customer(
        String id,
        @NotNull(message = "First Name is required")
        String firstName,
        @NotNull(message = "Last Name is required")
        String lastName,
        @NotNull(message = "Email is required")
        @Email(message = "Format @ is missing")
        String email
) {}
