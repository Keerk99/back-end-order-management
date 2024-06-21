package com.fractal.ordermanagement.dto;
import java.time.LocalDate;
import java.util.List;

public class OrderDto {
    private Long orderId;
    private String orderNumber;
    private LocalDate date = LocalDate.now();
    private String status = "p";
    private List<OrderDetailDto> orderDetail;
    private double total;

    public OrderDto() {
    }

    public OrderDto(Long orderId, String orderNumber, String status, List<OrderDetailDto> orderDetail, double total) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.date = LocalDate.now();
        this.status = status;
        this.orderDetail = orderDetail;
        this.total = total;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderDetailDto> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetailDto> OrderDetail) {
        this.orderDetail = OrderDetail;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
