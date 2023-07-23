package com.unipd.semicolon.core.repository.entity.Imp;

import com.unipd.semicolon.core.entity.Order;
import com.unipd.semicolon.core.repository.entity.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class OrderRepositoryImp extends CustomRepository implements OrderRepository {
    @Transactional
    @Override
    public Order save(Order order) {
        return save(Order.class, order);
    }

    @Override
    public void delete(Order order) {
        delete(Order.class, order);
    }

    @Override
    public List<Order> getAllBetweenDate(
            LocalDate startDate,
            LocalDate endDate) {
        return listQueryWrapper(entityManager.createQuery
                        ("SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate", Order.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate));

    }

    @Override
    public Order findOrderById(Long id) {
        return findById(Order.class, id);
    }

    @Override
    public List<Order> getAll() {
        return listQueryWrapper(entityManager.createQuery
                ("SELECT g FROM Order g ORDER BY g.id desc ", Order.class));
    }

}
