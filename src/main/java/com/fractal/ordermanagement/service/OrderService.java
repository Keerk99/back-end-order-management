package com.fractal.ordermanagement.service;

import com.fractal.ordermanagement.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);

    OrderDto getOrderById(Long orderId);

    List<OrderDto> getAllOrders();

    OrderDto updateOrder(Long orderId, OrderDto updateOrder);

    void deleteOrder(Long orderId);
}
