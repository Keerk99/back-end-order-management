package com.fractal.ordermanagement.service.impl;
import com.fractal.ordermanagement.dto.ProductDto;
import com.fractal.ordermanagement.entity.Product;
import com.fractal.ordermanagement.exception.ResourceNotFoundException;
import com.fractal.ordermanagement.mapper.ProductMapper;
import com.fractal.ordermanagement.repository.ProductRepositiry;
import com.fractal.ordermanagement.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepositiry productRepositiry;

    public ProductServiceImpl(ProductRepositiry productRepositiry) {
        this.productRepositiry = productRepositiry;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = ProductMapper.mapToProduct(productDto);
        product = productRepositiry.save(product);

        return ProductMapper.mapToProductDto(product);
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Product product = productRepositiry.findById(productId).orElse(null);

        return product != null ? ProductMapper.mapToProductDto(product) : null;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepositiry.findAll();

        return products.stream()
                .map((ProductMapper::mapToProductDto))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto updateProduct) {
        Product product = productRepositiry.findById(productId)
                .orElseThrow(()->
                        new RuntimeException("Product doesn't exist with the given id" + productId));

        product.setName(updateProduct.getName());
        product.setUnitPrice(updateProduct.getUnitPrice());

        Product updateProductObj = productRepositiry.save(product);

        return ProductMapper.mapToProductDto(updateProductObj);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepositiry.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product doesn't exist with the given id" + productId)
        );

        productRepositiry.deleteById(productId);
    }
}
