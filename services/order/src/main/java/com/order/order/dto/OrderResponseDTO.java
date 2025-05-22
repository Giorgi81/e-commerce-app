package com.order.order.dto;


import com.order.order.entity.OrderLine;
import com.order.order.enums.PaymentMethod;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(

        Long id,

        String reference,

        BigDecimal totalAmount,

        PaymentMethod paymentMethod,

        String customerId,

        List<OrderLine>orderLines,

        LocalDateTime createTime,

        LocalDateTime lastModifiedDate
) {
}
