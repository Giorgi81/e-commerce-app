package com.product.product.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExpcetionHandler extends RuntimeException {

    @ExceptionHandler(ProductPurchaseException.class)
    public ResponseEntity<String> handleProductPurchaseException(ProductPurchaseException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String,String>> entityNotFoundException(EntityNotFoundException e) {
        Map<String, String> map = new HashMap<>();
        map.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }

}
