package com.order.order.service;


import com.order.order.dto.OrderConfirmationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderKafkaProducer {

    private final KafkaTemplate<String, OrderConfirmationDTO> kafkaTemplate;

    public void send(OrderConfirmationDTO orderConfirmationDTO) {
        Message<OrderConfirmationDTO> message =
                MessageBuilder
                        .withPayload(orderConfirmationDTO)
                        .setHeader(KafkaHeaders.TOPIC, "order-topic")
                        .build();

        kafkaTemplate.send(message);

    }
}
