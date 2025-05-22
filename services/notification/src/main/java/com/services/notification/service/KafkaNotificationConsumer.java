package com.services.notification.service;


import com.services.notification.dto.OrderConfirmation;
import com.services.notification.dto.PaymentConfirmation;
import com.services.notification.entity.Notification;
import com.services.notification.enums.NotificationType;
import com.services.notification.repository.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class KafkaNotificationConsumer {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "order-topic", groupId = "notificationGroup")
    public void consumeOrderNotification(OrderConfirmation orderConfirmation) throws MessagingException {

        notificationRepository.save(
                Notification.builder()
                        .notificationType(NotificationType.ORDER_CONFIRMATION)
                        .orderConfirmation(orderConfirmation)
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        emailService.orderConfirmationSuccessNotification(
                orderConfirmation.customer().email(),
                orderConfirmation.customer().firstName(),
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }

    @KafkaListener(topics = "payment-topic", groupId = "notificationGroup")
    public void consumePaymentNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {

        notificationRepository.save(
                Notification.builder()
                        .paymentConfirmation(paymentConfirmation)
                        .createdAt(LocalDateTime.now())
                        .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                        .build()
        );
        emailService.paymentConfirmationSuccessNotification(
                paymentConfirmation.customerEmail(),
                paymentConfirmation.customerFirstName(),
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );
    }
}
