package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Login;
import com.unipd.semicolon.core.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductsRepository extends JpaRepository<OrderProduct, Long>, JpaSpecificationExecutor<OrderProduct> {

    List<OrderProduct> findAllByOrder_Id(Long orderId);
}
