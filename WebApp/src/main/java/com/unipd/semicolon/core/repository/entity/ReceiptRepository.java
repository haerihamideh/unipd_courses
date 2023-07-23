package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Receipt;
import com.unipd.semicolon.core.entity.enums.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReceiptRepository extends
        JpaRepository<Receipt, Long>,
        JpaSpecificationExecutor<Receipt> {

    Receipt save(Receipt receipt);
    Receipt findReceiptById(Long id);
    List<Receipt> findByPaymentMethod(PaymentMethod paymentMethod);
    List<Receipt> findByDate(Date date);
}
