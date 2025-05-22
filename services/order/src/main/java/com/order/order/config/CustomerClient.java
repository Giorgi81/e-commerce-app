package com.order.order.config;

import com.order.order.dto.CustomerResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "customer-service",
        url = "${application.config.customer-url}")
public interface CustomerClient {

    @GetMapping("/find/{id}")
    Optional<CustomerResponseDTO> getCustomerById(@PathVariable String id);
}
