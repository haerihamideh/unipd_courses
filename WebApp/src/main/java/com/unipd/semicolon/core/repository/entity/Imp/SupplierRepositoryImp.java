package com.unipd.semicolon.core.repository.entity.Imp;

import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.core.repository.entity.SupplierRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SupplierRepositoryImp extends CustomRepository implements SupplierRepository {
    public Supplier saveSupplier(Supplier s) {
        return save(Supplier.class, s);

    }

    public boolean deleteSupplier(Supplier s) {
        delete(Supplier.class, s);
        return true;
    }

    @Override
    public Supplier findById(Long id) {
        return findById(Supplier.class, id);
    }
}
