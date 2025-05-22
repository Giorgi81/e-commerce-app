package com.services.payment.payment.dto;

import com.services.payment.payment.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequestDTO(
        String orderReference,

        BigDecimal amount,

        PaymentMethod paymentMethod,

        String customerFirstName,

        String customerLastName,

        String customerEmail

) {
}
