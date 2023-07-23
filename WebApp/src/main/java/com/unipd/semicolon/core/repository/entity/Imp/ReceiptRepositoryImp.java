package com.unipd.semicolon.core.repository.entity.Imp;

import com.unipd.semicolon.core.entity.Receipt;
import com.unipd.semicolon.core.entity.enums.PaymentMethod;
import com.unipd.semicolon.core.exception.RepositoryException;
import com.unipd.semicolon.core.repository.entity.ReceiptRepository;
import com.unipd.semicolon.core.repository.entity.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public abstract  class ReceiptRepositoryImp extends CustomRepository implements ReceiptRepository{
    @Override
    public Receipt save(Receipt receipt) { // saving new receipt.
        return save(Receipt.class, receipt);
    }
    @Override
    public Receipt findReceiptById(Long id) {
        return findById(Receipt.class, id);
    }

    @Override
    public List<Receipt> findByPaymentMethod(PaymentMethod paymentMethod) {
        return listQueryWrapper(entityManager.createQuery(
                "SELECT g FROM Receipt g WHERE g.paymentMethod = :paymentMethod ORDER BY g.id DESC",
                Receipt.class).setParameter("paymentMethod", paymentMethod));
    }

    @Override
    public List<Receipt> findByDate(Date date) {
        return listQueryWrapper(entityManager.createQuery(
                "SELECT g FROM Receipt g WHERE g.date = :date ORDER BY g.id DESC",
                Receipt.class).setParameter("date", date));
    }

}
