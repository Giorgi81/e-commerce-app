package com.services.payment.payment.service;

import com.services.payment.payment.repository.PaymentRepository;
import com.services.payment.payment.dto.PaymentNotificationRequestDTO;
import com.services.payment.payment.dto.PaymentRequestDTO;
import com.services.payment.payment.dto.PaymentResponseDTO;
import com.services.payment.payment.entity.Payment;
import com.services.payment.payment.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final PaymentNotificationProducer paymentNotificationProducer;
    public PaymentResponseDTO createPayment(PaymentRequestDTO dto) {
        log.info("Saving payment: {}", dto);

        if (dto.customer() == null) {
            throw new IllegalArgumentException("Customer must not be null");
        }

        Payment payment = paymentRepository.save(paymentMapper.toPayment(dto));

        paymentNotificationProducer.sendPaymentNotification(
                new PaymentNotificationRequestDTO(
                        dto.orderReference(),
                        dto.amount(),
                        dto.paymentMethod(),
                        dto.customer().firstName(),
                        dto.customer().lastName(),
                        dto.customer().email()
                )
        );

        return paymentMapper.toPaymentResponseDTO(payment);
    }

}
