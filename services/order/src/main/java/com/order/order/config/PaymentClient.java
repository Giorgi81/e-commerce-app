package com.order.order.config;

import com.order.order.dto.PaymentRequestDTO;
import com.order.order.dto.PaymentResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", url = "${application.config.payment-url}")
public interface PaymentClient {

    @PostMapping
    PaymentResponseDTO createPayment(@RequestBody PaymentRequestDTO payment);
}
