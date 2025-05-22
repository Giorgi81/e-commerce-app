package com.product.product.service;

import com.product.product.dto.ProductPurchaseRequestDTO;
import com.product.product.dto.ProductPurchaseResponseDTO;
import com.product.product.dto.ProductRequestDTO;
import com.product.product.dto.ProductResponseDTO;
import com.product.product.entity.Product;
import com.product.product.exception.ProductPurchaseException;
import com.product.product.mapper.ProductMapper;
import com.product.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = productRepository.save(productMapper.toEntity(productRequestDTO));
        return productMapper.toResponseDTO(product);
    }


    public List<ProductPurchaseResponseDTO> purchaseProducts(List<ProductPurchaseRequestDTO> productPurchaseRequestDTOList) {

        List<Integer> productRequestIds = productPurchaseRequestDTOList
                .stream()
                .map(ProductPurchaseRequestDTO::productId)
                .toList();

        List<Product> storedProductsOrderedById =
                productRepository.findAllByIdInOrderById(productRequestIds);

        if(productRequestIds.size() != storedProductsOrderedById.size()) {
            throw new ProductPurchaseException("There are not enough products in order");
        }
        List<ProductPurchaseRequestDTO> sortedProductsById =
                productPurchaseRequestDTOList
                        .stream()
                        .sorted(Comparator.comparing(ProductPurchaseRequestDTO::productId)).toList();
        var res =
                productPurchaseRequestDTOList.stream().toList();
        System.out.println(res);

        List<ProductPurchaseResponseDTO> productPurchaseResponseDTOList = new ArrayList<>();
        List<Product> productResult = new ArrayList<>();
        for(int i = 0; i < sortedProductsById.size(); i++) {
            var product = storedProductsOrderedById.get(i);
            var storedProduct = sortedProductsById.get(i);

            if(product.getAvailableQuantity() < storedProduct.quantity()) {
                throw new EntityNotFoundException("No Stock");
            }

            var newAvailableQuantity = product.getAvailableQuantity() - storedProduct.quantity();
            product.setAvailableQuantity(newAvailableQuantity);

            productResult.add(product);
            productPurchaseResponseDTOList.add(toPurchase(product,  storedProduct.quantity()));
        }
        productRepository.saveAll(productResult);

        return productPurchaseResponseDTOList;

    }

    private ProductPurchaseResponseDTO toPurchase(Product product, Integer quantity) {

        return new ProductPurchaseResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }


    public ProductResponseDTO getProductById(Long id) {
        return productRepository.findById(id).map(productMapper::toResponseDTO).orElseThrow(() -> new NotFoundException(""));
    }

    public Page<ProductResponseDTO> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(productMapper::toResponseDTO);
    }
}
