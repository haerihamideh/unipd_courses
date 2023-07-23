package com.unipd.semicolon.business.service;

import com.unipd.semicolon.core.domain.MaterialResponse;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface MaterialService {
    Material save(
            String name,
            Long supplier,
            Country countryOfProduction,
            LocalDate expirationDate,
            byte[] image,
            Gender gender,
            float price,
            AgeGroup ageGroup,
            LocalDate lastModifiedDate,
            String description
    ) throws SQLException;

    boolean edit(
            Long id,
            String name,
            Long supplier,
            LocalDate expirationDate,
            byte[] image,
            Gender gender,
            AgeGroup ageGroup,
            float price,
            LocalDate lastModifiedDate,
            String description,
            Country countryOfProduction) throws SQLException;

    Material getById(Long id);

    List<MaterialResponse> getAll(Country countryOfProduction, Long supplierId, Gender gender);
}
