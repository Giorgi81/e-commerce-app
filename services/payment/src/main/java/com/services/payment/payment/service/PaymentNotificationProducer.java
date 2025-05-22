package com.services.payment.payment.service;

import com.services.payment.payment.dto.PaymentNotificationRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentNotificationProducer {

    private final KafkaTemplate<String, PaymentNotificationRequestDTO> kafkaTemplate;

    public void sendPaymentNotification(PaymentNotificationRequestDTO paymentNotificationRequestDTO) {
        Message<PaymentNotificationRequestDTO> message = MessageBuilder
                .withPayload(paymentNotificationRequestDTO)
                .setHeader(KafkaHeaders.TOPIC, "payment-topic")
                .build();

        kafkaTemplate.send(message);

    }


}
