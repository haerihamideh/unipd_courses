package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Order;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Repository
public interface OrderRepository{
    Order save(Order order);

    List<Order> getAll();

    Order findOrderById(Long id);

    void delete(Order order);

    List<Order> getAllBetweenDate(LocalDate start, LocalDate end);


}