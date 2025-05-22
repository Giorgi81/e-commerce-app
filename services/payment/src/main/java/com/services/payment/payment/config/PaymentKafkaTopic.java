package com.services.payment.payment.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class PaymentKafkaTopic {

    @Bean
    public NewTopic paymentTopic() {
        return TopicBuilder.name("payment-topic").build();
    }
}
