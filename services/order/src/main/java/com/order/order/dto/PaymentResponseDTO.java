package com.order.order.dto;

import com.order.order.enums.PaymentMethod;

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
