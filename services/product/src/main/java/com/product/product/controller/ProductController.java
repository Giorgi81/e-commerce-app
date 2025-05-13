package com.product.product.controller;


import com.product.product.dto.ProductPurchaseRequestDTO;
import com.product.product.dto.ProductPurchaseResponseDTO;
import com.product.product.dto.ProductRequestDTO;
import com.product.product.dto.ProductResponseDTO;
import com.product.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<ProductResponseDTO> createProduct(
            @RequestBody @Valid ProductRequestDTO productRequestDTO
            ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productRequestDTO));

    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponseDTO>> purchaseProducts(
            @RequestBody @Valid List<ProductPurchaseRequestDTO> productPurchaseRequestDTOList
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.purchaseProducts(productPurchaseRequestDTOList));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(
            @PathVariable Long id
    ){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id));

    }

    @GetMapping("/products")
    public ResponseEntity<Page<ProductResponseDTO>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts(page,size));
    }

}
