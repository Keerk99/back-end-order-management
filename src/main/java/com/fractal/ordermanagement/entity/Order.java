package com.fractal.ordermanagement.entity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "status")
    private String status; //p: pending, i: in progress, c: completed

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails;

    @Column(name = "total")
    private double total;

    public Order() {
        this.date = LocalDate.now();
        this.status = "p";
    }

    public Order(String orderNumber, List<OrderDetail> orderDetails) {
        this.orderNumber = orderNumber;
        this.date = LocalDate.now();
        this.status = "p";
        this.orderDetails = orderDetails;
        calculateTotal();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<OrderDetail> getOrderDetail() {
        return orderDetails;
    }

    public void setOrderDetail(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setOrder(this);
        }
        calculateTotal();
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void calculateTotal() {
        double sum = 0.0;
        for (OrderDetail orderDetail : orderDetails) {
            sum += orderDetail.getUnitPrice() * orderDetail.getQuantity();
        }
        this.total = sum;
    }
}
