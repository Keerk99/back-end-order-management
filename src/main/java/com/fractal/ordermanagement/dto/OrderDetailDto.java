package com.fractal.ordermanagement.dto;

public class OrderDetailDto {
    private Long orderDetailId;
    private String name;
    private Double unitPrice;
    private int quantity;

    public OrderDetailDto() {
    }

    public OrderDetailDto(Long orderDetailId, String name, Double unitPrice, int quantity) {
        this.orderDetailId = orderDetailId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
