package com.services.notification.entity;

import com.services.notification.dto.OrderConfirmation;
import com.services.notification.dto.PaymentConfirmation;
import com.services.notification.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class Notification {

    @Id
    private String id;
    private NotificationType notificationType;
    private LocalDateTime createdAt;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;

}
