package com.fractal.ordermanagement.service.impl;

import com.fractal.ordermanagement.dto.OrderDetailDto;
import com.fractal.ordermanagement.entity.OrderDetail;
import com.fractal.ordermanagement.exception.ResourceNotFoundException;
import com.fractal.ordermanagement.mapper.OrderDetailMapper;
import com.fractal.ordermanagement.repository.OrderDetailRepository;
import com.fractal.ordermanagement.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public OrderDetailDto createOrderDetail(OrderDetailDto orderDetailDto) {
        OrderDetail orderDetail = OrderDetailMapper.mapToProduct(orderDetailDto);
        OrderDetail savedOrderDetail = orderDetailRepository.save(orderDetail);

        return OrderDetailMapper.mapToProductDto(savedOrderDetail);
    }

    @Override
    public OrderDetailDto getOrderDetailById(Long orderDetailId) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("OrderDetail not found with the given id: "+ orderDetailId));

        return OrderDetailMapper.mapToProductDto(orderDetail);
    }

    @Override
    public List<OrderDetailDto> getOrderDetailByOrderId(Long orderId) {
       List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
       return orderDetails.stream()
               .map(OrderDetailMapper::mapToProductDto)
               .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailDto> getAllOrderDetails() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        return orderDetails.stream().
                map((OrderDetailMapper::mapToProductDto))
                .collect(Collectors.toList());
    }
}
