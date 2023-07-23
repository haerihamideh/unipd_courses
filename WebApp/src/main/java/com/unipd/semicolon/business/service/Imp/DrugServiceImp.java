package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.exception.*;
import com.unipd.semicolon.business.exception.IllegalArgumentException;
import com.unipd.semicolon.business.exception.IllegalStateException;
import com.unipd.semicolon.business.mapper.DrugMapper;
import com.unipd.semicolon.business.service.DrugService;
import com.unipd.semicolon.business.service.ValidationService;
import com.unipd.semicolon.core.domain.DrugResponse;
import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;
import com.unipd.semicolon.core.repository.entity.DrugRepository;
import com.unipd.semicolon.core.repository.entity.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class DrugServiceImp implements DrugService {
    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ValidationService validationService;

    @Override
    public Drug save(
            String name,
            Long supplierId,
            Date expirationDate,
            byte[] image,
            String shape,
            Gender gender,
            AgeGroup ageGroup,
            boolean isSensitive,
            boolean needPrescription,
            String description,
            int limitation,
            float price,
            Country countryOfProduction) throws CustomException {
        Objects.requireNonNull(name, "Name is null");
        Objects.requireNonNull(supplierId, "Supplier is null");
        if (image != null) {
            validationService.validateImage(image, 10 * 1024 * 1024);
        }
        validationService.validateDate(expirationDate, false);
        validationService.validatePrice(price);
        if (limitation <= 0) {
            throw new IllegalArgumentException("Limitation amount can not be negative");
        }
        Supplier supplier = supplierRepository.findById(supplierId);
        if (supplier == null) {
            throw new IllegalArgumentException("Supplier should be defined");
        }
        Drug drug = new Drug(
                name,
                supplier,
                expirationDate,
                image,
                shape,
                gender,
                ageGroup,
                isSensitive,
                needPrescription,
                description,
                limitation,
                price,
                countryOfProduction);

        if (drugRepository.findDrugsByNameAndSupplierAndExpirationDateAndShapeAndAgeGroupAndCountryOfProduction(
                drug.getName(),
                drug.getSupplier(),
                drug.getExpirationDate(),
                drug.getShape(),
                drug.getAgeGroup(),
                drug.getCountryOfProduction()
        ).isEmpty() ) {
            drugRepository.save(drug);
        } else {
            throw new DrugExistsException();
        }
        return drug;
    }

    @Override
    public Drug edit(
            Long drugId,
            String name,
            Long supplierId,
            Date expirationDate,
            byte[] image,
            String shape,
            Gender gender,
            AgeGroup ageGroup,
            boolean isSensitive,
            boolean needPrescription,
            String description,
            int limitation,
            float price,
            Country countryOfProduction) throws SQLException {
        if (drugId == null || drugId < 0) {
            throw new IllegalArgumentException("Invalid input parameter");
        } else {
            if (image != null) {
                validationService.validateImage(image, 10 * 1024 * 1024);
            }
            if (expirationDate != null) {
                validationService.validateDate(expirationDate, false);
            }
            if (price != 0) {
                validationService.validatePrice(price);
            }
            if (limitation < 0) {
                throw new IllegalArgumentException("Limitation amount can not be negative");
            }

            Drug drug = drugRepository.findById(drugId)
                    .orElseThrow(() -> new IllegalStateException("Drug not found - " + drugId));
            if (drug == null) {
                throw new NotFoundException();
            }
            if (name != null) {
                drug.setName(name);
            }
            if (supplierId != null && supplierId > 0) {
                Supplier supplier = supplierRepository.findById(supplierId);
                if (supplier != null)
                    drug.setSupplier(supplier);
            }
            if (countryOfProduction != null) {
                drug.setCountryOfProduction(countryOfProduction);
            }
            if (expirationDate != null) {
                drug.setExpirationDate(expirationDate);
            }
            if (image != null) {
                drug.setImage(image);
            }
            if (gender != null) {
                drug.setGender(gender);
            }
            if (price > 0.0) {
                drug.setPrice(price);
            }
            if (ageGroup != null) {
                drug.setAgeGroup(ageGroup);
            }
            if (limitation != 0) {
                drug.setLimitation(limitation);
            }
            if (description != null) {
                drug.setDescription(description);
            }
            drugRepository.save(drug);
            return drug;

        }
    }

    @Override
    public DrugResponse getById(Long id) {
        Drug drug = drugRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Drug not found - " + id));

        if (drug == null)
            throw new EntityNotFoundException("Drug Not Found with id" + id);
        {
            return new DrugResponse(
                    drug.getId(),
                    drug.getName(),
                    drug.getSupplier(),
                    drug.getExpirationDate(),
                    drug.getImage(),
                    drug.getShape(),
                    drug.getGender(),
                    drug.getAgeGroup(),
                    drug.isSensitive(),
                    drug.isNeedPrescription(),
                    drug.getDescription(),
                    drug.getLimitation(),
                    drug.getPrice(),
                    drug.getCountryOfProduction());
        }
    }

    public List<DrugResponse> getAll(Long supplierId,
                                     Integer isSensitive,
                                     Country countryOfProduction,
                                     String shape, Gender gender) {
        Specification<Drug> spec = Specification.where(null);

        if (supplierId != null) {
            spec = spec.and((root, query, builder) -> {
                Join<Drug, Supplier> supplierJoin = root.join("supplier");
                return builder.equal(supplierJoin.get("id"), supplierId);
            });
        }

        if (isSensitive != null) {
            spec = spec.and((root, query, builder) -> {
                return builder.equal(root.get("isSensitive"), isSensitive == 1);
            });
        }

        if (countryOfProduction != null) {
            spec = spec.and((root, query, builder) -> {
                return builder.equal(root.get("countryOfProduction"), countryOfProduction);
            });
        }

        if (shape != null && !shape.isEmpty()) {
            spec = spec.and((root, query, builder) -> {
                return builder.like(builder.lower(root.get("shape")), "%" + shape.toLowerCase() + "%");
            });
        }

        if (gender != null) {
            spec = spec.and((root, query, builder) -> {
                return builder.equal(root.get("gender"), gender);
            });
        }

        List<Drug> drugs;
        drugs = drugRepository.findAll(spec);

        List<DrugResponse> drugList = new ArrayList<>();
        for (Drug drug : drugs) {
            drugList.add(DrugMapper.drugResponse(drug));
        }
        return drugList;
    }

    @Override
    public List<Drug> findDrugsByNameAndSupplierAndExpirationDateAndShapeAndAgeGroupAndCountryOfProduction(
            String name,
            Supplier supplier,
            Date expirationDate,
            String shape,
            AgeGroup ageGroup,
            Country countryOfProduction
    ) {
        return drugRepository.findDrugsByNameAndSupplierAndExpirationDateAndShapeAndAgeGroupAndCountryOfProduction(
                name,
                supplier,
                expirationDate,
                shape,
                ageGroup,
                countryOfProduction
        );
    }
}
