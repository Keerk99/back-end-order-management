package com.fractal.ordermanagement.service;

import com.fractal.ordermanagement.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);

    ProductDto getProductById(Long productId);

    List<ProductDto> getAllProducts();

    ProductDto updateProduct(Long productId, ProductDto updateProduct);

    void deleteProduct(Long productId);
}
