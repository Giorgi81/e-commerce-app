package com.product.product.mapper;

import com.product.product.dto.ProductPurchaseResponseDTO;
import com.product.product.dto.ProductRequestDTO;
import com.product.product.dto.ProductResponseDTO;
import com.product.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductRequestDTO productRequestDTO);

    ProductResponseDTO toResponseDTO(Product product);

    ProductPurchaseResponseDTO toPurchaseResponseDTO(Product product);
}
