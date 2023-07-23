package com.unipd.semicolon.api.model;

import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Pharmacy;
import com.unipd.semicolon.core.entity.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class OrderModel {
    private Long id;
    private LocalDate orderDate;
    private Map<Long, Integer> orderDrugs;
    private Map<Long, Integer> orderMaterials;
    private OrderStatus status;
    private float price;
    private boolean isActive;
    private Pharmacy pharmacy;

    private String token;

    public OrderModel(
            LocalDate orderDate,
            Map<Long, Integer> orderDrugs,
            Map<Long, Integer> orderMaterials,
            OrderStatus status,
            float price,
            boolean isActive,
            Pharmacy pharmacy,
            String token) {
        this.orderDate = orderDate;
        this.orderDrugs = orderDrugs;
        this.orderMaterials = orderMaterials;
        this.status = status;
        this.price = price;
        this.isActive = isActive;
        this.pharmacy = pharmacy;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Map<Long, Integer> getOrderDrugs() {
        return orderDrugs;
    }

    public void setOrderDrugs(Map<Long, Integer> orderDrugs) {
        this.orderDrugs = orderDrugs;
    }

    public Map<Long, Integer> getOrderMaterials() {
        return orderMaterials;
    }

    public void setOrderMaterials(Map<Long, Integer> orderMaterials) {
        this.orderMaterials = orderMaterials;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
