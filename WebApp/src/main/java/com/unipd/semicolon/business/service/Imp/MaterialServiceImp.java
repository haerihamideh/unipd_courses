package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.exception.NotFoundException;
import com.unipd.semicolon.business.exception.IllegalArgumentException;
import com.unipd.semicolon.business.exception.IllegalStateException;
import com.unipd.semicolon.business.mapper.MaterialMapper;
import com.unipd.semicolon.business.service.MaterialService;
import com.unipd.semicolon.core.domain.MaterialResponse;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;
import com.unipd.semicolon.core.repository.entity.MaterialRepository;
import com.unipd.semicolon.core.repository.entity.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialServiceImp implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public Material save(
            String name,
            Long supplierId,
            Country countryOfProduction,
            LocalDate expirationDate,
            byte[] image,
            Gender gender,
            float price,
            AgeGroup ageGroup,
            LocalDate lastModifiedDate,
            String description) throws SQLException {
        Supplier supplier = null;
        if (name == null ||
                gender == null || price < 0
                || countryOfProduction == null) {
            throw new IllegalArgumentException("Invalid input parameter");
        } else {
            if (supplierId != null) {
                supplier = supplierRepository.findById(supplierId);
            } else {
                throw new IllegalArgumentException("Invalid input parameter, Supplier has not been set");
            }

            Material material = new Material(
                    name,
                    supplier,
                    countryOfProduction,
                    expirationDate,
                    image,
                    gender,
                    price,
                    ageGroup,
                    lastModifiedDate,
                    description);
            materialRepository.save(material);
            return material;
        }
    }

    @Override
    public boolean edit(
            Long id,
            String name,
            Long supplierId,
            LocalDate expirationDate,
            byte[] image,
            Gender gender,
            AgeGroup ageGroup,
            float price,
            LocalDate lastModifiedDate,
            String description,
            Country countryOfProduction) throws SQLException {
        Supplier supplier = null;
        if (id == null || id < 0 || name == null || supplierId == null
                || gender == null || price < 0 || countryOfProduction == null) {
            throw new IllegalArgumentException("Invalid input parameter");
        } else {
            supplier = supplierRepository.findById(supplierId);
            Material material = materialRepository.findById(id)
                    .orElseThrow(() -> new IllegalStateException("Material not found - " + id));
            if (material == null) {
                throw new NotFoundException();
            }
            if (name != null) {
                material.setName(name);
            }
            if (supplier != null) {
                material.setSupplier(supplier);
            }
            if (countryOfProduction != null) {
                material.setCountryOfProduction(countryOfProduction);
            }
            if (expirationDate != null) {
                material.setExpirationDate(expirationDate);
            }
            if (image != null) {
                material.setImage(image);
            }
            if (gender != null) {
                material.setGender(gender);
            }
            if (price < 0) {
                material.setPrice(price);
            }
            if (ageGroup != null) {
                material.setAgeGroup(ageGroup);
            }
            if (lastModifiedDate != null) {
                material.setLastModifiedDate(lastModifiedDate);
            }
            if (description != null) {
                material.setDescription(description);
            }
            return true;
        }
    }

    @Override
    public Material getById(Long id) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Material not found - " + id));
        if (material != null) {
            return material;
        } else {
            throw new EntityNotFoundException("Material Not Found with id" + id);
        }
    }

    @Override
    public List<MaterialResponse> getAll(
            Country countryOfProduction,
            Long supplierId,
            Gender gender) {

        Specification<Material> spec = Specification.where(null);

        if (countryOfProduction != null) {
            spec = spec.and((root, query, builder) -> {
                return builder.equal(root.get("countryOfProduction"), countryOfProduction);
            });
        }

        if (supplierId != null) {
            spec = spec.and((root, query, builder) -> {
                Join<Material, Supplier> supplierJoin = root.join("supplier");
                return builder.equal(supplierJoin.get("id"), supplierId);
            });
        }

        if (gender != null) {
            spec = spec.and((root, query, builder) -> {
                return builder.equal(root.get("gender"), gender);
            });
        }

        List<Material> materials = materialRepository.findAll(spec);
        List<MaterialResponse> materialList = new ArrayList<>();
        for (Material material : materials) {
            materialList.add(MaterialMapper.materialResponse(material));
        }
        return materialList;
    }
}
