package com.order.order.dto;

import com.order.order.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequestDTO(

        BigDecimal amount,
        PaymentMethod paymentMethod,
        Long orderId,
        String orderReference,
        CustomerResponseDTO customer
) {
}
