package com.fractal.ordermanagement.repository;

import com.fractal.ordermanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositiry extends JpaRepository<Product, Long> {
}
