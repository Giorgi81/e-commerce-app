package com.services.notification.dto;

import com.services.notification.enums.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
