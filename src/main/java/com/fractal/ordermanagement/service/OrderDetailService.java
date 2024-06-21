package com.fractal.ordermanagement.service;

import com.fractal.ordermanagement.dto.OrderDetailDto;

import java.util.List;

public interface OrderDetailService {
    OrderDetailDto createOrderDetail(OrderDetailDto orderDetailDto);

    OrderDetailDto getOrderDetailById(Long orderDetailId);

    List<OrderDetailDto> getOrderDetailByOrderId(Long orderId);

    List<OrderDetailDto> getAllOrderDetails();

}
