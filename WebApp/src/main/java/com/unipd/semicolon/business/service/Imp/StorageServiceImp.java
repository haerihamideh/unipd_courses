package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.exception.*;
import com.unipd.semicolon.business.exception.IllegalArgumentException;
import com.unipd.semicolon.business.mapper.StorageMapper;
import com.unipd.semicolon.business.service.SecurityService;
import com.unipd.semicolon.business.service.StorageService;
import com.unipd.semicolon.core.domain.StorageReportResponse;
import com.unipd.semicolon.core.domain.StorageResponse;
import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Pharmacy;
import com.unipd.semicolon.core.entity.Storage;
import com.unipd.semicolon.core.repository.entity.DrugRepository;
import com.unipd.semicolon.core.repository.entity.MaterialRepository;
import com.unipd.semicolon.core.repository.entity.PharmacyRepository;
import com.unipd.semicolon.core.repository.entity.StorageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.IllegalStateException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StorageServiceImp implements StorageService {

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private SecurityService securityService;

    @Override
    public Storage save(Long pharmacyId,
                        List<Long> drugId,
                        List<Long> materialId,
                        int amount,
                        int threshold,
                        double discount,
                        String token) {
        String roleFromToken = securityService.getRoleFromToken(token);
        if (roleFromToken.contains("admin")) {
            Objects.requireNonNull(pharmacyId, "Pharmacy is null"); // Check that pharmacy is not null
            if (amount <= 0 || threshold <= 0 || discount < 0.0 || discount > 100.0) {
                throw new IllegalArgumentException("Invalid input parameter");
            } else if (drugId == null && materialId == null) {
                throw new IllegalArgumentException("Either drug or material must be specified");
            } else {
                // Declare variables to store the found entities
                Pharmacy pharmacyRepositoryById = null;
                Material materialRepositoryById = null;
                Drug drugRepositoryById = null;
                List<Drug> drugList = new ArrayList<>();
                List<Material> materialList = new ArrayList<>();
//            if (drugId != null) {
//                for (Long i : drugId) {
//                    drugList.add(drugRepository.findById(i).get());
//                }
//            }
//            if (materialId != null) {
//                for (Long i : materialId) {
//                    materialList.add(materialRepository.findById(i).get());
//                }
//            }
                if (pharmacyRepository.findById(pharmacyId).isPresent()) {
                    pharmacyRepositoryById = pharmacyRepository.findById(pharmacyId).get();
                    // check material
                    if (materialId != null) {
                        for (Long i : materialId) {
                            if (materialRepository.findById(i) != null) {
                                materialRepositoryById = materialRepository.findById(i).get();
                                Storage storage = new Storage(pharmacyRepositoryById,
                                        null,
                                        materialRepositoryById,
                                        amount,
                                        threshold,
                                        discount);

                                Storage savedStorage = storageRepository.save(storage);
                                if (savedStorage == null) {
                                    throw new RuntimeException("Failed to save Storage");
                                }
                                return savedStorage;

                            }
                        }
                    }
                    if (drugId != null) {
                        for (Long i : drugId) {
                            if (drugRepository.findById(i) != null) {
                                drugRepositoryById = drugRepository.findById(i).get();
                                Storage storage = new Storage(pharmacyRepositoryById,
                                        drugRepositoryById,
                                        null,
                                        amount,
                                        threshold,
                                        discount);
                                Storage savedStorage = storageRepository.save(storage);
                                if (savedStorage == null) {
                                    throw new RuntimeException("Failed to save Storage");
                                }
                                return savedStorage;
                            }
                        }
                    }

                } else {
                    throw new NotFoundException();
                }

            }
        } else {
            throw new PermissionException(token);
        }
        return null;
    }

    @Override
    public boolean edit(Long storageId,
                        Pharmacy pharmacy,
                        Drug drug,
                        Material material,
                        int amount,
                        int threshold,
                        double discount,
                        String token) {
        String roleFromToken = securityService.getRoleFromToken(token);
        if (roleFromToken.contains("admin")) {
            if (storageId == null || storageId < 0) {
                throw new IllegalArgumentException("storageId is null");
            } else {
                Storage storage = storageRepository.findById(storageId);
                if (storage == null) {
                    throw new NotFoundException();
                }
                if (pharmacy != null)
                    storage.setPharmacy(pharmacy);
                if (drug != null)
                    storage.setDrug(drug);
                if (material != null)
                    storage.setMaterial(material);
                if (amount > 0)
                    storage.setAmount(amount);
                if (threshold > 0)
                    storage.setThreshold(threshold);
                if (discount >= 0 && discount <= 100)
                    storage.setDiscount(discount);
                storageRepository.save(storage);
                return true;
            }
        } else {
            throw new PermissionException(token);
        }
    }

    @Override
    public boolean delete(Long id, String token) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("Cannot delete null storage!");
        } else {
            try {
                String roleFromToken = securityService.getRoleFromToken(token);
                if (roleFromToken.contains("admin")) {
                    Storage storage = storageRepository.findById(id);
                    storageRepository.delete(storage);
                    return true;
                } else {
                    throw new PermissionException(token);
                }

            } catch (Exception e) {
                throw e;
            }
        }
    }

    @Override
    public StorageResponse getById(Long id) {
        Storage storage = storageRepository.findById(id);
        if (storage == null)
            throw new EntityNotFoundException("Storage not found with id: " + id);
        {
            return new StorageResponse(
                    storage.getId(),
                    storage.getPharmacy(),
                    storage.getDrug(),
                    storage.getMaterial(),
                    storage.getAmount(),
                    storage.getThreshold(),
                    storage.getDiscount());
        }
    }

    @Override
    public List<StorageResponse> getAll() {
        List<StorageResponse> storageList = new ArrayList<>();
        for (Storage storage : storageRepository.getAll()) {
            storageList.add(StorageMapper.storageResponse(storage));
        }
        return storageList;
    }

    @Override
    public boolean updateStorage(
            Storage storage,
            int amount) {
        storage.setAmount(amount);
        return storageRepository.save(storage) != null;
    }

    @Override
    public Storage storageExist(
            Pharmacy pharmacy,
            Drug drug,
            Material material) {
        Storage storage = null;
        if (drug != null) {
            storage = storageRepository.findStorageByPharmacyIdAndDrugId(pharmacy.getId(), drug.getId());
        } else if (material != null) {
            storage = storageRepository.findStorageByPharmacyIdAndMaterialId(pharmacy.getId(), material.getId());
        }
        return storage;
    }

    @Override
    public List<Storage> getAllByPharmacyId(Long pharmacyId) {
        List<Storage> storageList = storageRepository.getAll();

        List<Storage> filteredStorageList = storageList.stream()
                .filter(obj -> obj.getPharmacy().getId().equals(pharmacyId))
                .collect(Collectors.toList());

        return filteredStorageList;
    }

    public StorageReportResponse getStorageReportResponse(Long pharmacyId) {

        pharmacyRepository.findById(pharmacyId).orElseThrow(
                () -> new IllegalStateException("Pharmacy is not found by id = " + pharmacyId
                ));

        List<Storage> storageList = getAllByPharmacyId(pharmacyId);

        float drugPrice = 0f;
        float materialPrice = 0f;

        int drugCount = 0;
        int materialCount = 0;

        for (Storage storage : storageList) {
            Drug drug = storage.getDrug();
            Material material = storage.getMaterial();

            if (drug != null) {
                drugPrice += drug.getPrice() * storage.getAmount();
                drugCount += 1;
            }

            if (material != null) {
                materialPrice += material.getPrice() * storage.getAmount();
                materialCount += 1;
            }
        }

        return new StorageReportResponse(pharmacyId, drugCount, materialCount, drugPrice, materialPrice);
    }

    public List<StorageReportResponse> getAllStorageReports() {
        List<StorageResponse> storageList = getAll();
        HashMap<Long, StorageReportResponse> storagesByPharmacy = new HashMap<>();
        List<StorageReportResponse> responseEntities = new ArrayList<>();

        for (StorageResponse storage : storageList) {
            storagesByPharmacy.put(storage.getPharmacy().getId(), getStorageReportResponse(storage.getPharmacy().getId()));
        }

        for (Long id : storagesByPharmacy.keySet()) {
            responseEntities.add(storagesByPharmacy.get(id));
        }

        return responseEntities;
    }

}