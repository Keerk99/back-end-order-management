package com.fractal.ordermanagement.mapper;

import com.fractal.ordermanagement.dto.ProductDto;
import com.fractal.ordermanagement.entity.Product;

public class ProductMapper {
    public static ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getUnitPrice()
        );
    }

    public static Product mapToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getProductId());
        product.setName(productDto.getName());
        product.setUnitPrice(productDto.getUnitPrice());
        return  product;
    }
}
