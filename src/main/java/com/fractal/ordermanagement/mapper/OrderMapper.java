package com.fractal.ordermanagement.mapper;
import com.fractal.ordermanagement.dto.OrderDto;
import com.fractal.ordermanagement.dto.OrderDetailDto;
import com.fractal.ordermanagement.entity.Order;
import com.fractal.ordermanagement.entity.OrderDetail;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDto mapToOrderDto(Order order) {

        List<OrderDetailDto> orderDetailDtos = order.getOrderDetail().stream()
                .map(OrderDetailMapper::mapToProductDto)
                .collect(Collectors.toList());

        return new OrderDto(
                order.getId(),
                order.getOrderNumber(),
                //order.getDate(),
                order.getStatus(),
                orderDetailDtos,
                order.getTotal());
    }

    public static Order mapToOrder(OrderDto orderDto) {

        List<OrderDetail> orderDetails = orderDto.getOrderDetail().stream()
                .map(OrderDetailMapper::mapToProduct)
                .collect(Collectors.toList());

        Order order = new Order();
        order.setOrderNumber(orderDto.getOrderNumber());
        order.setId(orderDto.getOrderId());
        order.setStatus(orderDto.getStatus());
        order.setDate(orderDto.getDate());
        order.setOrderDetail(orderDetails);
        order.calculateTotal();
        return order;
    }
}
