package com.unipd.semicolon.business.service;

import com.unipd.semicolon.business.enums.OrderReport;
import com.unipd.semicolon.business.exception.CustomException;
import com.unipd.semicolon.core.domain.OrderResponse;
import com.unipd.semicolon.core.entity.Order;
import com.unipd.semicolon.core.entity.Pharmacy;
import com.unipd.semicolon.core.entity.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
public interface OrderService {
    Order save(
            String token,
            LocalDate orderDate,
            Map<Long, Integer> orderDrugs,
            Map<Long, Integer> orderMaterials,
            OrderStatus status,
            float price,
            boolean isActive,
            Pharmacy pharmacy
    ) throws CustomException;

    void delete(
           String token,
           Long id
    );

    Order getById(
            String token,
            Long id
    ) throws CustomException;

    List<OrderResponse> getAll();

    Order status(
            String token,
            Long orderId,
            OrderStatus orderStatus) throws CustomException;


    List<OrderResponse> reportBaseDate(
            String token,
            OrderReport orderReport,
            Short num) throws CustomException;
}
