package com.services.payment.payment.dto;

import com.services.payment.payment.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequestDTO(
        Long id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Long orderId,
        String orderReference,
        Customer customer
) {}
