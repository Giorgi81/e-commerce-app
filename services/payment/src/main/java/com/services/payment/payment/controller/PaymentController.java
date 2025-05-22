package com.services.payment.payment.controller;


import com.services.payment.payment.dto.PaymentRequestDTO;
import com.services.payment.payment.dto.PaymentResponseDTO;
import com.services.payment.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> createPayment(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        log.info("Received payment request: {}", paymentRequestDTO);
        log.info("Customer: {}", paymentRequestDTO.customer());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentService.createPayment(paymentRequestDTO));
    }

}
