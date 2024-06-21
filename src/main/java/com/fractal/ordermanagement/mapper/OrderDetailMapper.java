package com.fractal.ordermanagement.mapper;
import com.fractal.ordermanagement.dto.OrderDetailDto;
import com.fractal.ordermanagement.entity.OrderDetail;

public class OrderDetailMapper {
    public static OrderDetailDto mapToProductDto(OrderDetail orderDetail) {

        return new OrderDetailDto(
                orderDetail.getId(),
                orderDetail.getName(),
                orderDetail.getUnitPrice(),
                orderDetail.getQuantity()
        );
    }

    public static OrderDetail mapToProduct(OrderDetailDto orderDetailDto) {

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(orderDetailDto.getOrderDetailId());
        orderDetail.setName(orderDetailDto.getName());
        orderDetail.setUnitPrice(orderDetailDto.getUnitPrice());
        orderDetail.setQuantity(orderDetailDto.getQuantity());
        return orderDetail;
    }
}
