package com.unipd.semicolon.business.mapper;

import com.unipd.semicolon.core.domain.OrderResponse;
import com.unipd.semicolon.core.entity.Order;
import com.unipd.semicolon.core.entity.OrderProduct;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public static List<OrderResponse> orderResponse(List<Order> orders) {
        List<OrderResponse> orderList = new ArrayList<>();
        if ( orders != null) {
            for (Order order : orders) {
                orderList.add(new OrderResponse(
                        order.getId(),
                        order.getOrderDate(),
                        order.getStatus(),
                        order.getPrice(),
                        order.isActive(),
                        order.getOrderProducts()
                ));
            }
            return orderList;
        } else return null;
    }
}
