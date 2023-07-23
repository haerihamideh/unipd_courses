package com.unipd.semicolon.core.domain;

import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.OrderProduct;
import com.unipd.semicolon.core.entity.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public class OrderResponse {
    private Long id;
    private LocalDate orderDate;
    private List<OrderProduct> orderProducts;
    private OrderStatus status;
    private float price;
    private boolean isActive;

    public OrderResponse(
            Long id,
            LocalDate orderDate,
            OrderStatus status,
            float price,
            boolean isActive,
            List<OrderProduct> orderProducts) {
        this.orderDate = orderDate;
        this.status = status;
        this.price = price;
        this.isActive = isActive;
        this.orderProducts = orderProducts;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public float getPrice() {
        return price;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
