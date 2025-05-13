package com.customer.customer_service.controller;


import com.customer.customer_service.dto.CustomerRequestDTO;
import com.customer.customer_service.dto.CustomerResponseDTO;
import com.customer.customer_service.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(
            @RequestBody @Valid CustomerRequestDTO customerRequestDTO
            ){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerRequestDTO));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(
            @PathVariable String id,
            @RequestBody @Valid CustomerRequestDTO customerRequestDTO
    ){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomer(id, customerRequestDTO));
    }

    @GetMapping
    public ResponseEntity<Page<CustomerResponseDTO>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomers(page,size));

    }

    @GetMapping("/exist/{id}")
    public ResponseEntity<Boolean> isCustomerExists(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.isCustomerExists(id));
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<CustomerResponseDTO> findCustomer(
            @PathVariable String id
    ){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findCustomer(id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable String id
    ) {
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
