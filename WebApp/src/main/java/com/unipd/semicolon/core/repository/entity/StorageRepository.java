package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Pharmacy;
import com.unipd.semicolon.core.entity.Storage;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StorageRepository {
    Storage save(
            Storage storage
    );
    boolean delete(
            Storage storage
    );

    Storage findById(Long id);

    List<Storage> getAll();

    Storage findStorageByPharmacyIdAndDrugId(
            Long pharmacyId,
            Long drugId
    );

    Storage findStorageByPharmacyIdAndMaterialId(
            Long pharmacyId,
            Long materialId
    );
    List<Storage> findStoragesByPharmacyId (Long id);


}