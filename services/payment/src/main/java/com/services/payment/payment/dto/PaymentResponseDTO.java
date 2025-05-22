package com.services.payment.payment.dto;

import com.services.payment.payment.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentResponseDTO(

        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,

        Customer customer
) {
}
