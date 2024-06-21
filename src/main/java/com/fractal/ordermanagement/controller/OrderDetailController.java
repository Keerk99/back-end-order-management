package com.fractal.ordermanagement.controller;
import com.fractal.ordermanagement.dto.OrderDetailDto;
import com.fractal.ordermanagement.service.OrderDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/orderdetail")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    //Build Add OrderDetail Rest API
    @PostMapping
    public ResponseEntity<OrderDetailDto> createProduct(@RequestBody OrderDetailDto orderDetailDto) {
        OrderDetailDto savedProduct = orderDetailService.createOrderDetail(orderDetailDto);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    //Build Get OrderDeatil Rest API
    @GetMapping("{id}")
    public ResponseEntity<OrderDetailDto> getProductById(@PathVariable("id") Long orderDetailId) {
        OrderDetailDto orderDetailDto = orderDetailService.getOrderDetailById(orderDetailId);
        return ResponseEntity.ok(orderDetailDto);
    }

    //Build Get OrderDeatil Rest API
    @GetMapping("/orderId/{order_id}")
    public ResponseEntity<List<OrderDetailDto>> getOrderDetailsByOrderId(@PathVariable("order_id") Long orderId) {
        List<OrderDetailDto> orderDetailDtos = orderDetailService.getOrderDetailByOrderId(orderId);
        return ResponseEntity.ok(orderDetailDtos);
    }

    //Get All Products Rest API
    @GetMapping
    public ResponseEntity<List<OrderDetailDto>> getAllProducts() {
        List<OrderDetailDto> orderDetailDtos = orderDetailService.getAllOrderDetails();
        return ResponseEntity.ok(orderDetailDtos);
    }
}
