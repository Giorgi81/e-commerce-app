package com.order.order.config;

import com.order.order.dto.PurchaseRequestDTO;
import com.order.order.dto.PurchaseResponseDTO;
import com.order.order.exception.BusinessException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {

    private final RestTemplate restTemplate;

    @Value("${application.config.product-url}")
    private String productUrl;

    public List<PurchaseResponseDTO> getPurchases(List<PurchaseRequestDTO> purchaseRequestDTOS) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<List<PurchaseRequestDTO>> request = new HttpEntity<>(purchaseRequestDTOS, headers);
        ParameterizedTypeReference<List<PurchaseResponseDTO>> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<PurchaseResponseDTO>> response = restTemplate.exchange(
                productUrl + "/purchase",
                HttpMethod.POST,
                request,
                responseType
        );

        if(response.getStatusCode().isError()){
            throw new BusinessException("Error Occured");
        }
        return response.getBody();

    }

}