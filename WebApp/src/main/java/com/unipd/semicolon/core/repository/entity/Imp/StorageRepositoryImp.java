package com.unipd.semicolon.core.repository.entity.Imp;

import com.unipd.semicolon.core.entity.Storage;
import com.unipd.semicolon.core.repository.entity.StorageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StorageRepositoryImp extends CustomRepository implements StorageRepository {

    @Transactional
    @Override
    public Storage save(Storage storage) {

        return save(Storage.class, storage);
    }

    @Override
    public boolean delete(Storage storage) {

        delete(Storage.class, storage);
        return true;
    }

    @Override
    public Storage findById(Long id) {

        return findById(Storage.class, id);
    }

    @Override
    public List<Storage> getAll() {
        return listQueryWrapper(entityManager.createQuery(
                "select g from Storage g order by g.id desc ",
                Storage.class));
    }

    @Override
    public Storage findStorageByPharmacyIdAndDrugId(
            Long pharmacyId,
            Long drugId) {
        return findQueryWrapper(entityManager.createQuery(
                "SELECT s FROM Storage s WHERE s.pharmacy.id =: pharmacyId AND s.drug.id =: drugId",
                Storage.class)
                .setParameter("pharmacyId", pharmacyId)
                .setParameter("drugId", drugId));
    }

    @Override
    public Storage findStorageByPharmacyIdAndMaterialId(Long pharmacyId, Long materialId) {
        return findQueryWrapper(entityManager.createQuery(
                "SELECT s FROM Storage s WHERE s.pharmacy.id =: pharmacyId AND s.drug.id =: materialId",
                Storage.class)
                .setParameter("pharmacyId", pharmacyId)
                .setParameter("materialId", materialId));
    }

    public List<Storage> findStoragesByPharmacyId(Long id) {
        return listQueryWrapper(
                entityManager.createQuery("SELECT s FROM Storage s WHERE s.pharmacy.id = :id ORDER BY s.id DESC",
                        Storage.class));
    }
}
