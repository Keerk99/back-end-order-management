package com.fractal.ordermanagement.service.impl;

import com.fractal.ordermanagement.dto.OrderDto;
import com.fractal.ordermanagement.entity.Order;
import com.fractal.ordermanagement.entity.OrderDetail;
import com.fractal.ordermanagement.exception.ResourceNotFoundException;
import com.fractal.ordermanagement.mapper.OrderDetailMapper;
import com.fractal.ordermanagement.mapper.OrderMapper;
import com.fractal.ordermanagement.repository.OrderRepository;
import com.fractal.ordermanagement.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = OrderMapper.mapToOrder(orderDto);
        order = orderRepository.save(order);

        return OrderMapper.mapToOrderDto(order);
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElse(null);

        return order != null ? OrderMapper.mapToOrderDto(order) : null;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().
                map((OrderMapper::mapToOrderDto))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderDto updateOrder(Long orderId, OrderDto updateOrder) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Order doesn't exist with the given id" + orderId));

        List<OrderDetail> orderDetails = updateOrder.getOrderDetail().stream()
                .map(OrderDetailMapper::mapToProduct)
                .collect(Collectors.toList());

        for (OrderDetail updatedDetail : orderDetails) {
            boolean detailUpdated = false;
            for (OrderDetail existingDetail : order.getOrderDetail()) {
                if (existingDetail.getId() != null && existingDetail.getId().equals(updatedDetail.getId())) {
                    existingDetail.setQuantity(updatedDetail.getQuantity());
                    detailUpdated = true;
                    break;
                }
            }
            if (!detailUpdated) {
                updatedDetail.setOrder(order);
                order.getOrderDetail().add(updatedDetail);
            }
        }

        order.getOrderDetail().removeIf(existingDetail ->
                existingDetail.getId() != null &&
                orderDetails.stream().noneMatch(updatedDetail ->
                        updatedDetail.getId() != null && updatedDetail.getId().equals(existingDetail.getId())));

        order.setStatus(updateOrder.getStatus());
        order.calculateTotal();

        Order updatedOrderObj = orderRepository.save(order);

        return OrderMapper.mapToOrderDto(updatedOrderObj);
    }

    @Override
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Order doesn't exist with the given id" + orderId)
        );

        orderRepository.deleteById(orderId);
    }
}
