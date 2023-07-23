package com.unipd.semicolon.business.service;

import com.unipd.semicolon.core.domain.StorageReportResponse;
import com.unipd.semicolon.core.domain.StorageResponse;
import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Pharmacy;
import com.unipd.semicolon.core.entity.Storage;

import java.util.List;

public interface StorageService {
    Storage save(Long pharmacyId,
                 List<Long> drugId,
                 List<Long> materialId,
                 int amount,
                 int threshold,
                 double discount,
                 String token);

    boolean edit(
            Long id_storage,
            Pharmacy pharmacy,
            Drug drug,
            Material material,
            int amount,
            int threshold,
            double discount,
            String token
    );

    boolean delete(
            Long id,
            String token
    );

    StorageResponse getById(
            Long id
    );

    List<StorageResponse> getAll();

    boolean updateStorage(
            Storage storage,
            int amount
    );

    Storage storageExist(
            Pharmacy pharmacy,
            Drug drug,
            Material material
    );

    List<Storage> getAllByPharmacyId(
            Long id
    );

    StorageReportResponse getStorageReportResponse(
            Long pharmacyId
    );

    List<StorageReportResponse> getAllStorageReports();

}
