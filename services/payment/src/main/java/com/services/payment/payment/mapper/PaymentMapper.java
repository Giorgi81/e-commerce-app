package com.services.payment.payment.mapper;

import com.services.payment.payment.dto.PaymentRequestDTO;
import com.services.payment.payment.dto.PaymentResponseDTO;
import com.services.payment.payment.entity.Payment;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    Payment toPayment(PaymentRequestDTO payment);

    PaymentResponseDTO toPaymentResponseDTO(Payment payment);
}
