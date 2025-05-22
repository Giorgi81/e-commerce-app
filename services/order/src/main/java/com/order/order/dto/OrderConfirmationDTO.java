package com.order.order.dto;

import com.order.order.enums.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmationDTO(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponseDTO customer,
        List<PurchaseResponseDTO> products
) {
}
