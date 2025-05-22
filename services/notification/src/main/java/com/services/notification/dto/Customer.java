package com.services.notification.dto;

public record Customer(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
