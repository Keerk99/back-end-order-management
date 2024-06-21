package com.fractal.ordermanagement.repository;

import com.fractal.ordermanagement.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
