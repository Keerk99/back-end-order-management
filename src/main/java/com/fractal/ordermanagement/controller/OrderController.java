package com.fractal.ordermanagement.controller;
import com.fractal.ordermanagement.dto.OrderDto;
import com.fractal.ordermanagement.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //Build Add Order Rest API
    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        OrderDto savedOrder = orderService.createOrder(orderDto);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") Long orderId) {
        OrderDto orderDto = orderService.getOrderById(orderId);
        return  ResponseEntity.ok(orderDto);
    }

    //Get All Orders Rest API
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orderDtos = orderService.getAllOrders();
        return ResponseEntity.ok(orderDtos);
    }

    //Build Update Order Rest API
    @PutMapping("{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable("id") Long orderId,
                                                @RequestBody OrderDto updateOrder) {
        OrderDto orderDto = orderService.updateOrder(orderId, updateOrder);
        return  ResponseEntity.ok(orderDto);
    }

    //Build Delete Order Rest API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok("Order deleted successfully!");
    }
}
